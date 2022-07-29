package acme.features.chef.thing;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.thing.Thing;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefKitchenUtensilListService implements AbstractListService<Chef, Thing>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefThingRepository repository;

	@Override
	public boolean authorise(final Request<Thing> request) {
		assert request != null;
		final int userId = request.getPrincipal().getActiveRoleId();
		
		return this.repository.findChefById(userId).isPresent();
	}

	@Override
	public void unbind(final Request<Thing> request, final Thing entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
	

		request.unbind(entity, model, "name", "code", "description", "retailPrice");
	}

	@Override
	public Collection<Thing> findMany(final Request<Thing> request) {
		assert request != null;
			
		final int chefId = request.getPrincipal().getActiveRoleId();
			
		return this.repository.findKitchenUtensilsByChef(chefId);
	}

}
