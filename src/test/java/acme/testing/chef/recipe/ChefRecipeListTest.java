package acme.testing.chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefRecipeListTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/list.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndexRecipe, final String code, final String heading, final String description, final String preparationNotes, final String moreInfo, final String totalPrice) {
		
		super.signIn("chef1", "chef1");
		
		// Listado
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndexRecipe, 0, heading);
		super.checkColumnHasValue(recordIndexRecipe, 1, code);
		super.checkColumnHasValue(recordIndexRecipe, 2, totalPrice);
		
		// Formulario
		super.clickOnListingRecord(recordIndexRecipe);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		super.checkInputBoxHasValue("totalPrice", totalPrice);
		super.clickOnButton("Amounts");
		super.checkListingExists();
		
	}
}