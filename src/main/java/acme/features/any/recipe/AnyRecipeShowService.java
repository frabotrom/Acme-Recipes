package acme.features.any.recipe;



import java.util.Collection;

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
import acme.framework.services.AbstractShowService;

@Service
public class AnyRecipeShowService implements AbstractShowService<Any, Recipe> {
	
	@Autowired
	protected AnyRecipeRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;

		Recipe result;
		int id;
		id = request.getModel().getInteger("id");
		
		result = this.repository.findRecipeById(id);
		
		final Money totalPrice = this.getTotalPrice(result);
		result.setTotalPrice(totalPrice);
		
		return result;
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "heading", "preparationNotes", "code", "totalPrice", "description", "moreInfo");
		model.setAttribute("readonly", true);
	}

	private Money getTotalPrice(final Recipe t) {
        final Collection<Amount> amounts = this.repository.findAllAmountOfRecipe(t);
        final Money result = new Money();
        final String systemCurrency = this.systemConfigRepository.findSystemConfiguration().getSystemCurrency();
        result.setCurrency(systemCurrency);
        result.setAmount(0.);
        for(final Amount a:amounts) {

                final String thingCurrency = a.getThing().getRetailPrice().getCurrency();
                if (!thingCurrency.equals(systemCurrency)) {
                	final Money newRetailPrice = this.moneyExchangeThings(a.getThing());
            		a.getThing().setRetailPrice(newRetailPrice);
                }
                final Money thingPrice = a.getThing().getRetailPrice();
                result.setAmount(result.getAmount()+ thingPrice.getAmount()*a.getQuantity());

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