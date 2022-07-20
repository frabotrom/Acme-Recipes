package acme.features.any.thing;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.thing.Thing;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyThingRepository extends AbstractRepository{

	@Query("SELECT t FROM Thing t WHERE t.thingType = acme.entities.thing.ThingType.INGREDIENT and t.published = 1")
	Collection<Thing> findAllIngredientsPublished();
	
	@Query("SELECT t FROM Thing t WHERE t.thingType = acme.entities.thing.ThingType.KITCHENUTENSIL and t.published = 1")
	Collection<Thing> findAllKitchenUtensilsPublished();
	
	@Query("select t from Thing t where t.id = :id")
	Thing findOneById(int id);
}
