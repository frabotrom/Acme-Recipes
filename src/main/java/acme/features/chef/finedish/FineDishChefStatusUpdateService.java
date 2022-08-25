package acme.features.chef.finedish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;
import spamDetector.SpamDetector;
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
		
		if(!errors.hasErrors("code")) {
				final boolean isReportSpam = SpamDetector.isSpam(entity.getCode(), this.repo.systemConfiguration());
				errors.state(request, !isReportSpam, "request", "epicure.fine-dish.form.error.code");
		}
		
		if(!errors.hasErrors("request")) {
				final boolean isReportSpam = SpamDetector.isSpam(entity.getRequest(), this.repo.systemConfiguration());
				errors.state(request, !isReportSpam, "request", "epicure.fine-dish.form.error.request");
			}
		
		if(!errors.hasErrors("moreInfo")) {
			final boolean isReportSpam = SpamDetector.isSpam(entity.getMoreInfo(), this.repo.systemConfiguration());
			errors.state(request, !isReportSpam, "request", "epicure.fine-dish.form.error.moreinfo");
		}
		
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		
		assert request != null;
		assert entity != null;
		
		
		
		this.repo.save(entity);
		
	}

}
