package acme.entities.ingredient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class Ingredient extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotBlank
	@Length(min = 0, max = 100)
	protected String name;
	
	@Column(unique=true)
	@NotBlank
	@Pattern(regexp="^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	protected String code;
	
	@NotBlank
	@Length(min = 0, max = 255)
	protected String description;
	
	@NotNull
	@Valid
	protected Money retailPrice;
			
	@URL
	protected String info;
	
	// Relationships ----------------------------------------------------------
	
	/*@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Chef chef;*/
	
}
