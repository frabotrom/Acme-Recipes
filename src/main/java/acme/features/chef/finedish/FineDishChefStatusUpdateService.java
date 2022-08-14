package acme.features.chef.finedish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;
@Service
public class FineDishChefStatusUpdateService implements AbstractUpdateService<Chef,FineDish>{
	
	@Autowired
	FineDishChefRepository repo;
	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		int finedishId;
		FineDish finedish;
		
		finedishId = request.getModel().getInteger("id");
		finedish = this.repo.findFineDishById(finedishId);
		return request.getPrincipal().getActiveRoleId() == finedish.getChef().getId();
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "status", "code", "request", "budget", "creationMoment", "startDate", "endDate", "moreInfo");
		
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "request", "budget", "creationMoment", "startDate", "endDate", "moreInfo");
		}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		
		assert request != null;
		FineDish result;
		int finedishId;
		finedishId = request.getModel().getInteger("id");
		result = this.repo.findFineDishById(finedishId);
		return result;
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		/*if(!errors.hasErrors("code")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getCode(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "code", "alert-message.form.spam");
		}
		
		if(!errors.hasErrors("legalStuff")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getLegalStuff(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "legalStuff", "alert-message.form.spam");
		}
		
		if(!errors.hasErrors("moreInfo")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getStrongSpamTermsEn();
			final String StrongEN = systemConfiguration.getStrongSpamTermsEn();
			final String WeakES = systemConfiguration.getWeakSpamTermsEs();
			final String WeakEN = systemConfiguration.getWeakSpamTermsEn();
			
			final double StrongT = systemConfiguration.getStrongThreshold();
			final double WeakT = systemConfiguration.getWeakThreshold();
						
			res = SpamDetector.spamDetector(entity.getMoreInfo(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "moreInfo", "alert-message.form.spam");
		}
		*/
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		
		assert request != null;
		assert entity != null;
		
		
		
		this.repo.save(entity);
		
	}

}
