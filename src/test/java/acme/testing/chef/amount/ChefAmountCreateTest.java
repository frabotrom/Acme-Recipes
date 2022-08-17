package acme.testing.chef.amount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefAmountCreateTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/amount/create-positive.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void CreateAmountPositiveTest(final int recordIndex,final int itemRecordIndex, final String quantity, final String unit) {
		
		super.signIn("chef2", "chef2");
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnButton("Add Quantities");
		super.checkFormExists();
		
		super.fillInputBoxIn("quantity", quantity);
		super.fillInputBoxIn("unit", unit);

		super.clickOnSubmit("Create");
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnButton("List Quantities");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(itemRecordIndex);
		
		
		super.checkInputBoxHasValue("quantity", quantity);
		super.checkInputBoxHasValue("unit", unit);
		
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();

		
		
		super.signOut();
		
	}

}
