package br.com.mackenzie.peladafc.facade;

import java.util.List;

import android.content.Context;
import br.com.mackenzie.peladafc.controller.JogadorController;
import br.com.mackenzie.peladafc.controller.ModalidadeController;
import br.com.mackenzie.peladafc.controller.PartidaController;
import br.com.mackenzie.peladafc.controller.TimeController;
import br.com.mackenzie.peladafc.exception.DAOException;
import br.com.mackenzie.peladafc.model.Jogador;

public class FacadeController {

	private JogadorController jogadorController;
	private ModalidadeController modalidadeController;
	private PartidaController partidaController;
	private TimeController timeController;
	
	public FacadeController(Context context){
		this.jogadorController = new JogadorController(context); 
		this.modalidadeController = new ModalidadeController(context);
		//this.partidaController = new PartidaController();
		this.timeController = new TimeController();
	}
	/**
	 * Cadastra um jogador por nome e apelido
	 * @param nome
	 * @param apelido
	 * @throws DAOException 
	 */
	public void adicionarJogador(String nome, String apelido) throws DAOException{
		this.jogadorController.adicionar(nome, apelido);
	}
	/**
	 * Obtem todos os jogadores cadastrados na base.
	 * @return List<Jogador>
	 * @throws DAOException 
	 */
	public List<Jogador> obterJogadores() throws DAOException{
		return this.jogadorController.obterJogadores();
	}
}
