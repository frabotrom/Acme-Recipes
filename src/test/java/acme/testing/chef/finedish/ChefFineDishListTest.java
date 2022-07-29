package acme.testing.chef.finedish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefFineDishListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fine-dishes/fine-dishes-list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String code, final String status,  final String budget) {
		
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My fine dishes");
		super.checkListingExists();
		super.sortListing(0, "asc");
				
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, status);
		
		super.signOut();
			
	}

}
