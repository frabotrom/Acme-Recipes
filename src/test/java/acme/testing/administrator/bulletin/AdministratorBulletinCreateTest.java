package acme.testing.administrator.bulletin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorBulletinCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/bulletin/create-positive.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(5)
	public void positiveTest(final int recordIndex, final String heading, final String text, 
		final String link, final String critical, final String confirmation) {

		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Create Bulletin");

		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("critical", critical);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create Bulletin");
		super.checkNotErrorsExist();


		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/bulletin/create-negative.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String heading, final String text, 
		final String link, final String critical, final String confirmation) {

		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Create Bulletin");

		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("critical", critical);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create Bulletin");

		super.checkErrorsExist();

		super.signOut();
	}
}
