package acme.features.chef.pimpam_ingredients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.pimpam.Pimpam;
import acme.entities.thing.Thing;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefPimpamIngShowService implements AbstractShowService<Chef, Pimpam>{

	// Internal state -------------------------------------------------------------------

	@Autowired
	protected ChefPimpamIngRepository repository;

	// AbstractShowService<Chef, Pimpam> interface ---------------------------

	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;

		boolean result;
		final Thing thing;
		int pimpamId;

		pimpamId = request.getModel().getInteger("id");
		thing = this.repository.findThingByPimpamId(pimpamId);
		result = request.getPrincipal().getActiveRoleId() == thing.getChef().getId();

		return result;
	}

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		assert request != null;

		final Pimpam pimpam;
		final int pimpamId;

		pimpamId = request.getModel().getInteger("id");
		pimpam = this.repository.findPimpamById(pimpamId);

		return pimpam;
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "budget", "instantiationMoment", "startDate", "endDate", "link");
		model.setAttribute("period", entity.period());
		
		final Thing thing;
		int pimpamId;

		pimpamId = entity.getId();
		thing = this.repository.findThingByPimpamId(pimpamId);
		
		model.setAttribute("artefactName", thing.getName());
		model.setAttribute("artefactCode", thing.getCode());
		model.setAttribute("artefactDescription", thing.getDescription());
		model.setAttribute("artefactRetailPrice", thing.getRetailPrice());
		model.setAttribute("artefactLink", thing.getInfo());
		model.setAttribute("artefactType", thing.getThingType());
		model.setAttribute("artefactVisible", thing.isPublished());
	}

}
