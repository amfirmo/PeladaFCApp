package br.com.mackenzie.peladafc.model;

import java.util.ArrayList;
import java.util.List;




/** */
public class Time {
	/** */
	private Object id;
	
	/** */
	private Object nome;
	
	/** */
	List<Jogador> escalacao;

	@Override
	public String toString() {
		return "Time: " + nome;
	}
	
	public Time(){
		escalacao = new ArrayList<Jogador>();
	}
	
	/** */
	public void adicionar(Jogador jogador) {
		escalacao.add(jogador);
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getNome() {
		return nome;
	}

	public void setNome(Object nome) {
		this.nome = nome;
	}

	public List<Jogador> getEscalacao() {
		return escalacao;
	}

	public void setEscalacao(List<Jogador> escalacao) {
		this.escalacao = escalacao;
	}
	
	
}
