package acme.features.chef.thing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.thing.Thing;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefThingDeleteService implements AbstractDeleteService<Chef, Thing>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefThingRepository repository;

	// AbstractDeleteService<Chef, Thing> interface ---------------------------

	@Override
	public boolean authorise(final Request<Thing> request) {
		assert request != null;

		boolean result;
		int thingId;
		final Thing thing;
		final Chef chef;
			
		thingId = request.getModel().getInteger("id");
		thing = this.repository.findThingById(thingId);
		chef = thing.getChef();
		result = !thing.isPublished() && request.isPrincipal(chef);

		return result;
	}
		
	@Override
	public Thing findOne(final Request<Thing> request) {
		assert request != null;

		Thing result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findThingById(id);

		return result;
	}

	@Override
	public void bind(final Request<Thing> request, final Thing entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}
		
	@Override
	public void validate(final Request<Thing> request, final Thing entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void unbind(final Request<Thing> request, final Thing entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	@Override
	public void delete(final Request<Thing> request, final Thing entity) {
		assert request != null;
		assert entity != null;
			
		this.repository.delete(entity);
	}
}
