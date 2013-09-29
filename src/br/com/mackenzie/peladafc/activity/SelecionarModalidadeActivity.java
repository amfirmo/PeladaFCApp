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
import br.com.mackenzie.peladafc.model.Modalidade;

public class SelecionarModalidadeActivity extends PeladaFCActivity  implements OnClickListener{
	private ListView listView;
	private ArrayAdapter<Modalidade> adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		List<Modalidade> listaModalidades = null;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selecionar_modalidade);

		findViewsById();
		try {
			listaModalidades = getFacadeController().obterModalidades();

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		adapter = new ArrayAdapter<Modalidade>(this,
											   android.R.layout.simple_list_item_single_choice,
											   listaModalidades);

		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView.setAdapter(adapter);
		
		//se a lista de jogadores selecionados nao esta vazia, marca os jogadores selecionados
		if(getModalidadeSelecionada() != null && !listaModalidades.isEmpty()){
			
			for(int i = 0;i<listaModalidades.size(); i++){
				int position = i;
				
				Modalidade modalidade = (Modalidade) listView.getItemAtPosition(position);	
				//se a modalidade atual ja esta na lista de selecionadas, marca-a
				if(getModalidadeSelecionada().equals(modalidade)){
					listView.setItemChecked(position, true);
				}
			}
		}
	}

	@Override
	public void onBackPressed()
	{
		SparseBooleanArray checked = listView.getCheckedItemPositions();
		setModalidadeSelecionada(null);

		for (int i = 0; i < checked.size(); i++) {
			// Item position in adapter
			int position = checked.keyAt(i);
			// Add sport if it is checked i.e.) == TRUE!
			if (checked.valueAt(i))
				setModalidadeSelecionada(adapter.getItem(position));
		}

/*		Modalidade[] outputStrArr = new Modalidade[modalidadeSelecionada.size()];

		for (int i = 0; i < modalidadeSelecionada.size(); i++) {
			outputStrArr[i] = modalidadeSelecionada.get(i);
		}
*/
		showMessageShort("Sua seleção foi gravada com sucesso!");
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
		ArrayList<Modalidade> modalidadeSelecionadas = new ArrayList<Modalidade>();
		for (int i = 0; i < checked.size(); i++) {
			// Item position in adapter
			int position = checked.keyAt(i);
			// Add sport if it is checked i.e.) == TRUE!
			if (checked.valueAt(i))
				modalidadeSelecionadas.add(adapter.getItem(position));
		}

/*		Modalidade[] outputStrArr = new Modalidade[modalidadeSelecionada.size()];

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
