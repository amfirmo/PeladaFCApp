package br.com.mackenzie.peladafc.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;
import br.com.mackenzie.peladafc.adapter.TimesListAdapter;
import br.com.mackenzie.peladafc.model.Time;

public class CriarTimesActivity extends PeladaFCActivity  implements OnClickListener{
	private SparseArray<Time> times = new SparseArray<Time>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		List<Time> listaTimes = new ArrayList<Time>();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_criar_times);

		//findViewsById();
		
		
		try {
			listaTimes = getFacadeController().sortear(PeladaFCActivity.getJogadoresSelecionados(),
										  PeladaFCActivity.getModalidadeSelecionada().getJogadoresPorTime());
			
			for (int i = 0; i < listaTimes.size(); i++) {
				Time time = listaTimes.get(i);
				time.setNome(i + 1);
				times.append(i, time);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		try {
			
			//listaTimes.add(getFacadeController().obterTimePorId(0));
			//listaTimes.add(getFacadeController().obterTimePorId(1));
			/*Time timeA = new Time();
			timeA.setNome("A");
			timeA.setEscalacao(getJogadoresSelecionados());
			Time timeB = new Time();
			timeB.setEscalacao(getJogadoresSelecionados());
			timeB.setNome("B");
			
			listaTimes.add(timeA);
			listaTimes.add(timeB);
			times.append(0, timeA);
			times.append(1, timeB);*/

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView1);
	    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	    TimesListAdapter adapter = new TimesListAdapter(this,times);
	    listView.setAdapter(adapter);
		
		
/*
		adapter = new ArrayAdapter<Time>(this,
				android.R.layout.simple_expandable_list_item_1,
				listaTimes);

		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listView.setAdapter(adapter);
*/
		
		
	}

	@Override
	public void onBackPressed()
	{
/*		SparseBooleanArray checked = listView.getCheckedItemPositions();
		timesSelecionados = new ArrayList<Time>();

		for (int i = 0; i < checked.size(); i++) {
			// Item position in adapter
			int position = checked.keyAt(i);
			// Add sport if it is checked i.e.) == TRUE!
			if (checked.valueAt(i))
				timesSelecionados.add(adapter.getItem(position));
		}
*/
/*		Jogador[] outputStrArr = new Jogador[modalidadeSelecionada.size()];

		for (int i = 0; i < modalidadeSelecionada.size(); i++) {
			outputStrArr[i] = modalidadeSelecionada.get(i);
		}
*/
		Context context = getApplicationContext();
		CharSequence text = "Sua Seleção foi Gravada com Sucesso!";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();

		super.onBackPressed();
	}

/*	private void findViewsById() {
		listView = (ListView) findViewById(R.id.listView1);
		//button = (Button) findViewById(R.id.testbutton);
	}
*/
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
/*		
		SparseBooleanArray checked = listView.getCheckedItemPositions();
		ArrayList<Time> jogadoresSelecionados = new ArrayList<Time>();
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
