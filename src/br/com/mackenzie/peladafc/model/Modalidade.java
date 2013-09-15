package br.com.mackenzie.peladafc.model;

import java.io.Serializable;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : PeladaFC
//  @ File Name : Modalidade.java
//  @ Date : 07/09/2013
//  @ Author : 
//
//
/** */
public class Modalidade implements Serializable {
	/** */
	private int id;
	
	/** */
	private String descricao;
	
	/** */
	private int jogadoresPorTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getJogadoresPorTime() {
		return jogadoresPorTime;
	}

	public void setJogadoresPorTime(int jogadoresPorTime) {
		this.jogadoresPorTime = jogadoresPorTime;
	}

	@Override
	public String toString() {
		return descricao + "(Qtde. Jogadores: " + jogadoresPorTime + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modalidade other = (Modalidade) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
