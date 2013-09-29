package br.com.mackenzie.peladafc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends PeladaFCActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void cadastarJogador(View view){
		Intent intent = new Intent(this, CadastrarJogadorActivity.class);
		startActivity(intent);
	}

	public void selecionarJogador(View view){
		Intent intent = new Intent(this, SelecionarJogadorActivity.class);
		startActivity(intent);
	}

	public void selecionarModalidade(View view){
		Intent intent = new Intent(this, SelecionarModalidadeActivity.class);
		startActivity(intent);
	}

	public void criarTimes(View view){
		if(getTimesFormados() != null && !getTimesFormados().isEmpty() && getJogadoresSelecionados().isEmpty()){
			Intent intent = new Intent(this, CriarTimesActivity.class);
			startActivity(intent);
		}else{
			if(getJogadoresSelecionados().isEmpty()){
				showMessageShort("Não há jogadores selecionados!");
			}else if(getModalidadeSelecionada() == null){
				showMessageShort("Não há modalidade selecionada!");
				//no mínimo o dobro de jogadores
			}else if(getJogadoresSelecionados().size() < ((getModalidadeSelecionada().getJogadoresPorTime())* 2)){
				showMessageShort("A quantidade de jogadores selecionados " +
						"é menor que o esperado pela modalidade selecionada!");
			}else{
				Intent intent = new Intent(this, CriarTimesActivity.class);
				startActivity(intent);
			}
		}
	}

	public void iniciarPartida(View view){
		if(getTimesSelecionados() == null || getTimesSelecionados().isEmpty()){
			showMessageShort("Não há times selecionados, sortear e selecionar os times primeiro.");
		}else if(getTimesSelecionados().size() < 2){
			showMessageShort("Deve haver pelo menos dois times selecionados");
		}else{
			Intent intent = new Intent(this, PartidaActivity.class);
			startActivity(intent);
		}
	}
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		//System.exit(0);

	}
}
