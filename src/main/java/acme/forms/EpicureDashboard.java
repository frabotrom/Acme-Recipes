
package acme.forms;

import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.finedish.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EpicureDashboard {

	// Attributes -------------------------------------------------------------

	protected Map<Status, Integer> numberOfFineDishesByStatus;

	protected Map<Pair<Status, String>, Double>	averageNumberOfBudgetsByCurrencyAndStatus;

	protected Map<Pair<Status, String>, Double>	deviationOfBudgetsByCurrencyAndStatus;

	protected Map<Pair<Status, String>, Double>		maxBudgetByCurrencyAndStatus;

	protected Map<Pair<Status, String>, Double>		minBudgetByCurrencyAndStatus;
	
	
	
	

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
