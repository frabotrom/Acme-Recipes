package acme.features.chef.finedish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.entities.moneyExchange.MoneyExchange;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class FineDishChefShowService implements AbstractShowService<Chef,FineDish>{
	
	@Autowired
	protected FineDishChefRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;

	//@Autowired
	//protected AuthenticatedSystemConfigurationRepository systemConfigRepository;
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final FineDish finedish = this.repository.findFineDishById(id);
		final int chefId = request.getPrincipal().getActiveRoleId();
		
		return finedish.getChef().getId() == chefId;
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		return this.repository.findFineDishById(id);
			
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Money newBudget = this.moneyExchangeFineDish(entity);
		model.setAttribute("newBudget", newBudget);
		
		request.unbind(entity, model, "status", "code", "request", "budget", "creationMoment","startDate","endDate","moreInfo","chef.organisation","published");
		model.setAttribute("chefId", entity.getChef().getId());
		model.setAttribute("epicureId", entity.getEpicure().getId());
		model.setAttribute("epicureOrganisation", entity.getEpicure().getOrganisation());
		model.setAttribute("epicureAssertion", entity.getEpicure().getAssertion());
		model.setAttribute("epicureFullName", entity.getEpicure().getIdentity().getFullName());
		model.setAttribute("epicureEmail", entity.getEpicure().getIdentity().getEmail());
		model.setAttribute("epicureInfo", entity.getEpicure().getInfo());
		

		
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
