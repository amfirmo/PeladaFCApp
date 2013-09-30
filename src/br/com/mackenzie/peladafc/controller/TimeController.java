package br.com.mackenzie.peladafc.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.mackenzie.peladafc.dao.TimeDAO;
import br.com.mackenzie.peladafc.exception.DAOException;
import br.com.mackenzie.peladafc.model.Jogador;
import br.com.mackenzie.peladafc.model.Time;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : PeladaFC
//  @ File Name : TimeController.java
//  @ Date : 07/09/2013
//  @ Author : 
//
//




/** */
public class TimeController {
	/** */
	private List<Time> times;
	
	/** */
	public JogadorController jogadorController;
	
	/** */
	public Time time;
	
	/** */
	public ModalidadeController modalidadeController;
	
	/** */
	public TimeDAO timeDAO ;
	
	public TimeController() {
		setTimes(new ArrayList<Time>());
	}
	
	public List<Time> getTimes() {
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}

	/**
	 * @throws DAOException  */
	public List<Time> sortear(List<Jogador> jogadores, int qtdJogadoresPorTime) throws DAOException {
		/** */
		List<Time> listTimes = new ArrayList<Time>();
		Map<String, Integer> totalTimes = gerarQuantidadeTime(jogadores.size(), qtdJogadoresPorTime);
		List<Jogador> copiaLista = jogadores;
		          
 		while (listTimes.size() < totalTimes.get("TotTimes")) {
 			Time time = new Time();
 			
 			while (true) {
	 			Integer escolhido = (int)(Math.random() * jogadores.size());
	 			//Jogador j = jogadores.get(tamanho);
	 			
				if (time.getEscalacao().size() >= qtdJogadoresPorTime) {
					Jogador nomePrimeiroJogadorNomearaTime = time.getEscalacao().get(0);
					time.setNome(nomePrimeiroJogadorNomearaTime.getApelido());
					listTimes.add(time);
					break;
				} else {
				 	Jogador j = copiaLista.get(escolhido);
					time.adicionar(j);
					copiaLista.remove(j);
				}
 			}
 		}
 		
 			if (totalTimes.get("Reservas") > 0) {
 				Time time = new Time();
 				
 				for (Jogador j : jogadores) {
 					time.adicionar(j);
 				}
 				listTimes.add(time);
 			}
 			
 		return listTimes;	
	}
	
	/** */
	public Time obterTimePorId(int id) {
		return timeDAO.obterTime(id);
	}
	
	private Map<String, Integer> gerarQuantidadeTime(Integer totalJogador, Integer maxPorTime) {
		Map<String, Integer> totalTimes = new HashMap<String, Integer>();
		
		totalTimes.put("TotTimes", totalJogador/maxPorTime);
		totalTimes.put("Reservas", totalJogador%maxPorTime);
		
		return totalTimes;
	}
}
