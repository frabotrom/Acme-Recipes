package acme.features.chef.finedish;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class FineDishChefListService implements AbstractListService<Chef,FineDish>{
	
	@Autowired
	protected FineDishChefRepository repository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<FineDish> findMany(final Request<FineDish> request) {
		assert request != null;
		final Integer id = request.getPrincipal().getActiveRoleId();
		Collection<FineDish> result;
		result = this.repository.findFineDishes(id);
		return result;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		

		if(entity.isPublished()) {
			model.setAttribute("published", "\u2714");
		} else if(!entity.isPublished()) {
			model.setAttribute("published", "\u274C");
		}
		
		request.unbind(entity, model, "status", "code", "budget");
		model.setAttribute("epicure", entity.getEpicure().getUserAccount().getUsername());
	}
	

}
