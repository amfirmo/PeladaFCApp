package br.com.mackenzie.peladafc.model;

import java.util.List;

import br.com.mackenzie.peladafc.controller.TimeController;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : PeladaFC
//  @ File Name : Time.java
//  @ Date : 07/09/2013
//  @ Author : 
//
//




/** */
public class Time {
	/** */
	private Object id;
	
	/** */
	private Object nome;
	
	/** */
	private List<Jogador> escalacao;
	
	/** */
	public Partida Unnamed1;
	
	/** */
	public Jogador Unnamed2;
	
	/** */
	public TimeController Unnamed3;

	@Override
	public String toString() {
		return "Time: " + nome;
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
