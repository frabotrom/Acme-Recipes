package acme.features.epicure.finedish;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Epicure;

@Service
public class FineDishEpicureListService implements AbstractListService<Epicure,FineDish>{
	
	@Autowired
	protected FineDishEpicureRepository repository;
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		

		request.unbind(entity, model, "status", "code", "request", "budget", "creationMoment", "startDate", "endDate", "moreInfo");
	}

	@Override
	public Collection<FineDish> findMany(final Request<FineDish> request) {
		assert request != null;
		final int epicureId = request.getPrincipal().getActiveRoleId();
		
		return this.repository.findFineDishByEpicureId(epicureId);
	}

}
