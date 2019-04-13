package br.com.neolog.welcomekit.exceptions;

public class CodeCategoryNotFoundException extends RuntimeException {

	/**
	 * Exceção quando não acha o codigo da categoria
	 */
	private static final long serialVersionUID = -4145483259461816265L;

	public CodeCategoryNotFoundException(final String message) {
		super(message);
	}
}
