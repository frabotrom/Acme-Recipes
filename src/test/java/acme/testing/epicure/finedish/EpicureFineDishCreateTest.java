package acme.testing.epicure.finedish;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class EpicureFineDishCreateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/create-finedish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveFineDishCreate(final int recordIndex, final String code, final String request, final String budget, final String startDate, final String endDate, 
		final String moreInfo) {
		
		super.signIn("epicure2", "epicure2");
		
		super.clickOnMenu("Epicure", "My fine dishes");
		super.checkListingExists();
		super.clickOnButton("Create");

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate",startDate );
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("moreInfo", moreInfo);
	
		super.clickOnSubmit("Create");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 2, budget);
	
		super.signOut();
		
	}
	
	@ParameterizedTest	
	@CsvFileSource(resources = "/epicure/fine-dish/create-finedish-nefative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String code, final String request, final String budget, final String startDate, final String endDate, 
		final String moreInfo) {

		super.signIn("epicure2", "epicure2");
		
		super.clickOnMenu("Epicure", "My fine dishes");
		super.checkListingExists();
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate",startDate );
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/epicure/fine-dish/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/epicure/fine-dish/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("chef1", "chef1");
		super.navigate("/epicure/fine-dish/create");
		super.checkPanicExists();
		super.signOut();
	}

}
