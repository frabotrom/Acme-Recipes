package acme.features.chef.amount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.amount.Amount;
import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefAmountDeleteService implements AbstractDeleteService<Chef,Amount>{
	@Autowired
	ChefAmountRepository repository;
	@Override
	public boolean authorise(final Request<Amount> request) {
		assert request != null;
		boolean result;
		int amountId;
		amountId = request.getModel().getInteger("id");
		final Recipe recipe;
		recipe = this.repository.findRecipeByAmountId(amountId);
		result = !recipe.isPublished() && request.isPrincipal(recipe.getChef());
		return result;
	}

	@Override
	public void bind(final Request<Amount> request, final Amount entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "quantity", "unit", "thing.name", "recipe.heading");
	}

	@Override
	public void unbind(final Request<Amount> request, final Amount entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
	
		request.unbind(entity, model, "quantity", "unit", "thing.name", "recipe.heading","recipe.published");
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
	public void delete(final Request<Amount> request, final Amount entity) {
		assert request !=null;
		assert entity != null;
		this.repository.delete(entity);
		
	}

}
