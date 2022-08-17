package acme.features.chef.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipe.Recipe;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefRecipeCreateService implements AbstractCreateService<Chef, Recipe> {
	@Autowired
	protected ChefRecipeRepository repository;
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "heading", "preparationNotes", "code", "description", "moreInfo");

		
		
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "heading", "preparationNotes", "code", "description", "moreInfo");
		
	}

	@Override
	public Recipe instantiate(final Request<Recipe> request) {
		
		assert request != null;
		
		Recipe result;
		
		Chef chef;
		
		chef = this.repository.findChefById(request.getPrincipal().getActiveRoleId());
		
		result = new Recipe();
		result.setChef(chef);
		result.setPublished(false);
		
		return result;
	}

	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if(!errors.hasErrors("code")) {
			Recipe existingRecipe;
			existingRecipe = this.repository.findRecipeByCode(entity.getCode());
			errors.state(request, existingRecipe == null , "code", "inventor.toolkit.form.error.duplicated");
		}
		if(!errors.hasErrors("moreInfo")) {
			boolean isUrl;
			if(!entity.getMoreInfo().isEmpty()) {
			isUrl = (entity.getMoreInfo().startsWith("http") || entity.getMoreInfo().startsWith("www")) && entity.getMoreInfo().contains(".");
			errors.state(request, isUrl, "moreInfo", "inventor.toolkit.form.error.url");
			}
		}
		
		
		if(!errors.hasErrors("heading")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.findSystemConfiguration();
			final String SpamTermsEs = systemConfiguration.getSpamTermsEn();
			final String SpamTermsEn = systemConfiguration.getSpamTermsEs();
			final double SpamThreshold = systemConfiguration.getSpamThreshold();
						
			//res = SpamDetector.spamDetector(entity.getHeading(),SpamTermsEs,SpamTermsEn,SpamThreshold);
			
			//errors.state(request, res, "heading", "alert-message.form.spam");
		}
		
		if(!errors.hasErrors("description")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.findSystemConfiguration();
			final String SpamTermsEs = systemConfiguration.getSpamTermsEn();
			final String SpamTermsEn = systemConfiguration.getSpamTermsEs();
			final double SpamThreshold = systemConfiguration.getSpamThreshold();						
			//res = SpamDetector.spamDetector(entity.getDescription(),SpamTermsEs,SpamTermsEn,SpamThreshold);
			
			//errors.state(request, res, "description", "alert-message.form.spam");
		}
		
		if(!errors.hasErrors("assamblyNotes")) {
			final boolean res;
			final SystemConfiguration systemConfiguration = this.repository.findSystemConfiguration();
			final String SpamTermsEs = systemConfiguration.getSpamTermsEn();
			final String SpamTermsEn = systemConfiguration.getSpamTermsEs();
			final double SpamThreshold = systemConfiguration.getSpamThreshold();
						
			//res = SpamDetector.spamDetector(entity.getPreparationNotes(),SpamTermsEs,SpamTermsEn,SpamThreshold);
			
			//errors.state(request, res, "preparationNotes", "alert-message.form.spam");
		}
		
	}

	@Override
	public void create(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	
	}

}
