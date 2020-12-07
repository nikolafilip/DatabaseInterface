package hr.java.vjezbe.iznimke;

public class NemoguceOdreditiGrupuOsiguranjaException extends Exception {

	/**
	 * Iznimka koja se baca kada nije moguce odrediti grupu osiguranja
	 * 
	 * @author Nikola Filip
	 */
	private static final long serialVersionUID = 6590743867235175565L;

	public NemoguceOdreditiGrupuOsiguranjaException(String poruka) {
		super(poruka);
	}

	public NemoguceOdreditiGrupuOsiguranjaException(Throwable uzrok) {
		super(uzrok);
	}

	public NemoguceOdreditiGrupuOsiguranjaException(String poruka, Throwable uzrok) {
		super(poruka, uzrok);
	}

	public NemoguceOdreditiGrupuOsiguranjaException() {
		// TODO Auto-generated constructor stub
	}

}
