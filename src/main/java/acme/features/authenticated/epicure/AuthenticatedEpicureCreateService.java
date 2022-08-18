package acme.features.authenticated.epicure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;
import acme.roles.Epicure;

@Service
public class AuthenticatedEpicureCreateService implements AbstractCreateService<Authenticated, Epicure>{
	
	@Autowired
	protected AuthenticatedEpicureRepository repo;
	
	@Override
	public boolean authorise(final Request<Epicure> request) {
		assert request != null;
		
		boolean result;
		
		result = !request.getPrincipal().hasRole(Chef.class); 

		return result;
	}
	
	@Override
	public void validate(final Request<Epicure> request, final Epicure entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		/*
		if(!errors.hasErrors("company")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repo.systemConfiguration();
			final String StrongES = systemConfiguration.getSpamTermsEn();
			final String StrongEN = systemConfiguration.getSpamTermsEn();
			final String WeakES = systemConfiguration.getSpamTermsEs();
			final String WeakEN = systemConfiguration.getSpamTermsEn();
			
			final double StrongT = systemConfiguration.getSpamThreshold();
			final double WeakT = systemConfiguration.getSpamThreshold();
						
			res = SpamDetector.spamDetector(entity.getCompany(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "company", "alert-message.form.spam");
		}
		
		if(!errors.hasErrors("statement")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.systemConfiguration();
			final String StrongES = systemConfiguration.getSpamTermsEs();
			final String StrongEN = systemConfiguration.getSpamTermsEn();
			final String WeakES = systemConfiguration.getSpamTermsEs();
			final String WeakEN = systemConfiguration.getSpamTermsEn();
			
			final double StrongT = systemConfiguration.getSpamThreshold();
			final double WeakT = systemConfiguration.getSpamThreshold();
						
			res = SpamDetector.spamDetector(entity.getStatement(),StrongES,StrongEN,WeakES,WeakEN,StrongT,WeakT);
			
			errors.state(request, res, "statement", "alert-message.form.spam");
		}
		*/
	}
	
	@Override
	public void bind(final Request<Epicure> request, final Epicure entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "organisation", "assertion", "info");
	}

	@Override
	public void unbind(final Request<Epicure> request, final Epicure entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "organisation", "assertion", "info");
	}
	
	@Override
	public Epicure instantiate(final Request<Epicure> request) {
		assert request != null;

		Epicure result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repo.findOneUserAccountById(userAccountId);

		result = new Epicure();
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void create(final Request<Epicure> request, final Epicure entity) {
		assert request != null;
		assert entity != null;

		this.repo.save(entity);
	}

	@Override
	public void onSuccess(final Request<Epicure> request, final Response<Epicure> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
	

}
