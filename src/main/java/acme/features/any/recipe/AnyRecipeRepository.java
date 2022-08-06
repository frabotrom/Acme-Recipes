
package acme.features.any.recipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.amount.Amount;
import acme.entities.recipe.Recipe;
import acme.entities.thing.Thing;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyRecipeRepository extends AbstractRepository {
	
	@Query("SELECT r FROM Recipe r WHERE r.published = true")
	Collection<Recipe> findAllRecipe();
	
	@Query("SELECT a FROM Amount a")
	Collection<Amount> findAllAmount();
	
	@Query("SELECT r FROM Recipe r WHERE r.id =:recipeId")
	Recipe findRecipeById(int recipeId);
	
	@Query("SELECT a FROM Amount a WHERE a.recipe =:recipe")
	Collection<Amount> findAllAmountOfRecipe(Recipe recipe);
	
	@Query("SELECT a.thing.name FROM Amount a WHERE a.recipe =:recipe")
	Collection<String> findAllThingNamesOfRecipe(Recipe recipe);
	
	@Query("SELECT a.thing.code FROM Amount a WHERE a.recipe =:recipe")
	Collection<String> findAllThingCodesOfRecipe(Recipe recipe);
	
	@Query("SELECT a FROM Amount a WHERE a.recipe.id = :recipeId")
	Collection<Amount> findQuantitiesByRecipeId(int recipeId);
	
	@Query("SELECT a.recipe FROM Amount a WHERE a.thing.id =:thingId")
	Collection<Recipe> findRecipeByThingId(int thingId);
	
	@Query("SELECT a.thing FROM Amount a WHERE a.id =:amountId")
	Thing findThingByAmountId(int amountId);
	

}
