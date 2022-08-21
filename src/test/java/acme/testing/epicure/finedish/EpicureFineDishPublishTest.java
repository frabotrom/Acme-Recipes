package acme.testing.epicure.finedish;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class EpicureFineDishPublishTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code) {
		super.signIn("epicure1", "epicure1");

		super.clickOnMenu("Epicure", "My fine dishes");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/publish-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String code, final String request) {
		super.signIn("epicure1", "epicure1");

		super.clickOnMenu("Epicure", "My fine dishes");
		super.checkListingExists();
		super.sortListing(0, "desc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		
		super.fillInputBoxIn("request", request);
		
		super.clickOnSubmit("Publish");
		super.checkErrorsExist();

		super.signOut();
	}

}
