package com.company.Exception;



public class CorInexistenteException extends Exception {
	
	private String nomeCor;

	public CorInexistenteException(String nomeCor) {
		super();
		this.nomeCor = nomeCor;
	}
	

	public String getNomeConta() {
		return nomeCor;
	}
	
	

}