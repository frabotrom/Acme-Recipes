package acme.features.epicure.memorandum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.finedish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;


	@Repository
	public interface EpicureMemorandumRepository extends AbstractRepository {
	
		@Query("select a from Memorandum a where a.id = :id")
		Memorandum findOneMemorandumById(int id);
	
		@Query("select a from Memorandum a where a.fineDish.epicure.id = :id")
		Collection<Memorandum> findMemorandumsByEpicureId(int id);

		@Query("select a from FineDish a where a.id = :id")
		FineDish findOneFineDishById(Integer id);
	
		@Query("select p.fineDish from Memorandum p where p.id = :id")
		FineDish findOneFineDishByMemorandumId(int id);
		
		@Query("select a from Memorandum a where a.fineDish.id = :masterId")
		Collection<Memorandum> findMemorandumsByMasterId(int masterId);
		
		@Query("SELECT c FROM SystemConfiguration c")
		SystemConfiguration getSystemConfiguration();
		
	}


	