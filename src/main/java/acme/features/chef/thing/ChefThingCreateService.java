package acme.features.chef.thing;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.thing.Thing;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;
import spamDetector.SpamDetector;

@Service
public class ChefThingCreateService implements AbstractCreateService<Chef, Thing>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefThingRepository repository;
	
	// AbstractCreateService<Chef, Thing> interface ---------------------------
	
	@Override
	public boolean authorise(final Request<Thing> request) {
		assert request != null;
		final int userId = request.getPrincipal().getActiveRoleId();
		
		if (this.repository.findChefById(userId).isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Thing instantiate(final Request<Thing> request) {
		assert request != null;
		
		final Chef chef;
		final Thing result;
		
		chef = this.repository.findChefById(request.getPrincipal().getActiveRoleId()).get();
		result = new Thing();
		result.setChef(chef);
		result.setName("");
		result.setCode("");
		result.setDescription("");
		result.setInfo("");
		result.setPublished(false);
		
		return result;
	}
	
	@Override
	public void bind(final Request<Thing> request, final Thing entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "thingType", "name", "code", "description", "retailPrice", "info");
		
	}
	
	@Override
	public void validate(final Request<Thing> request, final Thing entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			
			Thing thing;
			
			thing = this.repository.findThingByCode(entity.getCode());
			
			errors.state(request, thing == null, "code", "chef.thing.form.error.duplicated-code");
		}
		
		if(!errors.hasErrors("retailPrice")) {
			final Double amount = entity.getRetailPrice().getAmount();
			
			final String[] acceptedCurrencies = this.repository.findAcceptedCurrencies().split(",");
			final Set<String> acceptedCurrenciesAux = new HashSet<String>();
			Collections.addAll(acceptedCurrenciesAux, acceptedCurrencies);
			final Boolean validCurrency = acceptedCurrenciesAux.contains(entity.getRetailPrice().getCurrency());
			
			errors.state(request, amount > 0., "retailPrice", "chef.thing.form.error.retail-price-amount-negative-or-zero");
			errors.state(request, validCurrency, "retailPrice", "chef.thing.form.error.retail-price-currency-invalid");
		}
		
		if(!errors.hasErrors("name")) {
			final boolean isNameSpam = SpamDetector.isSpam(entity.getName(), this.repository.getSystemConfiguration());
			errors.state(request, !isNameSpam, "name", "name.form.spam");
		}
		
		if(!errors.hasErrors("description")) {
			final boolean isDescriptionSpam = SpamDetector.isSpam(entity.getName(), this.repository.getSystemConfiguration());
			errors.state(request, !isDescriptionSpam, "description", "description.form.spam");
		}
		
	}
	
	@Override
	public void unbind(final Request<Thing> request, final Thing entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "thingType", "name", "code", "description", "retailPrice", "info");
		
	}
	
	@Override
	public void create(final Request<Thing> request, final Thing entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);		
	}

	
}
