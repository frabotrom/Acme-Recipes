package acme.entities.pimpam;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pimpam extends AbstractEntity {
	
	// Serialisation identifier --------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes ----------------------------------------------------
	
	@NotBlank
	@Pattern(regexp = "^\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")
	// pattern “PATTERN”, where “yy”, “mm”, and “dd” refer to the year, month, and day when the PIMPAM is created
	protected String 					code;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date 						instantiationMoment;
	
	
	@NotBlank
	@Length(min = 1, max = 100)
	protected String 					title;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String 					description;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date						startDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date						endDate;
	
	@NotNull
	@Valid
	protected Money						budget;
	
	@URL
	protected String 					link;
	
	// Derived attributes -----------------------------------------------------
	@Transient
	public Integer period() {
		final long result = this.endDate.getTime() - this.startDate.getTime();
		final long resultDays = result/(1000*60*60*24);
		return (int) resultDays;
	}

	
	
}
