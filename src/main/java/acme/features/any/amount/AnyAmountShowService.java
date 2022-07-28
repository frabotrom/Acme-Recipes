package acme.features.any.amount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.amount.Amount;
import acme.entities.moneyExchange.MoneyExchange;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyAmountShowService implements AbstractShowService<Any,Amount>{
	@Autowired
	protected AnyAmountRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;
	
	@Override
	public boolean authorise(final Request<Amount> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Amount findOne(final Request<Amount> request) {
		assert request != null;
		int amountId;
		Amount amount;
		amountId = request.getModel().getInteger("id");
		amount = this.repository.findAmountById(amountId);
//		final Money newRetailPrice = this.moneyExchangeItems(quantity.getItem());
//		quantity.getItem().setRetailPrice(newRetailPrice);
		return amount;
	}

	@Override
	public void unbind(final Request<Amount> request, final Amount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Money newRetailPrice = this.moneyExchangeAmount(entity);
		model.setAttribute("newRetailPrice", newRetailPrice);

		request.unbind(entity, model, "quantity", "recipe.heading", "thing.name", "thing.code", "thing.info", "thing.description", "thing.retailPrice",
			"thing.thingType", "thing.published", "unit");
		model.setAttribute("published", entity.getRecipe().isPublished());
		
	}
	
	//MÃ©todos auxiliares
	
	public Money moneyExchangeAmount(final Amount q) {
		final String itemCurrency = q.getThing().getRetailPrice().getCurrency();
			
		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		final String systemCurrency = this.systemConfigRepository.findSystemConfiguration().getSystemCurrency();
		final Double conversionAmount;
				
		if(!systemCurrency.equals(itemCurrency)) {
			MoneyExchange conversion;
			conversion = moneyExchange.computeMoneyExchange(q.getThing().getRetailPrice(), systemCurrency);
			conversionAmount = conversion.getTarget().getAmount();	
		}
		else {
			conversionAmount = q.getThing().getRetailPrice().getAmount();
		}
			
		final Money newBudget = new Money();
		newBudget.setAmount(conversionAmount);
		newBudget.setCurrency(systemCurrency);
			
		return newBudget;
	}
	
}
