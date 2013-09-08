package br.com.mackenzie.peladafc.activity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import br.com.mackenzie.peladafc.exception.DAOException;
import br.com.mackenzie.peladafc.model.Jogador;

public class SelecionarJogadorActivity extends PeladaFCActivity  implements OnClickListener{
	Button button;
	ListView listView;
	ArrayAdapter<String> adapter;
	Object[] jogadores;
	List<Jogador> listaJogadores;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selecionar_jogador);

		findViewsById();
		//jogadores = getResources().getStringArray(R.array.jogadores_array);
		try {

			listaJogadores = getFacadeController().obterJogadores();

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//obtem da lista de jogadores, seus nomes e apelidos "nome(apelido)"
		List<String> lista = new LinkedList<String>();
		for (Jogador jogador : listaJogadores) {
			lista.add(jogador.getNome()+" ("+jogador.getApelido()+")");
		}

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice,
				lista);

		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listView.setAdapter(adapter);
		//listView.setItemChecked(1, true);

		//   button.setOnClickListener(this);
	}

	@Override
	public void onBackPressed()
	{
		SparseBooleanArray checked = listView.getCheckedItemPositions();
		ArrayList<String> selectedItems = new ArrayList<String>();

		for (int i = 0; i < checked.size(); i++) {
			// Item position in adapter
			int position = checked.keyAt(i);
			// Add sport if it is checked i.e.) == TRUE!
			if (checked.valueAt(i))
				selectedItems.add(adapter.getItem(position));
		}

		String[] outputStrArr = new String[selectedItems.size()];

		for (int i = 0; i < selectedItems.size(); i++) {
			outputStrArr[i] = selectedItems.get(i);
		}


		Context context = getApplicationContext();
		CharSequence text = "Seleção Gravada";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();

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
		ArrayList<String> selectedItems = new ArrayList<String>();
		for (int i = 0; i < checked.size(); i++) {
			// Item position in adapter
			int position = checked.keyAt(i);
			// Add sport if it is checked i.e.) == TRUE!
			if (checked.valueAt(i))
				selectedItems.add(adapter.getItem(position));
		}

		String[] outputStrArr = new String[selectedItems.size()];

		for (int i = 0; i < selectedItems.size(); i++) {
			outputStrArr[i] = selectedItems.get(i);
		}
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
