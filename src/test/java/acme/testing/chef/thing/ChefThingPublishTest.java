package acme.testing.chef.thing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class ChefThingPublishTest extends TestHarness{
	
	@BeforeAll
	public void createTestThing() {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My ingredients");
		super.clickOnButton("Create");
		super.fillInputBoxIn("name", "A - Ejemplo para publicar");
		super.fillInputBoxIn("code", "AA:AAA-764");
		super.fillInputBoxIn("description", "Esto es un ejemplo para publicar");
		super.fillInputBoxIn("retailPrice", "EUR 8.5");
		super.fillInputBoxIn("info", "https://en.wikipedia.org/wiki/Example");
		super.fillInputBoxIn("thingType", "INGREDIENT");
		super.clickOnSubmit("Create");
		
		super.signOut();
	}
	
	@Test
	@Order(10)
	public void positiveTest() {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My ingredients");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);

		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkInputBoxHasValue("published", "true");
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void negativeTest() {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My ingredients");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);

		super.checkNotButtonExists("Publish");
		
		super.signOut();
	}
}
