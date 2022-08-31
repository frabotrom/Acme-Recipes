package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfiguration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveShowTest(final int recordIndex, final String systemCurrency, final String acceptedCurrencies, final String spamTermsEs, final String spamTermsEn, 
		 final String spamThreshold) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "System Configuration");
		
		super.checkFormExists();
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("spamTermsEs", spamTermsEs);
		super.checkInputBoxHasValue("spamTermsEn", spamTermsEn);
		super.checkInputBoxHasValue("spamThreshold", spamThreshold);

		super.signOut();
		
}
}
