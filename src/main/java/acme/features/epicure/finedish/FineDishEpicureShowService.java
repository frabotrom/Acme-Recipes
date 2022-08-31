package acme.features.epicure.finedish;

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
import acme.roles.Epicure;

@Service
public class FineDishEpicureShowService implements AbstractShowService<Epicure,FineDish>{
	
	@Autowired
	protected FineDishEpicureRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;

	// AbstractUpdateService<Authenticated, Consumer> interface -----------------

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		final int epicureId = request.getPrincipal().getActiveRoleId();
		final int finedishId = request.getModel().getInteger("id");
		final int finedishEpicureId = this.repository.findOneFineDishById(finedishId).getEpicure().getId();

		return  epicureId == finedishEpicureId; 
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Money newBudget = this.moneyExchangeFineDish(entity);
		model.setAttribute("newBudget", newBudget);
	

		request.unbind(entity, model, "status", "code", "request", "budget", "creationMoment", "startDate", "endDate", "moreInfo", "published");
		model.setAttribute("organisation", entity.getEpicure().getOrganisation());
		model.setAttribute("epicureId", entity.getId());
		model.setAttribute("chefId", entity.getChef().getId());
		model.setAttribute("chefOrganisation", entity.getChef().getOrganisation());
		model.setAttribute("chefAssertion", entity.getChef().getAssertion());
		model.setAttribute("chefFullName", entity.getChef().getIdentity().getFullName());
		model.setAttribute("chefEmail", entity.getChef().getIdentity().getEmail());
		model.setAttribute("chefInfo", entity.getChef().getInfo());
		
		
	}
	
	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return  this.repository.findOneFineDishById(id);
	
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
