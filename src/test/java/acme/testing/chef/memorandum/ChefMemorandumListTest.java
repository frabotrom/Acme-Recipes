package acme.testing.chef.memorandum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefMemorandumListTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memorandum/list.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndexFineDish, final int recordIndexMemorandum, final String code, final String status,
		final String budget, final String creationMoment, final String report) {

		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My fine dishes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndexFineDish, 0, code);
		super.checkColumnHasValue(recordIndexFineDish, 1, status);


		super.clickOnListingRecord(recordIndexFineDish);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.clickOnButton("Memorandums");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndexMemorandum, 0, report);


		super.clickOnListingRecord(recordIndexMemorandum);
		super.checkFormExists();
		super.checkInputBoxHasValue("report", report);


		super.clickOnButton("Return");




	}
} 