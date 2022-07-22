package acme.features.any.thing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.thing.Thing;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyThingShowService implements AbstractShowService<Any, Thing>{
	
	@Autowired
	protected AnyThingRepository repository;

	@Override
	public boolean authorise(final Request<Thing> request) {
		assert request != null;
		
		int id;
		id = request.getModel().getInteger("id");
		Thing thing;
		thing = this.repository.findOneById(id);
		
		if (thing.isPublished()) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public Thing findOne(final Request<Thing> request) {
		assert request != null;

		Thing result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Thing> request, final Thing entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "thingType", "name", "code","description","retailPrice", "info", "published");
		
	}

}
