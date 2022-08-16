package acme.features.chef.amount;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.amount.Amount;
import acme.entities.recipe.Recipe;
import acme.entities.thing.Thing;
import acme.features.chef.recipe.ChefRecipeRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefAmountCreateService implements AbstractCreateService<Chef,Amount>{
	
	@Autowired
	protected ChefAmountRepository repository;
	@Autowired
	protected ChefRecipeRepository recipeRepository;
	@Override
	public boolean authorise(final Request<Amount> request) {
		assert request != null;
		
		boolean result;
		int recipeId;
		Recipe recipe;
		recipeId = request.getModel().getInteger("id");
		recipe = this.recipeRepository.findRecipeById(recipeId);
		result = recipe != null && !recipe.isPublished() && request.isPrincipal(recipe.getChef());
		return result;
		
	}

	@Override
	public void bind(final Request<Amount> request, final Amount entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		int thingId;
		Thing thing;
		thingId = request.getModel().getInteger("thingId");
		thing = this.repository.findThingById(thingId);
		entity.setThing(thing);
		request.bind(entity, errors, "quantity","unit", "recipe.heading");
		
	}

	@Override
	public void unbind(final Request<Amount> request, final Amount entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
	
		request.unbind(entity, model, "quantity","unit", "thing.name", "recipe.heading");
		model.setAttribute("id", request.getModel().getAttribute("id"));
		model.setAttribute("published", entity.getRecipe().isPublished());
		final Collection<Thing> items ;
		final Collection<Thing> publishedItems = new HashSet<>();
		
		items = this.repository.findAllThings();

		for(final Thing i:items) {
			if(i.isPublished()) {
				final Amount q = this.repository.findAmountByThingIdAndRecipeId(i.getId(), entity.getRecipe().getId());
				if(q == null) {
					publishedItems.add(i);
				}
			}
		}
		model.setAttribute("publishedItems", publishedItems);
		
	}

	@Override
	public Amount instantiate(final Request<Amount> request) {
		assert request != null;
		Amount result;
		Thing thing;
		int recipeId;
		recipeId = request.getModel().getInteger("id");
		Recipe recipe;
		recipe = this.recipeRepository.findRecipeById(recipeId);
		result = new Amount();
		thing = new Thing();
		result.setRecipe(recipe);
		result.setThing(thing);
		
		return result;
	}

	@Override
	public void validate(final Request<Amount> request, final Amount entity, final Errors errors) {
		assert request != null;
		
		assert entity != null;
		
		assert errors != null;
			
		
		
	}

	@Override
	public void create(final Request<Amount> request, final Amount entity) {
		assert request != null;
		assert entity != null;
	
		this.repository.save(entity);
		
	}

}
