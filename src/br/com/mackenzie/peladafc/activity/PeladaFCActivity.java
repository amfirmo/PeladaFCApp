package br.com.mackenzie.peladafc.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.widget.Toast;
import br.com.mackenzie.peladafc.facade.FacadeController;
import br.com.mackenzie.peladafc.model.Jogador;
import br.com.mackenzie.peladafc.model.Modalidade;
import br.com.mackenzie.peladafc.model.Time;

public abstract class PeladaFCActivity extends Activity{

	private FacadeController facadeController;
	private static List<Time> timesFormados = new ArrayList<Time>();
	private static List<Time> timesSelecionados = new ArrayList<Time>(2);
	public static List<Time> getTimesSelecionados() {
		return timesSelecionados;
	}

	public static void setTimesSelecionados(List<Time> timesSelecionados) {
		PeladaFCActivity.timesSelecionados = timesSelecionados;
	}

	private static List<Jogador> jogadoresSelecionados = new ArrayList<Jogador>();
	private static Modalidade modalidadeSelecionada = null;

	public final FacadeController getFacadeController(){
		if(facadeController == null){
			facadeController = new FacadeController(getApplicationContext());
		}
		return facadeController;
	}

	public static List<Time> getTimesFormados() {
		return timesFormados;
	}

	public static void setTimesFormados(List<Time> timesSelecionados) {
		PeladaFCActivity.timesFormados = timesSelecionados;
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
	/**
	 * Exibe mensagem ao usuário que desaparece rapidamente
	 * @param O texto da mensagem
	 */
	public void showMessageShort(CharSequence text){
		showMessage(text, Toast.LENGTH_SHORT);
	}
	/**
	 * Exibe mensagem ao usuário que desaparece lentamente
	 * @param O texto da mensagem
	 */
	public void showMessageLong(CharSequence text){
		showMessage(text, Toast.LENGTH_LONG);
	}

	private void showMessage(CharSequence text, int duration){
		Toast.makeText(getApplicationContext(), text, duration).show();
	}

}
