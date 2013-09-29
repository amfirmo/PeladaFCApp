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
import android.widget.ExpandableListView;
import android.widget.ListView;
import br.com.mackenzie.peladafc.adapter.TimesListAdapter;
import br.com.mackenzie.peladafc.model.Time;

public class CriarTimesActivity extends PeladaFCActivity  implements OnClickListener{
	private ArrayAdapter<Time> adapter2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_criar_times);
		List<Time> timesFormados = null;
		if (getJogadoresSelecionados() == null || !getJogadoresSelecionados().isEmpty()) {
			try {
				timesFormados = 
						getFacadeController()
						.sortear(PeladaFCActivity.getJogadoresSelecionados(),
								PeladaFCActivity.getModalidadeSelecionada().getJogadoresPorTime());
			} catch (Exception e) {
				e.printStackTrace();
			}

			setTimesFormados(timesFormados);
		}
		//primeira lista simples com os times formados
		ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView1);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		TimesListAdapter adapter = new TimesListAdapter(this,getTimesFormados());
		listView.setAdapter(adapter);

		//monta a segunda lista com os detalhes de cada time
		ListView listView2 = (ListView) findViewById(R.id.listView2);
		adapter2 = new ArrayAdapter<Time>(this,
				android.R.layout.simple_list_item_multiple_choice,
				getTimesFormados());

		listView2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listView2.setAdapter(adapter2);


	}

	@Override
	public void onBackPressed()
	{
		ListView listView = (ListView) findViewById(R.id.listView2);
		SparseBooleanArray checked = listView.getCheckedItemPositions();
		byte totalTimesSelecionados = 0;
		setTimesSelecionados(new ArrayList<Time>(2));

		for (int i = 0; i < checked.size() && totalTimesSelecionados<2; i++) {
			// Item position in adapter
			int position = checked.keyAt(i);
			// Add sport if it is checked i.e.) == TRUE!
			if (checked.valueAt(i)){
				totalTimesSelecionados++;
				getTimesSelecionados().add(adapter2.getItem(position));
			}	
		}
		
		if(totalTimesSelecionados<2){
			showMessageShort("Selecione pelo menos dois times!");
		}else{
			showMessageShort("Sua Seleção foi Gravada com Sucesso!");
		}
		super.onBackPressed();
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
		
	}
}
