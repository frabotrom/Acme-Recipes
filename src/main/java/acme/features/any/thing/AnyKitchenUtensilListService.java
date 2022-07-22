package acme.features.any.thing;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.thing.Thing;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyKitchenUtensilListService implements AbstractListService<Any, Thing>{
	@Autowired
	protected AnyThingRepository repository;

	@Override
	public boolean authorise(final Request<Thing> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Thing> findMany(final Request<Thing> request) {
		assert request != null;
		
		Collection<Thing> result;
		result = this.repository.findAllKitchenUtensilsPublished();
		return result;
	}

	@Override
	public void unbind(final Request<Thing> request, final Thing entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"name", "code", "description", "retailPrice");
		
	}
}
