package acme.testing.chef.thing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefThingUpdateTest extends TestHarness{
	
	@BeforeAll
	public void createTestThing() {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My ingredients");
		super.clickOnButton("Create");
		super.fillInputBoxIn("name", "A - Ejemplo para actualizar");
		super.fillInputBoxIn("code", "AA:AAA-764");
		super.fillInputBoxIn("description", "Esto es un ejemplo para actualizar");
		super.fillInputBoxIn("retailPrice", "EUR 8.5");
		super.fillInputBoxIn("info", "https://en.wikipedia.org/wiki/Example");
		super.fillInputBoxIn("thingType", "INGREDIENT");
		super.clickOnSubmit("Create");
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/thing/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String thingType, final String name,  final String code, final String description,  final String retailPrice, final String info) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My ingredients");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("thingType", thingType);
		super.clickOnSubmit("Update");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(0, 0, name);
		super.checkColumnHasValue(0, 1, code);
		super.checkColumnHasValue(0, 2, description);
		super.checkColumnHasValue(0, 3, retailPrice);
		
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("thingType", thingType);
		super.checkInputBoxHasValue("published", "false");
		
		super.signOut();
	}

}
