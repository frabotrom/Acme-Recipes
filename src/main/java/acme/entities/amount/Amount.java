package acme.entities.amount;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import acme.entities.recipe.Recipe;
import acme.entities.thing.Thing;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Amount extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
		
	// Attributes -------------------------------------------------------------
		
	@Min(1)
	protected int quantity;
	
	@NotNull
	protected Unit unit;
		
	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne
	protected Recipe recipe;
	
	@NotNull
	@Valid
	@ManyToOne
	protected Thing thing;
	
	
}
