package hr.java.vjezbe.iznimke;

public class CijenaJePreniskaException extends Exception {
	/**
	 * Iznimka koja se baca kada je cijena niza od odredene svota
	 * 
	 * @author Nikola Filip
	 */
	private static final long serialVersionUID = 7982118838294857288L;

	public CijenaJePreniskaException(String poruka) {
		super(poruka);
	}

	public CijenaJePreniskaException(Throwable uzrok) {
		super(uzrok);
	}

	public CijenaJePreniskaException(String poruka, Throwable uzrok) {
		super(poruka, uzrok);
	}
}
