package acme.features.chef.recipe;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.amount.Amount;
import acme.entities.recipe.Recipe;
import acme.features.chef.amount.ChefAmountRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefRecipeDeleteService implements AbstractDeleteService<Chef,Recipe> {
	
	@Autowired
	protected ChefRecipeRepository recipeRepository;
	@Autowired
	protected ChefAmountRepository amountRepository;

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		int recipeId;
		Recipe recipe;
		Chef chef;
		recipeId = request.getModel().getInteger("id");
		recipe = this.recipeRepository.findRecipeById(recipeId);
		chef = recipe.getChef();
		return request.isPrincipal(chef) && !recipe.isPublished(); 
	}

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "heading", "preparationNotes", "code", "totalPrice", "description", "moreInfo","published");
		
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
		int recipeId;
		recipeId = request.getModel().getInteger("id");
		result = this.recipeRepository.findRecipeById(recipeId);
		return result;
	}

	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;
		Collection<Amount> amounts;
		amounts = this.recipeRepository.findAllAmountOfRecipe(entity);
		for (final Amount a : amounts) {
			this.amountRepository.delete(a);
		}
		this.recipeRepository.delete(entity);
		
	}

}
