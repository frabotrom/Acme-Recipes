package acme.features.chef.amount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.amount.Amount;
import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefAmountUpdateService implements AbstractUpdateService<Chef,Amount>{
	@Autowired
	ChefAmountRepository repository;
	@Override
	public boolean authorise(final Request<Amount> request) {
		assert request != null;
		boolean result;
		Recipe recipe;
		final int amountId = request.getModel().getInteger("id");
		recipe = this.repository.findRecipeByAmountId(amountId);
		result = !recipe.isPublished() && request.isPrincipal(recipe.getChef());
		return result;
	}

	@Override
	public void bind(final Request<Amount> request, final Amount entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "quantity", "unit");
		
	}

	@Override
	public void unbind(final Request<Amount> request, final Amount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
	
		request.unbind(entity, model, "quantity", "unit", "recipe.heading", "thing.name", "thing.code", "thing.info", "thing.description", "thing.retailPrice",
			"thing.thingType", "thing.published");
		model.setAttribute("id", request.getModel().getAttribute("id"));
		model.setAttribute("published", entity.getRecipe().isPublished());

	}
		
	

	@Override
	public Amount findOne(final Request<Amount> request) {
		assert request != null;
		int amountId;
		Amount amount;
		amountId = request.getModel().getInteger("id");
		amount = this.repository.findAmountById(amountId);
		return amount;
	}

	@Override
	public void validate(final Request<Amount> request, final Amount entity, final Errors errors) {
		assert request != null;
		
		assert entity != null;
		
		assert errors != null;
		


	}

	@Override
	public void update(final Request<Amount> request, final Amount entity) {
		assert request != null;
		assert entity != null;
	
		this.repository.save(entity);
		
	}

}
