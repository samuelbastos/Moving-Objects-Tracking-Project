package br.pucrio.inf1802.service;

@SuppressWarnings("serial")
public class ServiceException extends Exception {
	
	public ServiceException(String message){
		super(message);
	}
	
	public ServiceException(String message, Throwable cause){
		super(message,cause);
	}

}
