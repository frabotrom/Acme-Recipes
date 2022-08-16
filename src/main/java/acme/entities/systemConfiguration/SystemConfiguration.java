package acme.entities.systemConfiguration;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity {

	// ------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	// ------------------------------------------------------------------
	
	
	@NotBlank
	@Pattern(regexp = "^([A-Z]{3})(,[A-Z]{3})*$")
	protected String acceptedCurrencies;
	
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}$")
	protected String systemCurrency;
	
	@NotBlank
	@Pattern(regexp="(\\((['][a-zA-Z ’]*['])[:]([0-1].[0-9]{2})\\)[,])*")
	protected String spamTermsEn;
	
	@NotBlank
	@Pattern(regexp="(\\((['][a-zA-Z ]*['])[:]([0-1].[0-9]{2})\\)[,])*")
	protected String spamTermsEs;
	
	@Range(min=0,max=1)
	protected double spamThreshold;
	
	
}