package br.com.neolog.welcomekit.exceptions;

public class CustomerEmailNotFundException extends RuntimeException {

	/**
	 * Exceção quando email requisitado não é encontrado
	 */
	private static final long serialVersionUID = 1052349659819814209L;

	public CustomerEmailNotFundException(final String message) {
		super(message);
	}

}
