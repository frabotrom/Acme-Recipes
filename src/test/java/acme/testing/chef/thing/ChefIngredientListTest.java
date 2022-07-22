package acme.testing.chef.thing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefIngredientListTest extends TestHarness{
	
	// Test cases -------------------------------------------------------------

		@ParameterizedTest
		@CsvFileSource(resources = "/chef/thing/ingredient-list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positive(final int recordIndex, final String thingType, final String name,  final String code, final String description,  final String retailPrice, final String info, final String published) {
			
			super.signIn("chef1", "chef1");
			super.clickOnMenu("Chef", "My ingredients");
			super.checkListingExists();
			super.sortListing(0, "asc");
					
			super.checkColumnHasValue(recordIndex, 0, name);
			super.checkColumnHasValue(recordIndex, 1, code);
			super.checkColumnHasValue(recordIndex, 2, description);
			super.checkColumnHasValue(recordIndex, 3, retailPrice);
					
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.checkInputBoxHasValue("name", name);
			super.checkInputBoxHasValue("code", code);
			super.checkInputBoxHasValue("description", description);
			super.checkInputBoxHasValue("retailPrice", retailPrice);
			super.checkInputBoxHasValue("info", info);
			super.checkInputBoxHasValue("thingType", thingType);
			super.checkInputBoxHasValue("published", published);
				
		}

}
