package hr.java.vjezbe.entitet;

/**
 * Abstraktna klasa koju nasljeduju sve ostale klase iz paketa entitet.
 * 
 * @author Nikola Filip
 *
 */
public abstract class Entitet {
	private Long id;

	public Entitet(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
