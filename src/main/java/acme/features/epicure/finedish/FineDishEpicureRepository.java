package acme.features.epicure.finedish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.finedish.FineDish;
import acme.framework.repositories.AbstractRepository;

public interface FineDishEpicureRepository extends AbstractRepository{
	
	@Query("select f from FineDish f where f.epicure.id = :id")
	Collection<FineDish> findFineDishByEpicureId(int id);
	
	@Query("select f from FineDish f where f.id = :id")
	FineDish findOneFineDishById(int id);
}
