package br.pucrio.inf1802.dao;

@SuppressWarnings("serial")
public class DALException extends Exception {
	
	public DALException(String message) {
		super(message);
	}
	
	public DALException(String message,Throwable cause){
		super(message,cause);
	}

}