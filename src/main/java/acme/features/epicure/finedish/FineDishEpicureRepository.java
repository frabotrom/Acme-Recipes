package acme.features.epicure.finedish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.finedish.FineDish;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;
import acme.roles.Epicure;

public interface FineDishEpicureRepository extends AbstractRepository{
	
	@Query("select f from FineDish f where f.epicure.id = :id")
	Collection<FineDish> findFineDishByEpicureId(int id);
	
	@Query("select f from FineDish f where f.id = :id")
	FineDish findOneFineDishById(int id);
	
	@Query("select e from Epicure e WHERE e.id=:id")
	Epicure findEpicureById(int id);
	
	@Query("select c from Chef c WHERE c.id = :id")
    Chef findChefById(int id);
	
	@Query("select c from Chef c")
    Collection<Chef> findChefs();
}
