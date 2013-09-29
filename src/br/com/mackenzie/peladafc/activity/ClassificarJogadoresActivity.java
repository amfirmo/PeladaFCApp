package br.com.mackenzie.peladafc.activity;

import java.util.List;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import br.com.mackenzie.peladafc.adapter.TimesListClassificationAdapter;
import br.com.mackenzie.peladafc.exception.DAOException;
import br.com.mackenzie.peladafc.model.Jogador;
import br.com.mackenzie.peladafc.model.Time;

public class ClassificarJogadoresActivity extends PeladaFCActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classificar_jogadores);
		// Show the Up button in the action bar.
		setupActionBar();
		
	    ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListView1);
	    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	    TimesListClassificationAdapter adapter = new TimesListClassificationAdapter(this,getTimesSelecionados());
	    listView.setAdapter(adapter);
	}
	
	public void classificar(View view){
		List<Time> times = getTimesSelecionados();
		
		for (Time time : times) {
			List<Jogador> jogadores = time.getEscalacao();
			for (Jogador jogador : jogadores) {
				try {
					
					getFacadeController().atualizarJogador(jogador);
					
				} catch (DAOException e) {
					e.printStackTrace();
					showMessageShort("Não foi possível atualizar o jogador");
					this.finish();
				}
				//showMessageShort(jogador.getNome()+" -> NOTA: "+jogador.getClassificao());
				
			}
		}
		showMessageShort("Classificações gravadas com sucesso!");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.classificar_jogadores, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
