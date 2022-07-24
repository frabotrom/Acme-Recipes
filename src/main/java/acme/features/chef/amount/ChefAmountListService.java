package acme.features.chef.amount;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.amount.Amount;
import acme.entities.moneyExchange.MoneyExchange;
import acme.entities.recipe.Recipe;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.features.chef.recipe.ChefRecipeRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefAmountListService implements AbstractListService<Chef,Amount>{
	
	@Autowired
	protected ChefRecipeRepository recipeRepository;
	
	@Autowired
	protected ChefAmountRepository repository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;
	
	@Override
	public boolean authorise(final Request<Amount> request) {
		
		assert request != null;
		int recipeId;
		boolean result;
		recipeId = request.getModel().getInteger("id");
		final Recipe recipe = this.recipeRepository.findRecipeById(recipeId);
		result = recipe != null && (recipe.isPublished() || request.isPrincipal(recipe.getChef()));
		return result;
	}

	@Override
	public Collection<Amount> findMany(final Request<Amount> request) {
		assert request != null;
		int recipeId ;
		Collection<Amount> result;
		recipeId = request.getModel().getInteger("id");
		result = this.repository.findAmountByRecipeId(recipeId);
		return result;
	}

	@Override
	public void unbind(final Request<Amount> request, final Amount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Money newRetailPrice = this.moneyExchangeQuantity(entity);
		model.setAttribute("newRetailPrice", newRetailPrice);
		
		request.unbind(entity, model, "quantity", "thing.code","thing.name", "thing.retailPrice", "thing.thingType");
		int recipeId;
		recipeId = request.getModel().getInteger("id");
		model.setAttribute("recipeId", recipeId);
		model.setAttribute("published", entity.getRecipe().isPublished());
	}
	/*@Override
	public void unbind(final Request<Amount> request, final Collection<Amount> entities, final Model model) {
		assert request != null;
		assert model != null;
		
		int recipeId;
		recipeId = request.getModel().getInteger("id");
		model.setAttribute("recipeId", recipeId);
		Recipe recipe;
		recipe = this.recipeRepository.findRecipeById(recipeId);
		model.setAttribute("isPublished", recipe.isPublished());
	}*/
	
	//MÃ©todos auxiliares
	
	public Money moneyExchangeQuantity(final Amount a) {
		final String itemCurrency = a.getThing().getRetailPrice().getCurrency();
			
		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		final String systemCurrency = this.systemConfigRepository.findSystemConfiguration().getSystemCurrency();
		final Double conversionAmount;
				
		if(!systemCurrency.equals(itemCurrency)) {
			MoneyExchange conversion;
			conversion = moneyExchange.computeMoneyExchange(a.getThing().getRetailPrice(), systemCurrency);
			conversionAmount = conversion.getTarget().getAmount();	
		}
		else {
			conversionAmount = a.getThing().getRetailPrice().getAmount();
		}
			
		final Money newBudget = new Money();
		newBudget.setAmount(conversionAmount);
		newBudget.setCurrency(systemCurrency);
			
		return newBudget;
	}

}
