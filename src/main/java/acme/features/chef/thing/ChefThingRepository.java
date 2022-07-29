package acme.features.chef.thing;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.thing.Thing;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefThingRepository extends AbstractRepository{

	@Query("SELECT c FROM Chef c WHERE c.id = :id")
	Optional<Chef> findChefById(int id);
	
	@Query("SELECT t FROM Thing t WHERE t.thingType = acme.entities.thing.ThingType.INGREDIENT and t.chef.id = :id")
	Collection<Thing> findIngredientsByChef(int id);
	
	@Query("SELECT t FROM Thing t WHERE t.thingType = acme.entities.thing.ThingType.KITCHENUTENSIL and t.chef.id = :id")
	Collection<Thing> findKitchenUtensilsByChef(int id);
	
	@Query("SELECT t FROM Thing t WHERE t.id = :id")
	Thing findThingById(int id);
}
