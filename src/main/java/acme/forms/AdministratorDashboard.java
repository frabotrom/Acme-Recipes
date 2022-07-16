package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

public class AdministratorDashboard implements Serializable{
	
	protected static final long	serialVersionUID	= 1L;

	
	int totalNumberOfIngredients;
	int totalNumberOfKitchenUtensils;
	int totalNumberOfPFineDishesByStatus;
	
	Map<Pair<String, String>, Double> averageRetailPriceOfIngredientByCurrency;
	Map<Pair<String, String>, Double> deviationRetailPriceOfIngredientByCurrency; 
	Map<Pair<String, String>, Double> minimumRetailPriceOfIngredientByCurrency; 
	Map<Pair<String, String>, Double> maximumRetailPriceOfIngredientByCurrency;

	
	Map<String, Double> averageRetailPriceOfKitchenUtensilsByCurrency;
	Map<String, Double> deviationRetailPriceOfKitchenUtensilsByCurrency;
	Map<String, Double> minimumRetailPriceOfKitchenUtensilsByCurrency;
	Map<String, Double>maximumRetailPriceOfKitchenUtensilsByCurrency;

	
	Map<String, Double> averageBudgetFineDishesByStatus;
	Map<String, Double> deviationBudgetFineDishesByStatus;
	Map<String, Double> minimumBudgetFineDishesByStatus;
	Map<String, Double> maximumBudgetFineDishesByStatus;

}
