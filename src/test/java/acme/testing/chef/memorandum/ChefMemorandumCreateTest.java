package acme.testing.chef.memorandum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefMemorandumCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memorandum/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePatronageReportTest(final int recordIndex, final String report, final String info) {
		super.signIn("chef2", "chef2");
		super.clickOnMenu("Chef", "My fine dishes");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.clickOnButton("Memorandums");
		super.clickOnButton("Create");

		super.fillInputBoxIn("report", report);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, report);

		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memorandum/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeChirpTest(final int recordIndex, final String report, final String info) {

		super.signIn("chef2", "chef2");
		super.clickOnMenu("Chef", "My fine dishes");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.clickOnButton("Memorandums");
		super.clickOnButton("Create");

		super.fillInputBoxIn("report", report);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}

	@Test
	@Order(30)
	public void hackingTest() {

	}

}