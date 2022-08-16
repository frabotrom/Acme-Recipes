package acme.features.epicure.memorandum;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamDetector;
import acme.entities.finedish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandumCreateService implements AbstractCreateService<Epicure, Memorandum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureMemorandumRepository repository;

	// AbstractCreateService<Epicure, Memorandum> interface --------------


	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;

		boolean result;
		int masterId;
		FineDish fineDish;

		masterId = request.getModel().getInteger("masterId");
		fineDish = this.repository.findOneFineDishById(masterId);
		result = (fineDish != null && (!fineDish.isPublished() || request.isPrincipal(fineDish.getEpicure())));
		

		return result;
	}
	

	@Override
	public void bind(final Request<Memorandum> request, final Memorandum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "serialNumber", "instantiationMoment","report","info");

	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "serialNumber", "instantiationMoment","report","info");
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
		model.setAttribute("published", entity.getFineDish().isPublished());

		
	}

	@Override
	public Memorandum instantiate(final Request<Memorandum> request) {
		assert request != null;

		Memorandum result;
		FineDish fineDish;
		int masterId;
		Date moment;
		
		masterId = request.getModel().getInteger("masterId");
		fineDish = this.repository.findOneFineDishById(masterId);
		
		moment = new Date(System.currentTimeMillis() - 1);

		
		final Random num = new SecureRandom();
		
		result = new Memorandum();

		final StringBuilder bld = new StringBuilder();

		for(int i = 0; i < 4; i++) {
		
			final Integer c = num.nextInt(10);
			final String aux = c.toString();
			 bld.append(aux);

		}
		final String res = bld.toString(); 
		result.setSerialNumber(res);

		result.setInstantiationMoment(moment);
		result.setFineDish(fineDish);
		return result;
		
	}

	@Override
	public void validate(final Request<Memorandum> request, final Memorandum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
		if(!errors.hasErrors("report")) {
			final boolean isReportSpam = SpamDetector.isSpam(entity.getReport(), this.repository.getSystemConfiguration());
			errors.state(request, !isReportSpam, "report", "Report contiene spam");
		}
	}

	@Override
	public void create(final Request<Memorandum> request, final Memorandum entity) {
		assert request != null;
		assert entity != null;

		
		this.repository.save(entity);
	}

}