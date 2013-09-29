package br.com.mackenzie.peladafc.facade;

import java.util.List;

import android.content.Context;
import br.com.mackenzie.peladafc.controller.JogadorController;
import br.com.mackenzie.peladafc.controller.ModalidadeController;
import br.com.mackenzie.peladafc.controller.PartidaController;
import br.com.mackenzie.peladafc.controller.TimeController;
import br.com.mackenzie.peladafc.exception.DAOException;
import br.com.mackenzie.peladafc.model.Jogador;
import br.com.mackenzie.peladafc.model.Modalidade;
import br.com.mackenzie.peladafc.model.Time;

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
	 * Atualiza os dados de um jogador.
	 * @param jogador referencia para o Jogador a ser atualizado.
	 * @throws DAOException
	 */
	public void atualizarJogador(Jogador jogador) throws DAOException{
		this.jogadorController.atualizar(jogador);
	}
	/**
	 * Obtem todos os jogadores cadastrados na base.
	 * @return List<Jogador>
	 * @throws DAOException 
	 */
	public List<Jogador> obterJogadores() throws DAOException{
		return this.jogadorController.obterJogadores();
	}
	/**
	 * Obtem todas as modalidades disponiveis.
	 * @return List<Modalidade>
	 * @throws DAOException
	 */
	public List<Modalidade> obterModalidades() throws DAOException{
		return this.modalidadeController.obterModalidades();
	}
	/**
	 * Monta os Times por meio de sorteio
	 * @param jogadores (Lista de jogadores que participarao do sorteio)
	 * @param qtdJogadoresPorTime (Quantidade de jogadores a ser distribuido por time)
	 * @throws DAOException
	 */
	public void sortear(List<Jogador>jogadores, int qtdJogadoresPorTime) throws DAOException{
		this.timeController.sortear(jogadores, qtdJogadoresPorTime);
	}
	
	public Time obterTimePorId(int id) throws DAOException{
		return this.timeController.obterTimePorId(id);
	}

	
}
