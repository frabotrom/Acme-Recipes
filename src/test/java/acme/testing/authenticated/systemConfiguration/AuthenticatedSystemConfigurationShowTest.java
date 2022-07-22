package acme.testing.authenticated.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedSystemConfigurationShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/systemConfiguration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveShowTest(final int recordIndex, final String systemCurrency, final String acceptedCurrencies) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Authenticated", "System Configuration");
		
		super.checkFormExists();
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);

}
	
}
