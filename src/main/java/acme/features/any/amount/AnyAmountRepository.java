package acme.features.any.amount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.amount.Amount;
import acme.entities.recipe.Recipe;
import acme.entities.thing.Thing;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyAmountRepository extends AbstractRepository{
	@Query("SELECT a FROM Amount a WHERE a.recipe.id =:recipeId ")
	Collection<Amount> findAmountByRecipeId(int recipeId);
	
	@Query("SELECT a FROM Amount a WHERE a.id =:amountId")
	Amount findAmountById(int amountId);
	
	@Query("SELECT t FROM Thing t WHERE t.name =:thingName")
	Thing findThingByName(String thingName);
	
	@Query("SELECT t FROM Thing t")
	Collection<Thing> findAllThings();
	
	@Query("SELECT a.recipe FROM Amount a WHERE a.id =:amountId")
	Recipe findRecipeByAmountId(int amountId);
	
	@Query("SELECT a FROM Amount a WHERE a.thing.id =:thingId AND a.recipe.id=:recipeId")
	Amount findAmountByThingIdAndRecipeId(int thingId, int recipeId);
	
	@Query("SELECT t FROM Thing t WHERE t.id =:thingId")
	Thing findThingById(int thingId);
}

