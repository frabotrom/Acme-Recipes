package acme.features.chef.pimpam_ingredients;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.pimpam.Pimpam;
import acme.entities.thing.Thing;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefPimpamIngRepository extends AbstractRepository {

	@Query("SELECT t.pimpam from Thing t where t.chef.id=:chefId ")
	Collection<Pimpam> findPimpamsByChefId(Integer chefId);

	@Query("SELECT t from Thing t where t.pimpam.id=:pimpamId")
	Thing findThingByPimpamId(int pimpamId);
	
	@Query("Select t.pimpam from Thing t where t.pimpam.id=:pimpamId")
	Pimpam findPimpamById(int pimpamId);
	
	@Query("SELECT t from Thing t where t.id=:thingId")
	Thing findThingByThingId(int thingId);

	@Query("Select p from Pimpam p where p.code=:newCode")
	Pimpam findPimpamByCode(String newCode);
}
