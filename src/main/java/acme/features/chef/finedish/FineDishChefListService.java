package acme.features.chef.finedish;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.entities.moneyExchange.MoneyExchange;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class FineDishChefListService implements AbstractListService<Chef,FineDish>{
	
	@Autowired
	protected FineDishChefRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<FineDish> findMany(final Request<FineDish> request) {
		assert request != null;
		final Integer id = request.getPrincipal().getActiveRoleId();
		Collection<FineDish> result;
		result = this.repository.findFineDishes(id);
		return result;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Money newBudget = this.moneyExchangeFineDish(entity);
		model.setAttribute("newBudget", newBudget);
		
		if(entity.isPublished()) {
			model.setAttribute("published", "\u2714");
		} else if(!entity.isPublished()) {
			model.setAttribute("published", "\u274C");
		}
		
		request.unbind(entity, model, "status", "code", "budget");
		model.setAttribute("epicure", entity.getEpicure().getUserAccount().getUsername());
	}
	
	public Money moneyExchangeFineDish(final FineDish f) {
		final String itemCurrency = f.getBudget().getCurrency();
	
		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
		final String systemCurrency = this.systemConfigRepository.findSystemConfiguration().getSystemCurrency();
		final Double conversionAmount;
		
		if(!systemCurrency.equals(itemCurrency)) {
			MoneyExchange conversion;
			conversion = moneyExchange.computeMoneyExchange(f.getBudget(), systemCurrency);
			conversionAmount = conversion.getTarget().getAmount();	
		}
		else {
			conversionAmount = f.getBudget().getAmount();
		}
		
		final Money newBudget = new Money();
		newBudget.setAmount(conversionAmount);
		newBudget.setCurrency(systemCurrency);
		
		return newBudget;
	}
	

}
