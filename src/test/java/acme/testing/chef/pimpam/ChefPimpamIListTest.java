
package acme.testing.chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamIListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/listI.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String code, final String budget, final String instantiationMoment, final String startDate, 
		final String endDate, final String link, final String period,
		final String artefactName, final String artefactCode, final String artefactDescription, final String artefactRetailPrice, final String artefactLink, final String artefactType, final String artefactVisible) {

		super.signIn("chef2", "chef2");
		super.clickOnMenu("Chef", "My pimpams");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, budget);

		//Datos del pimpam
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("instantiationMoment", instantiationMoment);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("period", period);

		//Datos del ingrediente
		super.checkInputBoxHasValue("artefactName", artefactName);
		super.checkInputBoxHasValue("artefactCode", artefactCode);
		super.checkInputBoxHasValue("artefactDescription", artefactDescription);
		super.checkInputBoxHasValue("artefactRetailPrice", artefactRetailPrice);
		super.checkInputBoxHasValue("artefactLink", artefactLink);
		super.checkInputBoxHasValue("artefactType", artefactType);
		super.checkInputBoxHasValue("artefactVisible", artefactVisible);

		
		super.signOut();
	}
}
