package acme.testing.chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefRecipeUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/update-positive.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndexRecipe, final String heading, final String description, final String preparationNotes, final String moreInfo) {

		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef","My recipes");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndexRecipe);
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("moreInfo", moreInfo);
		
		super.clickOnSubmit("Update");
		super.checkNotErrorsExist();

		super.clickOnMenu("Chef","My recipes");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndexRecipe);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/update-negative.csv", encoding ="utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndexRecipe, final String heading, final String description, final String preparationNotes, final String moreInfo) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef","My recipes");
		super.checkListingExists();
		super.clickOnListingRecord(recordIndexRecipe);
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("moreInfo", moreInfo);
		
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		
		super.signOut();
	}	
		
	
}
