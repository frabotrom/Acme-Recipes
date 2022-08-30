package acme.testing.chef.amount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefAmountUpdateTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/amount/update-positive.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndexRecipe, final int recordIndexAmount,final String quantity, final String unit) {
		super.signIn("chef1","chef1");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndexRecipe);
		super.checkFormExists();
		super.clickOnButton("List Quantities");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndexAmount);
		super.checkFormExists();
		
		super.fillInputBoxIn("quantity", quantity);
		super.fillInputBoxIn("unit", unit);
		
		super.clickOnSubmit("Update");
		super.checkNotErrorsExist();
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndexRecipe);
		super.checkFormExists();
		super.clickOnButton("List Quantities");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndexAmount);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("quantity", quantity);
		super.checkInputBoxHasValue("unit", unit);
		
		super.signOut();
	
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/amount/update-negative.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndexRecipe, final int recordIndexAmount,final String quantity, final String unit) {
		super.signIn("chef1","chef1");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndexRecipe);
		super.checkFormExists();
		super.clickOnButton("List Quantities");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndexAmount);
		super.checkFormExists();
		
		super.fillInputBoxIn("quantity", quantity);
		super.fillInputBoxIn("unit", unit);
		
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
	
		super.signOut();
	}
}
