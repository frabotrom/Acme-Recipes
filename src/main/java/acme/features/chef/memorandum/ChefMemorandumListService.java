package acme.features.chef.memorandum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefMemorandumListService implements AbstractListService<Chef, Memorandum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefMemorandumRepository repository;

	// AbstractListService<Chef, Memorandum> interface --------------


	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;

		boolean result;
		int masterId;
		FineDish fineDish;

		masterId = request.getModel().getInteger("masterId");
		fineDish = this.repository.findOneFineDishById(masterId);
		result = (fineDish != null && (!fineDish.isPublished() || request.isPrincipal(fineDish.getChef())));

		return result;
	}

	@Override
	public Collection<Memorandum> findMany(final Request<Memorandum> request) {
		assert request != null;

		Collection<Memorandum> result;
		int masterId;
		masterId = request.getModel().getInteger("masterId");

		result = this.repository.findMemorandumsByMasterId(masterId);


		return result;
	}
	
	
	@Override
	public void unbind(final Request<Memorandum> request, final Collection<Memorandum> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;

		int masterId;
		FineDish fineDish;
		final boolean showCreate;

		masterId = request.getModel().getInteger("masterId");
		fineDish = this.repository.findOneFineDishById(masterId);
		showCreate = fineDish.isPublished() && request.isPrincipal(fineDish.getChef());

		model.setAttribute("masterId", masterId);
		model.setAttribute("showCreate", showCreate);
		
		
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "report");
	}

}