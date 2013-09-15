package br.com.mackenzie.peladafc.activity;

import java.util.ArrayList;
import java.util.List;

import br.com.mackenzie.peladafc.facade.FacadeController;
import br.com.mackenzie.peladafc.model.Jogador;
import br.com.mackenzie.peladafc.model.Modalidade;
import br.com.mackenzie.peladafc.model.Time;
import android.app.Activity;

public abstract class PeladaFCActivity extends Activity{
	
	private FacadeController facadeController;
	private static List<Time> timesSelecionados = new ArrayList<Time>();
	private static List<Jogador> jogadoresSelecionados = new ArrayList<Jogador>();
	private static Modalidade modalidadeSelecionada = null;
	
	public final FacadeController getFacadeController(){
		if(facadeController == null){
			facadeController = new FacadeController(getApplicationContext());
		}
		return facadeController;
	}

	public static List<Time> getTimesSelecionados() {
		return timesSelecionados;
	}

	public static void setTimesSelecionados(List<Time> timesSelecionados) {
		PeladaFCActivity.timesSelecionados = timesSelecionados;
	}

	public static List<Jogador> getJogadoresSelecionados() {
		return jogadoresSelecionados;
	}

	public static void setJogadoresSelecionados(List<Jogador> jogadoresSelecionados) {
		PeladaFCActivity.jogadoresSelecionados = jogadoresSelecionados;
	}

	public static Modalidade getModalidadeSelecionada() {
		return modalidadeSelecionada;
	}

	public static void setModalidadeSelecionada(Modalidade modalidadeSelecionada) {
		PeladaFCActivity.modalidadeSelecionada = modalidadeSelecionada;
	}
}
