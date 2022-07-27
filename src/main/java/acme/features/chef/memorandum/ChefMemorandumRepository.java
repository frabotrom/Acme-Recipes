package acme.features.chef.memorandum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.finedish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.framework.repositories.AbstractRepository;


	@Repository
	public interface ChefMemorandumRepository extends AbstractRepository {
	
		@Query("select a from Memorandum a where a.id = :id")
		Memorandum findOneMemorandumById(int id);
	
		@Query("select a from Memorandum a where a.fineDish.chef.id = :id")
		Collection<Memorandum> findMemorandumsByEpicureId(int id);

		@Query("select a from FineDish a where a.id = :id")
		FineDish findOneFineDishById(Integer id);
	
		@Query("select p.fineDish from Memorandum p where p.id = :id")
		FineDish findOneFineDishByMemorandumId(int id);
		
		@Query("select a from Memorandum a where a.fineDish.id = :masterId")
		Collection<Memorandum> findMemorandumsByMasterId(int masterId);
	}

