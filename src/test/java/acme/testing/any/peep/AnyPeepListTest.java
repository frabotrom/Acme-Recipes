package acme.testing.any.peep;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.peep.Peep;
import acme.features.any.peep.AnyPeepRepository;
import acme.framework.helpers.FactoryHelper;
import acme.testing.TestHarness;

@SuppressWarnings("deprecation")
public class AnyPeepListTest extends TestHarness{
	
	@Autowired
	protected AnyPeepRepository repository;

	@Override
	@BeforeAll
	public void beforeAll() {
	    super.beforeAll();
	    FactoryHelper.autowire(this);
	    this.patchPeeps();
	}
	
	protected void patchPeeps() {
	    Collection<Peep> peeps;
	    Date moment;
	    final Date deadline = new Date("1901/01/01 00:00");
	    
	    peeps = this.repository.findAPeepsToPatch(deadline);
	    for (final Peep peep : peeps) {
	        moment = this.adjustMoment(peep.getInstantiationMoment());
	        peep.setInstantiationMoment(moment);
	        this.repository.save(peep);
	    }
	}

	private Date adjustMoment(final Date instantiationMoment) {
		Calendar res;
		res = Calendar.getInstance();
		final int deltaDays = instantiationMoment.getDate();
		res.add(Calendar.DATE, -deltaDays);
		res.set(Calendar.MINUTE, 0);
		res.set(Calendar.SECOND, 0);
		return res.getTime();
	}
	

	@ParameterizedTest
	@CsvFileSource(resources = "/any/peep/list-recent.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestComponents(final int recordIndex, final String instantiationMoment, final String heading, final String writer, final String text, 
		 final String email) {
		
		String moment;

        moment = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(this.adjustMoment(new Date(instantiationMoment)));

		super.clickOnMenu("Any","Peeps Recent list");
		super.checkListingExists();
		super.sortListing(0, "desc");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, heading);
		super.checkColumnHasValue(recordIndex, 2, writer);
		super.checkColumnHasValue(recordIndex, 3, text);
		super.checkColumnHasValue(recordIndex, 4, email); 
	}
	
}