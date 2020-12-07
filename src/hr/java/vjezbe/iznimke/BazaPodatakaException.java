package hr.java.vjezbe.iznimke;

public class BazaPodatakaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8188791072003984173L;

	public BazaPodatakaException(String poruka) {
		super(poruka);
	}

	public BazaPodatakaException(Throwable uzrok) {
		super(uzrok);
	}

	public BazaPodatakaException(String poruka, Throwable uzrok) {
		super(poruka, uzrok);
	}
}
