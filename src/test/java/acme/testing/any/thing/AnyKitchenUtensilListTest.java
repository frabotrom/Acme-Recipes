package acme.testing.any.thing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyKitchenUtensilListTest extends TestHarness{
	
	// Test cases -------------------------------------------------------------

			@ParameterizedTest
			@CsvFileSource(resources = "/any/thing/kitchen-utensil-list.csv", encoding = "utf-8", numLinesToSkip = 1)
			@Order(10)
			public void positive(final int recordIndex, final String thingType, final String name,  final String code, final String description,  final String retailPrice, final String info, final String published) {
				
				super.clickOnMenu("Any", "All kitchen utensils published");
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
