package acme.features.chef.thing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.thing.Thing;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefThingShowService implements AbstractShowService<Chef, Thing>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefThingRepository repository;

	@Override
	public boolean authorise(final Request<Thing> request) {
		assert request != null;

		final int chefId = request.getPrincipal().getActiveRoleId();
		final int thingId = request.getModel().getInteger("id");
		final int thingChefId = this.repository.findThingById(thingId).getChef().getId();

		return  chefId == thingChefId; 
	}

	@Override
	public void unbind(final Request<Thing> request, final Thing entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "thingType", "name", "code","description","retailPrice", "info", "published");
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

}
