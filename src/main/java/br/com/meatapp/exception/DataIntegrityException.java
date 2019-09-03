package br.com.meatapp.exception;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1l;
	
	public DataIntegrityException(String msg) {
		super(msg);
	}

}
