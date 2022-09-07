package acme.testing.chef.pimpam;

import java.time.LocalDate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class ChefPimpamCreateDeleteITest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/create-positiveI.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void aPositiveCreateTest(final int recordIndexPimpam, final String title, final String description, final String budget,
		final String startDate, final String endDate, final String link) {
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My ingredients");
		super.checkListingExists();
		super.clickOnListingRecord(3);
		super.clickOnButton("Create Pimpam");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("link", link);
		//Generación de código
		final LocalDate now = LocalDate.now();
		final String year = Integer.toString(now.getYear()).substring(2,4);
		String month = Integer.toString(now.getMonthValue());
		String day = Integer.toString(now.getDayOfMonth());
		
		if (month.length()==1) {
			month="0"+month;
		}
		if (day.length()==1) {
			day="0"+day;
		}
		final String generatedCode=year+"-"+month+"-"+day;
		super.fillInputBoxIn("code", generatedCode);

		super.clickOnSubmit("Create");
		super.checkNotErrorsExist();
		
		super.signOut();
		super.signIn("chef1", "chef1");


		super.clickOnMenu("Chef", "My pimpams");
		super.checkListingExists();
		super.clickOnListingRecord(5);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("code", generatedCode);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("link", link);
		
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();

		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/create-negativeI.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeCreateTest(final int recordIndexPimpam, final String title, final String code, final String description, final String budget,
		final String startDate, final String endDate, final String link) {
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My ingredients");
		super.checkListingExists();
		super.clickOnListingRecord(3);
		super.clickOnButton("Create Pimpam");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("endDate", endDate);
		super.fillInputBoxIn("link", link);
		//Generación de código
		final LocalDate now = LocalDate.now();
		final String year = Integer.toString(now.getYear()).substring(2,4);
		String month = Integer.toString(now.getMonthValue());
		String day = Integer.toString(now.getDayOfMonth());
		
		if (month.length()==1) {
			month="0"+month;
		}
		if (day.length()==1) {
			day="0"+day;
		}
		final String generatedCode=year+"-"+month+"-"+day;
		super.fillInputBoxIn("code", generatedCode+code);

		super.clickOnSubmit("Create");
		super.checkErrorsExist();

		super.signOut();

	}

	
}
