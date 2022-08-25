package acme.features.chef.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipe.Recipe;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;
import spamDetector.SpamDetector;

@Service
public class ChefRecipeUpdateService implements AbstractUpdateService<Chef,Recipe> {
	
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
		result = this.repository.findRecipeById(recipeId);
		return result;
	}

	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("moreInfo") && (!entity.getMoreInfo().isEmpty())) {
			boolean isUrl;
			isUrl = (entity.getMoreInfo().startsWith("http") || entity.getMoreInfo().startsWith("www")) && entity.getMoreInfo().contains(".");
			errors.state(request, isUrl, "moreInfo", "inventor.toolkit.form.error.url");
		
		}
		if(!errors.hasErrors("code")) {
			Recipe existingRecipe;
			existingRecipe = this.repository.findRecipeByCode(entity.getCode());
			
				errors.state(request, existingRecipe == null || existingRecipe.getId() == entity.getId(), "code", "inventor.toolkit.form.error.duplicated");
			
		}
		
		
		if(!errors.hasErrors("heading")) {

			final SystemConfiguration systemConfiguration = this.repository.findSystemConfiguration();
			final Boolean isSpamHeading = SpamDetector.isSpam(entity.getHeading(), systemConfiguration);	
			errors.state(request, !isSpamHeading, "heading", "alert-message.form.spam");

		}
		
		if(!errors.hasErrors("description")) {
			final SystemConfiguration systemConfiguration = this.repository.findSystemConfiguration();
			final Boolean isSpamDescription = SpamDetector.isSpam(entity.getDescription(), systemConfiguration);	
			errors.state(request, !isSpamDescription, "description", "alert-message.form.spam");

		}
		
		if(!errors.hasErrors("preparationNotes")) {
			final SystemConfiguration systemConfiguration = this.repository.findSystemConfiguration();
			final Boolean isSpamPrepNotes = SpamDetector.isSpam(entity.getPreparationNotes(), systemConfiguration);	
			errors.state(request, !isSpamPrepNotes, "preparationNotes", "alert-message.form.spam");
		}
		
	}

	@Override
	public void update(final Request<Recipe> request, final Recipe entity) {
		
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
