package acme.testing.chef.amount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefAmountListTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/amount/list.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndexRecipe, final int recordIndexAmount, final String code, final String name, final String price, final String quantity, final String type,
		final String recipeName, final String unit) {
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My recipes");
		
		super.checkListingExists();
		super.clickOnListingRecord(recordIndexRecipe);
		super.checkFormExists();
		super.clickOnButton("List Quantities");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndexAmount, 0, code);
		super.checkColumnHasValue(recordIndexAmount, 1, name);
		super.checkColumnHasValue(recordIndexAmount, 2, type);
		super.checkColumnHasValue(recordIndexAmount, 3, price);
		super.checkColumnHasValue(recordIndexAmount, 4, quantity);

		super.clickOnListingRecord(recordIndexAmount);
		super.checkFormExists();
		super.checkInputBoxHasValue("recipe.heading", recipeName);
		super.checkInputBoxHasValue("thing.name", name);
		super.checkInputBoxHasValue("thing.code", code);
		super.checkInputBoxHasValue("thing.thingType", type);
		super.checkInputBoxHasValue("thing.retailPrice", price);
		super.checkInputBoxHasValue("quantity", quantity);
		super.checkInputBoxHasValue("unit", unit);


	}
	
}
