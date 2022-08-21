package acme.testing.chef.thing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefThingCreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/thing/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String thingType, final String name,  final String code, final String description,  final String retailPrice, final String info) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My ingredients");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("thingType", thingType);
		super.clickOnSubmit("Create");
		
		
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
		super.checkInputBoxHasValue("published", "false");
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/thing/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String thingType, final String name,  final String code, final String description,  final String retailPrice, final String info) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My ingredients");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("thingType", thingType);
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/chef/thing/create");
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.navigate("/chef/thing/create");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/thing/create");
		super.checkPanicExists();
		super.signOut();
	}
}
