package acme.features.chef.finedish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class FineDishChefShowService implements AbstractShowService<Chef,FineDish>{
	
	@Autowired
	protected FineDishChefRepository repository;

	//@Autowired
	//protected AuthenticatedSystemConfigurationRepository systemConfigRepository;
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final FineDish patronage = this.repository.findFineDishById(id);
		final int patronId = request.getPrincipal().getActiveRoleId();
		
		return patronage.getChef().getId() == patronId;
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final FineDish f = this.repository.findFineDishById(id);
		
		
		
		return f;	
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "request", "budget", "creationMoment","startDate","endDate","moreInfo","chef.organisation","published");
		model.setAttribute("chefId", entity.getChef().getId());
		model.setAttribute("epicureId", entity.getEpicure().getId());
		model.setAttribute("epicureOrganisation", entity.getEpicure().getOrganisation());
		model.setAttribute("epicureAssertion", entity.getEpicure().getAssertion());
		model.setAttribute("epicureFullName", entity.getEpicure().getIdentity().getFullName());
		model.setAttribute("epicureEmail", entity.getEpicure().getIdentity().getEmail());
		model.setAttribute("epicureInfo", entity.getEpicure().getInfo());
		

		
	}

}
