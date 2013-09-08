package br.com.mackenzie.peladafc.exception;

public class DAOException extends Exception{
	private String mensagem;
	
	public DAOException(String mensagem) {
		// TODO Auto-generated constructor stub
		super(mensagem);
		this.mensagem = mensagem;
	}
	
	public String getMensagem(){
		return this.mensagem;
	}
}
