package acme.testing.any.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyRecipeListTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/any/recipe/list.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndexRecipe, final int recordIndexThing, final String code, final String heading, final String description, final String preparationNotes, final String moreInfo, final String totalPrice,
		final String name, final String retailPrice, final String thingCode) {
		
		// Listado de recipes
		super.clickOnMenu("Any", "All published recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndexRecipe, 0, heading);
		super.checkColumnHasValue(recordIndexRecipe, 1, code);
		super.checkColumnHasValue(recordIndexRecipe, 2, totalPrice);
		
		// Formulario de recipes
		super.clickOnListingRecord(recordIndexRecipe);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		super.checkInputBoxHasValue("totalPrice", totalPrice);
		super.clickOnButton("Things");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndexThing, 0, name);
		
		// Listado de things
		super.clickOnListingRecord(recordIndexThing);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", thingCode);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		
		super.clickOnButton("Return");
		
		
		
		
	}
}
