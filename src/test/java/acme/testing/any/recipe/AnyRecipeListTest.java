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
		final String name, final String retailPrice, final String thingCode, final String type, final String quantity, final String unit) {
		
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
		
		// Listado de things
		super.clickOnButton("Amounts");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndexThing);
		super.sortListing(1, "asc");
		super.checkInputBoxHasValue("thing.code", thingCode);
		super.checkInputBoxHasValue("thing.name", name);
		super.checkInputBoxHasValue("thing.thingType", type);
		super.checkInputBoxHasValue("newRetailPrice", retailPrice);
		super.checkInputBoxHasValue("quantity", quantity);
		super.checkInputBoxHasValue("unit", unit);
		
		super.clickOnButton("Return");
		
		
		
		
	}
}
