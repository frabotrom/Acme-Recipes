package acme.features.epicure.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.finedish.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureDashboardRepository extends AbstractRepository{
	
	
	
	@Query("SELECT COUNT(p) FROM FineDish p WHERE p.status = :status")
	Integer numFineDishByStatus(Status status);
	
	@Query("SELECT p.budget.currency, ROUND(AVG(p.budget.amount),2),p.status FROM FineDish p WHERE p.status = :status GROUP BY p.budget.currency")
	List<String> averageNumberOfBudgetsByCurrencyAndStatus(Status status);
	
	@Query("SELECT p.budget.currency, ROUND(STDDEV(p.budget.amount),2),p.status FROM FineDish p WHERE p.status = :status GROUP BY p.budget.currency")
	List<String> deviationOfBudgetsByCurrencyAndStatus(Status status);
	
	@Query("SELECT p.budget.currency, MIN(p.budget.amount),p.status FROM FineDish p WHERE p.status = :status GROUP BY p.budget.currency")
	List<String> minBudgetByCurrencyAndStatus(Status status);
	
	@Query("SELECT p.budget.currency, MAX(p.budget.amount),p.status FROM FineDish p WHERE p.status = :status GROUP BY p.budget.currency")
	List<String> maxBudgetByCurrencyAndStatus(Status status);
	
	
//	@Query("select count(p) from FineDish p where (p.status = acme.entities.finedish.Status.PROPOSED AND p.epicure.id = :id)")
//	Double  numberOfProposedFineDishes(int id);
//	
//	@Query("select count(p) from FineDish p where (p.status = acme.entities.finedish.Status.ACCEPTED AND p.epicure.id = :id)")
//	Double  numberOfAcceptedFineDishes(int id);
//	
//	@Query("select count(p) from FineDish p where (p.status = acme.entities.finedish.Status.DENIED AND p.epicure.id = :id)")
//	Double  numberOfDeniedFineDishes(int id);
//	
//	
//	// AVERAGE
//	
//	@Query("select avg(p.budget.amount) from FineDish p where (p.status = acme.entities.finedish.Status.PROPOSED AND p.epicure.id = :id) GROUP BY p.status")
//	Double averageBudgetProposedFineDishes(int id);
//	
//	@Query("select avg(p.budget.amount) from FineDish p where (p.status = acme.entities.finedish.Status.ACCEPTED AND p.epicure.id = :id) GROUP BY p.status")
//	Double averageBudgetAcceptedFineDishes(int id);
//	
//	@Query("select avg(p.budget.amount) from FineDish p where (p.status = acme.entities.finedish.Status.DENIED AND p.epicure.id = :id) GROUP BY p.status")
//	Double averageBudgetDeniedFineDishes(int id);
//	
//	
//	@Query("select stddev(p.budget.amount) from FineDish p where (p.status = acme.entities.finedish.Status.PROPOSED AND p.epicure.id = :id)")
//	Double deviationBudgetProposedFineDishes(int id);
//
//	@Query("select stddev(p.budget.amount) from FineDish p where (p.status = acme.entities.finedish.Status.ACCEPTED AND p.epicure.id = :id)")
//	Double deviationBudgetAcceptedFineDishes(int id);
//	
//	@Query("select stddev(p.budget.amount) from FineDish p where (p.status = acme.entities.finedish.Status.DENIED AND p.epicure.id = :id)")
//	Double deviationBudgetDeniedFineDishes(int id);
//	
//	
//	
//	@Query("select min(p.budget.amount) from FineDish p where (p.status = acme.entities.finedish.Status.PROPOSED AND p.epicure.id = :id)")
//	Double minBudgetProposedFineDishes(int id);
//	
//	@Query("select min(p.budget.amount) from FineDish p where (p.status = acme.entities.finedish.Status.ACCEPTED AND p.epicure.id = :id)")
//	Double minBudgetAcceptedFineDishes(int id);
//	
//	@Query("select min(p.budget.amount) from FineDish p where (p.status = acme.entities.finedish.Status.DENIED AND p.epicure.id = :id)")
//	Double minBudgetDeniedFineDishes(int id);
//	
//
//	
//	@Query("select max(p.budget.amount) from FineDish p where (p.status = acme.entities.finedish.Status.PROPOSED AND p.epicure.id = :id)")
//	Double maxBudgetProposedFineDishes(int id);
//	
//	@Query("select max(p.budget.amount) from FineDish p where (p.status = acme.entities.finedish.Status.ACCEPTED AND p.epicure.id = :id)")
//	Double maxBudgetAcceptedFineDishes(int id);
//	
//	@Query("select max(p.budget.amount) from FineDish p where (p.status = acme.entities.finedish.Status.DENIED AND p.epicure.id = :id)")
//	Double maxBudgetDeniedFineDishes(int id);
	
}