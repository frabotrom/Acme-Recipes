package acme.features.chef.recipe;

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
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefRecipeListService implements AbstractListService<Chef, Recipe> {
	
	@Autowired
	protected ChefRecipeRepository repository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Recipe> findMany(final Request<Recipe> request) {
		assert request != null;
		
		final int chefId = request.getPrincipal().getActiveRoleId();
		
		Collection<Recipe> recipes;
		final Collection<Recipe> result = new HashSet<>();
		recipes = this.repository.findRecipesByChefId(chefId);
		for(final Recipe r: recipes) {
			if(r.isPublished()) {
			r.setTotalPrice(this.getTotalPrice(r));
			result.add(r);
		}
		}
		return result;
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "heading","totalPrice");

	}

	private Money getTotalPrice(final Recipe r) {
        final Collection<Amount> amounts = this.repository.findAllAmountOfRecipe(r);
        final Money result = new Money();
        final String systemCurrency = this.systemConfigRepository.findSystemConfiguration().getSystemCurrency();
        result.setCurrency(systemCurrency);
        result.setAmount(0.);
        for(final Amount a:amounts) {

                final String itemCurrency = a.getThing().getRetailPrice().getCurrency();
                if (!itemCurrency.equals(systemCurrency)) {
                	final Money newRetailPrice = this.moneyExchangeThings(a.getThing());
            		a.getThing().setRetailPrice(newRetailPrice);
                }
                final Money itemPrice = a.getThing().getRetailPrice();
                result.setAmount(result.getAmount()+ itemPrice.getAmount()*a.getQuantity());

        }
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