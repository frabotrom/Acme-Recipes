package acme.features.administrator.bulletin;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.Bulletin;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorBulletinCreateService implements AbstractCreateService<Administrator, Bulletin>{

	@Autowired
	protected AdministratorBulletinRepository repository;
	

	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	
		request.bind(entity, errors, "heading", "text", "critical", "link");
	}
	
	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
	
		request.unbind(entity, model, "heading", "text", "critical", "link");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
	}
	
	@Override
	public Bulletin instantiate(final Request<Bulletin> request) {
		assert request != null;
	
		final Bulletin result;
		Date instantiationMoment;
	
		result = new Bulletin();
		instantiationMoment =  Calendar.getInstance().getTime();
		result.setInstantiationMoment(instantiationMoment);
	
		return result;
	}
	
	@Override
	public void validate(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	
		boolean confirmation;
		
		
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "administrator.announcement.confirmation.error");
		
		if(!errors.hasErrors("heading")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String spamTuplesEn = systemConfiguration.getSpamTermsEn();
			final String spamTuplesEs = systemConfiguration.getSpamTermsEs();
			final double spamThreshold = systemConfiguration.getSpamThreshold();
						
			//res = SpamDetector.spamDetector(entity.getTitle(),spamTuplesEn,spamTuplesEs,spamThreshold);
			//errors.state(request, res, "heading", "alert-message.form.spam");
		}
		
		if(!errors.hasErrors("text")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String spamTuplesEn = systemConfiguration.getSpamTermsEn();
			final String spamTuplesEs = systemConfiguration.getSpamTermsEs();
			final double spamThreshold = systemConfiguration.getSpamThreshold();
			
			
			//res = SpamDetector.spamDetector(entity.getTitle(),spamTuplesEn,spamTuplesEs,spamThreshold);
			//errors.state(request, res, "body", "alert-message.form.spam");
		}
		
	}
	
	@Override
	public void create(final Request<Bulletin> request, final Bulletin entity) {
		assert request != null;
		assert entity != null;
	
		Date instantiationMoment;
	
		instantiationMoment = Calendar.getInstance().getTime();
		entity.setInstantiationMoment(instantiationMoment);
		this.repository.save(entity);
	}

}