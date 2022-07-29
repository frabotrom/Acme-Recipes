package acme.features.chef.finedish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.finedish.FineDish;
import acme.framework.repositories.AbstractRepository;

public interface FineDishChefRepository extends AbstractRepository{
	
	
	@Query("select f from FineDish f where f.id = :id")
	FineDish findFineDishById(int id);
	
	@Query("select f from FineDish f where f.chef.id = :id")
	Collection<FineDish> findFineDishes(Integer id);

}
