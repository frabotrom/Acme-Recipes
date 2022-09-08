package acme.features.chef.pimpam_ingredients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.pimpam.Pimpam;
import acme.entities.thing.Thing;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefPimpamIngDeleteService implements AbstractDeleteService<Chef, Pimpam> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefPimpamIngRepository repository;

	// AbstractDeleteService<Employer, Duty> interface -------------------------


	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;
		final int id = request.getModel().getInteger("id");
		final Thing thing = this.repository.findThingByPimpamId(id);
		final int chefId = request.getPrincipal().getActiveRoleId();
		
		return thing.getChef().getId() == chefId && !thing.isPublished();
	}

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		assert request != null;

		Pimpam result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findPimpamById(id);

		return result;
	}

	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "description", "budget", "startDate", "endDate", "link");
		
		//Aqui no se modifica el creationMoment ni code
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

	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Pimpam> request, final Pimpam entity) {
		assert request != null;
		assert entity != null;
		
		Thing thing;
		int pimpamId;
		
		pimpamId = request.getModel().getInteger("id");		
		thing = this.repository.findThingByPimpamId(pimpamId);
		thing.setPimpam(null);

		
		this.repository.delete(entity);
	}

}
