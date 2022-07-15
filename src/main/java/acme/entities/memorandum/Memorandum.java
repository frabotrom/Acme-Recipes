package acme.entities.memorandum;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

import acme.entities.finedish.FineDish;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Memorandum extends AbstractEntity{

		protected static final long	serialVersionUID = 1L;
		
		@NotBlank
		@Pattern(regexp = "^[0-9]{4}$")
		protected String 					serialNumber;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Past
		protected Date 						instantiationMoment;
		
		
		@NotBlank
		@Length(min = 1, max = 255)
		protected String 					report;
		
		
		@URL
		protected String 					info;
		
		
		// Derived attributes -----------------------------------------------------
		@Transient
		@NotBlank
		public String getSeqNumber() {
			StringBuilder result;
			result = new StringBuilder();
			result.append(this.fineDish.getCode());
			result.append(":");
			result.append(this.serialNumber);
			return result.toString();
			
		}
		
		// Relationships ----------------------------------------------------------
		
		@NotNull
		@Valid
		@ManyToOne(optional=false)
		protected FineDish 				fineDish;
		
		

}