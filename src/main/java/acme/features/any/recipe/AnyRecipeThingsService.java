package acme.features.any.recipe;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.amount.Amount;
import acme.entities.moneyExchange.MoneyExchange;
import acme.entities.recipe.Recipe;
import acme.entities.thing.Thing;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyRecipeThingsService implements AbstractListService<Any,Thing> {
	@Autowired
	protected AnyRecipeRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;	
	
	@Override
	public boolean authorise(final Request<Thing> request) {
		
		assert request !=null;
		int recipeId;
		recipeId = request.getModel().getInteger("id");
		final Recipe r = this.repository.findRecipeById(recipeId);
		return r.isPublished();
			
	}
	@Override
	public Collection<Thing> findMany(final Request<Thing> request) {
		
		final Collection<Thing> result = new HashSet<>();
		int recipeId;
		recipeId = request.getModel().getInteger("id");
		
		final Collection<Amount> amounts = this.repository.findQuantitiesByRecipeId(recipeId);
		
		for(final Amount a: amounts) {
			final int amountId = a.getId();
			final Thing t = this.repository.findThingByAmountId(amountId);
			result.add(t);
		}
		
		return result;
	}

		
	@Override
	public void unbind(final Request<Thing> request, final Thing entity, final Model model) {
		assert request != null; 
		assert entity != null; 
		assert model != null;
		
		final Money newRetailPrice = this.moneyExchangeThing(entity);
		model.setAttribute("newRetailPrice", newRetailPrice);
		
		request.unbind(entity, model, "name", "code", "retailPrice");
	}
	
	
	public Money moneyExchangeThing(final Thing t) {
		final String itemCurrency = t.getRetailPrice().getCurrency();
		
		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		final String systemCurrency = this.systemConfigRepository.findSystemConfiguration().getSystemCurrency();
		final Double conversionAmount;
			
		if(!systemCurrency.equals(itemCurrency)) {
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
