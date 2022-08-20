package acme.features.epicure.finedish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
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

}
