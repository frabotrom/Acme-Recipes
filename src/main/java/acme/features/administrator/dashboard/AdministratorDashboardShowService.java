package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.finedish.Status;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {
	
	@Autowired
	protected AdministratorDashboardRepository repository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigRepository;

	// AbstractShowService<Administrator, AdministratorDashboard> interface ----------------

	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;

		final AdministratorDashboard result;
		
		final Integer totalNumberOfIngredients;
		final Map<String, Double> averageRetailPriceOfIngredientByCurrency = new HashMap<String, Double>();
		final Map<String, Double> deviationRetailPriceOfIngredientByCurrency = new HashMap<String, Double>();
		final Map<String, Double> minimumRetailPriceOfIngredientByCurrency = new HashMap<String, Double>();
		final Map<String, Double> maximumRetailPriceOfIngredientByCurrency = new HashMap<String, Double>();

		final Integer totalNumberOfKitchenUtensils;
		final Map<String, Double> averageRetailPriceOfKitchenUtensilsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> deviationRetailPriceOfKitchenUtensilsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> minimumRetailPriceOfKitchenUtensilsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> maximumRetailPriceOfKitchenUtensilsByCurrency =new HashMap<String, Double>();

		final Map<Status, Integer> totalNumberOfPFineDishesByStatus = new HashMap<Status, Integer>();
		final Map<Pair<Status, String>, Double> averageBudgetFineDishesByStatus = new HashMap<Pair<Status, String>, Double>();
		final Map<Pair<Status, String>, Double> deviationBudgetFineDishesByStatus = new HashMap<Pair<Status, String>, Double>();
		final Map<Pair<Status, String>, Double> minimumBudgetFineDishesByStatus = new HashMap<Pair<Status, String>, Double>();
		final Map<Pair<Status, String>, Double> maximumBudgetFineDishesByStatus = new HashMap<Pair<Status, String>, Double>();		
	
		totalNumberOfIngredients = this.repository.totalNumberOfIngredients();
		totalNumberOfKitchenUtensils = this.repository.totalNumberOfKitchenUtensils();
		
		final List<String> currencies = new ArrayList<>();
		final String acceptedCurrencies = this.systemConfigRepository.findSystemConfiguration().getAcceptedCurrencies();
		final String[] field = acceptedCurrencies.split(",");
		currencies.add(field[0]);
		currencies.add(field[1]);
		currencies.add(field[2]);
		
		
		for(final String currency: currencies) {
			final Double averageRetailPriceOfIngredientByX =  this.repository.averageRetailPriceOfIngredientByCurrency(currency);
			averageRetailPriceOfIngredientByCurrency.put(currency, averageRetailPriceOfIngredientByX);
		}
		
		for(final String currency: currencies) {
			final Double deviationRetailPriceOfIngredientByX = this.repository.deviationRetailPriceOfIngredientByCurrency(currency);
			deviationRetailPriceOfIngredientByCurrency.put(currency, deviationRetailPriceOfIngredientByX);
		}
		
		for(final String currency: currencies) {
			final Double minimumRetailPriceOfIngredientByX = this.repository.minimumRetailPriceOfIngredientByCurrency(currency);
			minimumRetailPriceOfIngredientByCurrency.put(currency, minimumRetailPriceOfIngredientByX);
		}
		
		
		for(final String currency: currencies) {
			final Double maximumRetailPriceOfIngredientByX = this.repository.maximumRetailPriceOfIngredientByCurrency(currency);
			maximumRetailPriceOfIngredientByCurrency.put(currency, maximumRetailPriceOfIngredientByX);
		}
		
		
		for(final String currency: currencies) {
			final Double averageRetailPriceOfKitchenUtensilsByX =  this.repository.averageRetailPriceOfKitchenUtensilsByCurrency(currency);
			averageRetailPriceOfKitchenUtensilsByCurrency.put(currency, averageRetailPriceOfKitchenUtensilsByX);
		}
		
		for(final String currency: currencies) {
			final Double deviationRetailPriceOfKitchenUtensilsByX = this.repository.deviationRetailPriceOfKitchenUtensilsByCurrency(currency);
			deviationRetailPriceOfKitchenUtensilsByCurrency.put(currency, deviationRetailPriceOfKitchenUtensilsByX);
		}
		
		for(final String currency: currencies) {
			final Double minimumRetailPriceOfKitchenUtensilsByX = this.repository.minimumRetailPriceOfKitchenUtensilsByCurrency(currency);
			minimumRetailPriceOfKitchenUtensilsByCurrency.put(currency, minimumRetailPriceOfKitchenUtensilsByX);
		}
		
		for(final String currency: currencies) {
			final Double maximumRetailPriceOfKitchenUtensilsByX = this.repository.maximumRetailPriceOfKitchenUtensilsByCurrency(currency);
			maximumRetailPriceOfKitchenUtensilsByCurrency.put(currency, maximumRetailPriceOfKitchenUtensilsByX);
		}
		
		
	
		final Integer numPatronagesProposed = this.repository.totalNumberOfPFineDishesByStatus(Status.PROPOSED);
		final Integer numPatronagesAccepted = this.repository.totalNumberOfPFineDishesByStatus(Status.ACCEPTED);
		final Integer numPatronagesDenied = this.repository.totalNumberOfPFineDishesByStatus(Status.DENIED);
		totalNumberOfPFineDishesByStatus.put(Status.PROPOSED, numPatronagesProposed);
		totalNumberOfPFineDishesByStatus.put(Status.ACCEPTED, numPatronagesAccepted);
		totalNumberOfPFineDishesByStatus.put(Status.DENIED, numPatronagesDenied);
		
		
		this.repository.averageBudgetPatronagesByStatus(Status.PROPOSED).stream()
		.forEach(x-> averageBudgetFineDishesByStatus.put(
				Pair.of(Status.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.averageBudgetPatronagesByStatus(Status.ACCEPTED).stream()
		.forEach(x-> averageBudgetFineDishesByStatus.put(
				Pair.of(Status.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.averageBudgetPatronagesByStatus(Status.DENIED).stream()
		.forEach(x-> averageBudgetFineDishesByStatus.put(
				Pair.of(Status.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		
		this.repository.deviationBudgetPatronagesByStatus(Status.PROPOSED).stream()
		.forEach(x-> deviationBudgetFineDishesByStatus.put(
				Pair.of(Status.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.deviationBudgetPatronagesByStatus(Status.ACCEPTED).stream()
		.forEach(x-> deviationBudgetFineDishesByStatus.put(
				Pair.of(Status.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.deviationBudgetPatronagesByStatus(Status.DENIED).stream()
		.forEach(x-> deviationBudgetFineDishesByStatus.put(
				Pair.of(Status.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		
		this.repository.minimumBudgetPatronagesByStatus(Status.PROPOSED).stream()
		.forEach(x-> minimumBudgetFineDishesByStatus.put(
				Pair.of(Status.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.minimumBudgetPatronagesByStatus(Status.ACCEPTED).stream()
		.forEach(x-> minimumBudgetFineDishesByStatus.put(
				Pair.of(Status.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.minimumBudgetPatronagesByStatus(Status.DENIED).stream()
		.forEach(x-> minimumBudgetFineDishesByStatus.put(
				Pair.of(Status.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
	
		this.repository.maximumBudgetPatronagesByStatus(Status.PROPOSED).stream()
		.forEach(x-> maximumBudgetFineDishesByStatus.put(
				Pair.of(Status.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.maximumBudgetPatronagesByStatus(Status.ACCEPTED).stream()
		.forEach(x-> maximumBudgetFineDishesByStatus.put(
				Pair.of(Status.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		this.repository.maximumBudgetPatronagesByStatus(Status.DENIED).stream()
		.forEach(x-> maximumBudgetFineDishesByStatus.put(
				Pair.of(Status.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
		
		//Pimpam
		final Double ratioOfThingsWithPimpam;
		final Map<String, Double> averageBudgetOfPimpamsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> deviationBudgetOfPimpamsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> minimumBudgetOfPimpamsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> maximumBudgetOfPimpamsByCurrency =new HashMap<String, Double>();
		
		//IMPORTANTE
		//En el control check se puede pedir que pimpam tenga relación con ingredientes, utensilios o ambas, en este caso lo he 
		//hecho con ingredientes, si no fuera así solo habría que cambiar la siguiente linea y en la que se calcula el ratio
		final Integer totalThings = this.repository.totalNumberOfIngredients();
		final Integer totalPimpams = this.repository.getTotalPimpams();
		ratioOfThingsWithPimpam = (double) totalPimpams / totalThings;
		
		for(final String currency: currencies) {
			final Double averageBudgetOfPimpamsByX =  this.repository.averageBudgetOfPimpamsIByCurrency(currency);
			averageBudgetOfPimpamsByCurrency.put(currency, averageBudgetOfPimpamsByX);
			
			final Double deviationBudgetOfPimpamsByX = this.repository.deviationBudgetOfPimpamsIByCurrency(currency);
			deviationBudgetOfPimpamsByCurrency.put(currency, deviationBudgetOfPimpamsByX);
			
			final Double minimumBudgetOfPimpamsByX = this.repository.minimumBudgetOfPimpamsIByCurrency(currency);
			minimumBudgetOfPimpamsByCurrency.put(currency, minimumBudgetOfPimpamsByX);
			
			final Double maximumBudgetOfPimpamsByX = this.repository.maximumBudgetOfPimpamsIByCurrency(currency);
			maximumBudgetOfPimpamsByCurrency.put(currency, maximumBudgetOfPimpamsByX);
		}
		
		
		
		result = new AdministratorDashboard();
		
		result.setTotalNumberOfIngredients(totalNumberOfIngredients);;
		result.setTotalNumberOfKitchenUtensils(totalNumberOfKitchenUtensils);
		result.setTotalNumberOfPFineDishesByStatus(totalNumberOfPFineDishesByStatus);
		
		result.setAverageRetailPriceOfIngredientByCurrency(averageRetailPriceOfIngredientByCurrency);
		result.setDeviationRetailPriceOfIngredientByCurrency(deviationRetailPriceOfIngredientByCurrency);
		result.setMinimumRetailPriceOfIngredientByCurrency(minimumRetailPriceOfIngredientByCurrency);
		result.setMaximumRetailPriceOfIngredientByCurrency(maximumRetailPriceOfIngredientByCurrency);

		result.setAverageRetailPriceOfKitchenUtensilsByCurrency(averageRetailPriceOfKitchenUtensilsByCurrency);
		result.setDeviationRetailPriceOfKitchenUtensilsByCurrency(deviationRetailPriceOfKitchenUtensilsByCurrency);
		result.setMinimumRetailPriceOfKitchenUtensilsByCurrency(minimumRetailPriceOfKitchenUtensilsByCurrency);
		result.setMaximumRetailPriceOfKitchenUtensilsByCurrency(maximumRetailPriceOfKitchenUtensilsByCurrency);
		
		result.setAverageBudgetFineDishesByStatus(averageBudgetFineDishesByStatus);
		result.setDeviationBudgetFineDishesByStatus(deviationBudgetFineDishesByStatus);
		result.setMinimumBudgetFineDishesByStatus(minimumBudgetFineDishesByStatus);
		result.setMaximumBudgetFineDishesByStatus(maximumBudgetFineDishesByStatus);
		
		//Pimpam
		result.setRatioOfThingsWithPimpam(ratioOfThingsWithPimpam);
		result.setAverageBudgetOfPimpamsByCurrency(averageBudgetOfPimpamsByCurrency);
		result.setDeviationBudgetOfPimpamsByCurrency(deviationBudgetOfPimpamsByCurrency);
		result.setMinimumBudgetOfPimpamsByCurrency(minimumBudgetOfPimpamsByCurrency);
		result.setMaximumBudgetOfPimpamsByCurrency(maximumBudgetOfPimpamsByCurrency);
		
		
		return result;
		
				
	}
	
	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberOfIngredients", "totalNumberOfKitchenUtensils", "totalNumberOfPFineDishesByStatus", 
			"averageRetailPriceOfIngredientByCurrency", "deviationRetailPriceOfIngredientByCurrency",
			"minimumRetailPriceOfIngredientByCurrency", "maximumRetailPriceOfIngredientByCurrency",
			"averageRetailPriceOfKitchenUtensilsByCurrency", "deviationRetailPriceOfKitchenUtensilsByCurrency", "minimumRetailPriceOfKitchenUtensilsByCurrency",
			"maximumRetailPriceOfKitchenUtensilsByCurrency", "averageBudgetFineDishesByStatus", "deviationBudgetFineDishesByStatus",
			"minimumBudgetFineDishesByStatus", "maximumBudgetFineDishesByStatus",
			
			//Pimpam
			"ratioOfThingsWithPimpam", "averageBudgetOfPimpamsByCurrency","deviationBudgetOfPimpamsByCurrency","maximumBudgetOfPimpamsByCurrency","minimumBudgetOfPimpamsByCurrency"
			
			);
	}
	
	
}
