package acme.testing.authenticated.bulletin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.bulletin.Bulletin;
import acme.features.authenticated.bulletin.AuthenticatedBulletinRepository;
import acme.framework.helpers.FactoryHelper;
import acme.testing.TestHarness;

@SuppressWarnings("deprecation")
public class AuthenticatedBulletinListTest extends TestHarness {

	@Autowired
	protected AuthenticatedBulletinRepository repository;
	
	
	@Override
	@BeforeAll
	public void beforeAll() {
	    super.beforeAll();
	    FactoryHelper.autowire(this);
	    this.patchBulletins();
	}

	protected void patchBulletins() {
	    Collection<Bulletin> bulletins;
	    Date moment;
	    final Date deadline = new Date("1901/01/01 00:00");

	    bulletins = this.repository.findABulletinToPatch(deadline);
	    for (final Bulletin bulletin : bulletins) {
	        moment = this.adjustMoment(bulletin.getInstantiationMoment());
	        bulletin.setInstantiationMoment(moment);
	        this.repository.save(bulletin);
	    }
	}

	private Date adjustMoment(final Date instantiationMoment) {
		Calendar res;
		res = Calendar.getInstance();
		final int deltaDays = instantiationMoment.getDate();
		res.add(Calendar.DATE, -deltaDays);
		return res.getTime();
	}

	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/bulletin/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest (final int recordIndex, final String instantiationMoment, final String heading,  final String text, final String critical,  final String link) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Authenticated", "Bulletins");
		super.checkListingExists();
		super.sortListing(0, "desc");
		
		final String moment = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(this.adjustMoment(new Date(instantiationMoment)));
		
		super.checkColumnHasValue(recordIndex, 1, heading);
		super.checkColumnHasValue(recordIndex, 0, moment);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("text", text);
		super.checkInputBoxHasValue("instantiationMoment", moment);
		super.checkInputBoxHasValue("critical", critical);
		super.checkInputBoxHasValue("link", link);
		
	}
}
