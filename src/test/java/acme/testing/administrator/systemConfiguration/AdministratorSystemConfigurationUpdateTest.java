package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfiguration/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String systemCurrency,final String acceptedCurrencies, 
		final String spamTermsEs, final String spamTermsEn, final String spamThreshold) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "System Configuration");
		super.checkFormExists();
		super.fillInputBoxIn("systemCurrency", systemCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("spamTermsEs", spamTermsEs);
		super.fillInputBoxIn("spamTermsEn", spamTermsEn);
		super.fillInputBoxIn("spamThreshold", spamThreshold);
		super.clickOnSubmit("Update");
		super.checkNotErrorsExist();
		super.clickOnMenu("Administrator", "System Configuration");
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("spamTermsEn", spamTermsEn);
		super.checkInputBoxHasValue("spamTermsEs", spamTermsEs);
		super.checkInputBoxHasValue("spamThreshold", spamThreshold);
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfiguration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String systemCurrency,final String acceptedCurrencies, 
		final String spamTermsEs, final String spamTermsEn, final String spamThreshold) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "System Configuration");
		super.checkFormExists();
		super.fillInputBoxIn("systemCurrency", systemCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("spamTermsEs", spamTermsEs);
		super.fillInputBoxIn("spamTermsEn", spamTermsEn);
		super.fillInputBoxIn("spamThreshold", spamThreshold);
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.signIn("chef1", "chef1");
		super.navigate("/administrator/systemConfiguration/show");
		super.checkPanicExists();
		super.signOut();
		super.signIn("epicure1", "epicure1");
		super.navigate("/administrator/systemConfiguration/show");
		super.checkPanicExists();
		super.signOut();
		super.navigate("/administrator/systemConfiguration/show");
		super.checkPanicExists();		
	}
}