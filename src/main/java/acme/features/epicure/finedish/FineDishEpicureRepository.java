package acme.features.epicure.finedish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.finedish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.entities.systemConfiguration.SystemConfiguration;
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
	
	@Query("SELECT m FROM Memorandum m WHERE m.fineDish.id =:id")
	Collection<Memorandum> findAllmemorandumsOfFineDish(Integer id);
	
	@Query("select f from FineDish f where f.code = :code")
	FineDish findFineDishByCode(String code);
	
	@Query("select systemConfiguration from SystemConfiguration systemConfiguration")
	SystemConfiguration systemConfiguration();
	
	@Query("select c from SystemConfiguration c")
	SystemConfiguration findSystemConfiguration();
}
