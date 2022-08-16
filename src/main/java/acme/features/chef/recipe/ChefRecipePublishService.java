package acme.features.chef.recipe;

import java.util.Collection;

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
public class ChefRecipePublishService implements AbstractUpdateService<Chef,Recipe> {

	@Autowired
	ChefRecipeRepository repository;
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		int recipeId;
		Recipe recipe;
		recipeId = request.getModel().getInteger("id");
		recipe = this.repository.findRecipeById(recipeId);
	
		return !recipe.isPublished() && request.isPrincipal(recipe.getChef());
	}

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "assamblyNotes", "url","published");
		final Collection<Amount> amounts = this.repository.findAllAmountOfRecipe(entity);
		errors.state(request, !amounts.isEmpty() , "published", "inventor.toolkit.form.error.published");
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "heading", "preparationNotes", "code", "totalPrice", "description", "moreInfo","published");
		
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;
		
		Recipe result;
		final int recipeId = request.getModel().getInteger("id");
		result = this.repository.findRecipeById(recipeId);
		return result;
	}

	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
	}

	@Override
	public void update(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;
		entity.setPublished(true);
		
		this.repository.save(entity);
		
	}
	

}
