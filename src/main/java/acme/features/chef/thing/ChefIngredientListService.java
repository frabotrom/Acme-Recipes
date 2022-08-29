package acme.features.chef.thing;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.moneyExchange.MoneyExchange;
import acme.entities.thing.Thing;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefIngredientListService implements AbstractListService<Chef, Thing>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefThingRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;

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
	public void unbind(final Request<Thing> request, final Thing entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
	
		final Money newRetailPrice = this.moneyExchangeIngredients(entity);
		model.setAttribute("newRetailPrice", newRetailPrice);
		
		request.unbind(entity, model, "name", "code", "description", "retailPrice");
	}

	@Override
	public Collection<Thing> findMany(final Request<Thing> request) {
		assert request != null;
			
		final int chefId = request.getPrincipal().getActiveRoleId();
			
		return this.repository.findIngredientsByChef(chefId);
	}
	
	public Money moneyExchangeIngredients(final Thing t) {
		final String thingCurrency = t.getRetailPrice().getCurrency();
			
		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		final String systemCurrency = this.systemConfigRepository.findSystemConfiguration().getSystemCurrency();
		final Double conversionAmount;
				
		if(!systemCurrency.equals(thingCurrency)) {
			MoneyExchange conversion;
			conversion = moneyExchange.computeMoneyExchange(t.getRetailPrice(), systemCurrency);
			conversionAmount = conversion.getTarget().getAmount();	
		}
		else {
			conversionAmount = t.getRetailPrice().getAmount();
		}
			
		final Money newBudget = new Money();
		newBudget.setAmount(conversionAmount);
		newBudget.setCurrency(systemCurrency);
			
		return newBudget;
	}

}
