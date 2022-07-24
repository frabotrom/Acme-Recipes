package acme.features.administrator.dashboard;

import java.util.Collection;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.finedish.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository{
	
	@Query("SELECT count(c) FROM Thing c WHERE c.thingType = acme.entities.thing.ThingType.INGREDIENT")
	Integer totalNumberOfIngredients();
	
	@Query("SELECT avg(i.retailPrice.amount) FROM Thing i WHERE (i.retailPrice.currency = :currency AND i.thingType = acme.entities.thing.ThingType.INGREDIENT)")
	Double  averageRetailPriceOfIngredientByCurrency(String currency);
	
	@Query("SELECT stddev(i.retailPrice.amount) FROM Thing i WHERE (i.retailPrice.currency = :currency AND i.thingType = acme.entities.thing.ThingType.INGREDIENT)")
	Double  deviationRetailPriceOfIngredientByCurrency(String currency);
	
	@Query("SELECT min(i.retailPrice.amount) FROM Thing i WHERE (i.retailPrice.currency = :currency AND i.thingType = acme.entities.thing.ThingType.INGREDIENT)")
	Double  minimumRetailPriceOfIngredientByCurrency(String currency);
	
	@Query("SELECT max(i.retailPrice.amount) FROM Thing i WHERE (i.retailPrice.currency = :currency AND i.thingType = acme.entities.thing.ThingType.INGREDIENT)")
	Double  maximumRetailPriceOfIngredientByCurrency(String currency);
	
	
	///
	
	@Query("SELECT count(c) FROM Thing c WHERE c.thingType = acme.entities.thing.ThingType.KITCHENUTENSIL")
	Integer totalNumberOfKitchenUtensils();
	
	@Query("SELECT avg(i.retailPrice.amount) FROM Thing i WHERE (i.retailPrice.currency = :currency AND i.thingType = acme.entities.thing.ThingType.KITCHENUTENSIL)")
	Double  averageRetailPriceOfKitchenUtensilsByCurrency(String currency);
	
	@Query("SELECT stddev(i.retailPrice.amount) FROM Thing i WHERE (i.retailPrice.currency = :currency AND i.thingType = acme.entities.thing.ThingType.KITCHENUTENSIL)")
	Double  deviationRetailPriceOfKitchenUtensilsByCurrency(String currency);
	
	@Query("SELECT min(i.retailPrice.amount) FROM Thing i WHERE (i.retailPrice.currency = :currency AND i.thingType = acme.entities.thing.ThingType.KITCHENUTENSIL)")
	Double  minimumRetailPriceOfKitchenUtensilsByCurrency(String currency);
	
	@Query("SELECT max(i.retailPrice.amount) FROM Thing i WHERE (i.retailPrice.currency = :currency AND i.thingType = acme.entities.thing.ThingType.KITCHENUTENSIL)")
	Double  maximumRetailPriceOfKitchenUtensilsByCurrency(String currency);
	
	
	//
	
	@Query("SELECT count(f) FROM FineDish f WHERE f.status = :status")
	Integer totalNumberOfPFineDishesByStatus(Status status);
	
	@Query("SELECT f.budget.currency, avg(f.budget.amount) FROM FineDish f WHERE f.status = :status GROUP BY f.budget.currency")
	Collection<Tuple> averageBudgetPatronagesByStatus(Status status);
	
	@Query("SELECT f.budget.currency, stddev(f.budget.amount) FROM FineDish f WHERE f.status = :status GROUP BY f.budget.currency")
	Collection<Tuple> deviationBudgetPatronagesByStatus(Status status);
	
	@Query("SELECT f.budget.currency, min(f.budget.amount) FROM FineDish f WHERE f.status = :status GROUP BY f.budget.currency")
	Collection<Tuple> minimumBudgetPatronagesByStatus(Status status);
	
	@Query("SELECT f.budget.currency, max(f.budget.amount) FROM FineDish f WHERE f.status = :status GROUP BY f.budget.currency")
	Collection<Tuple> maximumBudgetPatronagesByStatus(Status status);

}
