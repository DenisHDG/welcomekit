package br.com.neolog.welcomekit.exceptions;

public class ProductCategoryNotFoundException extends RuntimeException {

	/**
	 * Codigo do produto não foi encontrado
	 */
	private static final long serialVersionUID = -8624020945553395708L;

	public ProductCategoryNotFoundException(final String message) {
		super(message);
	}
}
