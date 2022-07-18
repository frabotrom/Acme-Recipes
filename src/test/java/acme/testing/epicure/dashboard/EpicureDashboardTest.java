package acme.testing.epicure.dashboard;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureDashboardTest extends TestHarness{
	

	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/epicure-dashboard/epicure-dashboard.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10) 
	public void positiveEpicureDashboardTest(final int recordIndex) {
		
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure","Dashboard");
		super.checkFormExists();
		super.signOut();
		
		
	}

}
