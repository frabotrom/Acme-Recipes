package acme.features.chef.thing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.moneyExchange.MoneyExchange;
import acme.entities.thing.Thing;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefThingShowService implements AbstractShowService<Chef, Thing>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefThingRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;

	@Override
	public boolean authorise(final Request<Thing> request) {
		assert request != null;

		final int chefId = request.getPrincipal().getActiveRoleId();
		final int thingId = request.getModel().getInteger("id");
		final int thingChefId = this.repository.findThingById(thingId).getChef().getId();

		return  chefId == thingChefId; 
	}

	@Override
	public void unbind(final Request<Thing> request, final Thing entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final Money newRetailPrice = this.moneyExchangeThings(entity);
		model.setAttribute("newRetailPrice", newRetailPrice);
		
		request.unbind(entity, model, "thingType", "name", "code","description","retailPrice", "info", "published");
	}
		
	@Override
	public Thing findOne(final Request<Thing> request) {
		assert request != null;
			
		Thing result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findThingById(id);

		return result;
	}
	
	public Money moneyExchangeThings(final Thing t) {
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
