package acme.features.chef.recipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.amount.Amount;
import acme.entities.recipe.Recipe;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.entities.thing.Thing;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefRecipeRepository extends AbstractRepository {
	
	@Query("SELECT r FROM Recipe r WHERE r.published = true")
	Collection<Recipe> findAllRecipe();
	
	@Query("SELECT a FROM Amount a")
	Collection<Amount> findAllAmount();
	
	@Query("SELECT r FROM Recipe r WHERE r.id =:recipeId")
	Recipe findRecipeById(int recipeId);
	
	@Query("SELECT a FROM Amount a WHERE a.recipe =:recipe")
	Collection<Amount> findAllAmountOfRecipe(Recipe recipe);
	
	@Query("SELECT a FROM Amount a WHERE a.recipe.id = :recipeId")
	Collection<Amount> findQuantitiesByRecipeId(int recipeId);
	
	@Query("SELECT a.recipe FROM Amount a WHERE a.thing.id =:thingId")
	Collection<Recipe> findRecipeByThingId(int thingId);
	
	@Query("SELECT a.thing FROM Amount a WHERE a.id =:amountId")
	Thing findThingByAmountId(int amountId);
	
	@Query("SELECT r FROM Recipe r WHERE r.chef.id =:chefId")
	Collection<Recipe> findRecipesByChefId(int chefId);
	
	@Query("SELECT c FROM Chef c WHERE c.id =:chefId")
	Chef findChefById(int chefId);
	
	@Query("SELECT r FROM Recipe r WHERE r.code =:code")
	Recipe findRecipeByCode(String code);
	
	@Query("select c from SystemConfiguration c")
	SystemConfiguration findSystemConfiguration();




}
