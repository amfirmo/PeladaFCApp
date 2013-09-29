package br.com.mackenzie.peladafc.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.mackenzie.peladafc.exception.DAOException;
import br.com.mackenzie.peladafc.model.Jogador;

public class SelecionarJogadorActivity extends PeladaFCActivity  implements OnClickListener{
	private ListView listView;
	private ArrayAdapter<Jogador> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		List<Jogador> listaJogadores = null;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selecionar_jogador);

		findViewsById();
		try {
			listaJogadores = getFacadeController().obterJogadores();

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		adapter = new ArrayAdapter<Jogador>(this,
				android.R.layout.simple_list_item_multiple_choice,
				listaJogadores);

		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listView.setAdapter(adapter);
		//se a lista de jogadores selecionados nao esta vazia, marca os jogadores selecionados
		if(!getJogadoresSelecionados().isEmpty() && !listaJogadores.isEmpty()){
			
			for(int i = 0;i<listaJogadores.size(); i++){
				int position = i;
				
				Jogador jogador = (Jogador) listView.getItemAtPosition(position);	
				//se o jogador atual ja esta na lista de selecionados, marca-o
				if(getJogadoresSelecionados().contains(jogador)){
					listView.setItemChecked(position, true);
				}
			}
		}
	}

	@Override
	public void onBackPressed()
	{
		SparseBooleanArray checked = listView.getCheckedItemPositions();
		setJogadoresSelecionados(new ArrayList<Jogador>());

		for (int i = 0; i < checked.size(); i++) {
			// Item position in adapter
			int position = checked.keyAt(i);
			// Add sport if it is checked i.e.) == TRUE!
			if (checked.valueAt(i))
				getJogadoresSelecionados().add(adapter.getItem(position));
		}

/*		Jogador[] outputStrArr = new Jogador[modalidadeSelecionada.size()];

		for (int i = 0; i < modalidadeSelecionada.size(); i++) {
			outputStrArr[i] = modalidadeSelecionada.get(i);
		}
*/
		showMessageShort("Sua Seleção foi Gravada com Sucesso!");
		
		super.onBackPressed();
	}

	private void findViewsById() {
		listView = (ListView) findViewById(R.id.listView1);
		//button = (Button) findViewById(R.id.testbutton);
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
		getMenuInflater().inflate(R.menu.display_message, menu);
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

	@Override
	public void onClick(View v) {
		SparseBooleanArray checked = listView.getCheckedItemPositions();
		ArrayList<Jogador> jogadoresSelecionados = new ArrayList<Jogador>();
		for (int i = 0; i < checked.size(); i++) {
			// Item position in adapter
			int position = checked.keyAt(i);
			// Add sport if it is checked i.e.) == TRUE!
			if (checked.valueAt(i))
				jogadoresSelecionados.add(adapter.getItem(position));
		}

/*		Jogador[] outputStrArr = new Jogador[modalidadeSelecionada.size()];

		for (int i = 0; i < modalidadeSelecionada.size(); i++) {
			outputStrArr[i] = modalidadeSelecionada.get(i);
		}
*/		
		/*
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);

        // Create a bundle object
        Bundle b = new Bundle();
        b.putStringArray("selectedItems", outputStrArr);

        // Add the bundle to the intent.
        intent.putExtras(b);

        // start the ResultActivity
        startActivity(intent);
		 */
	}
}
