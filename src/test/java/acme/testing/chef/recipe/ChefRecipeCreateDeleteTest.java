package acme.testing.chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefRecipeCreateDeleteTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/create-positive.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String heading, final String description, final String preparationNotes, final String moreInfo) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My recipes");
		super.clickOnButton("Create Recipe");

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("moreInfo", moreInfo);
		
		super.clickOnSubmit("Create");
		super.checkNotErrorsExist();
		
		super.clickOnMenu("Chef", "My recipes");
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, heading);
		super.checkColumnHasValue(recordIndex, 1, code);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		
		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/create-negative.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String code, final String heading, final String description, final String preparationNotes, final String moreInfo) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My recipes");
		super.clickOnButton("Create Recipe");

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("moreInfo", moreInfo);
		
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
	
	
	}
}
