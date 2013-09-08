package br.com.mackenzie.peladafc.exception;

public class ControllerException extends Exception{
	private String mensagem;
	
	public ControllerException(String mensagem) {
		// TODO Auto-generated constructor stub
		super(mensagem);
		this.mensagem = mensagem;
	}
	
	public String getMensagem(){
		return this.mensagem;
	}
}
