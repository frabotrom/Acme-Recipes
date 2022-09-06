package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.finedish.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable{
	
	protected static final long	serialVersionUID	= 1L;

	
	int totalNumberOfIngredients;
	int totalNumberOfKitchenUtensils;
	Map<Status, Integer> totalNumberOfPFineDishesByStatus;
	
	Map<String, Double> averageRetailPriceOfIngredientByCurrency;
	Map<String, Double> deviationRetailPriceOfIngredientByCurrency; 
	Map<String, Double> minimumRetailPriceOfIngredientByCurrency; 
	Map<String, Double> maximumRetailPriceOfIngredientByCurrency;

	
	Map<String, Double> averageRetailPriceOfKitchenUtensilsByCurrency;
	Map<String, Double> deviationRetailPriceOfKitchenUtensilsByCurrency;
	Map<String, Double> minimumRetailPriceOfKitchenUtensilsByCurrency;
	Map<String, Double>maximumRetailPriceOfKitchenUtensilsByCurrency;

	
	Map<Pair<Status, String>, Double> averageBudgetFineDishesByStatus;
	Map<Pair<Status, String>, Double> deviationBudgetFineDishesByStatus;
	Map<Pair<Status, String>, Double> minimumBudgetFineDishesByStatus;
	Map<Pair<Status, String>, Double> maximumBudgetFineDishesByStatus;
	
	//Pimpam
	Double ratioOfThingsWithPimpam;
	Map<String, Double> averageBudgetOfPimpamsByCurrency;
	Map<String, Double> deviationBudgetOfPimpamsByCurrency;
	Map<String, Double> minimumBudgetOfPimpamsByCurrency;
	Map<String, Double> maximumBudgetOfPimpamsByCurrency;

	


}
