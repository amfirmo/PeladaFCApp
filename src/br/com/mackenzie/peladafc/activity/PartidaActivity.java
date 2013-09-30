package br.com.mackenzie.peladafc.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import br.com.mackenzie.peladafc.adapter.TimesListAdapter;
import br.com.mackenzie.peladafc.model.Time;

public class PartidaActivity extends PeladaFCActivity {
	private Ringtone ring = null;
	private boolean pause = false;
	private long elapsedRealtimeOnPause = 0 ;
	private boolean running = false;
	private Time time1 = null;
	private Time time2 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_partida);
		// Show the Up button in the action bar.
		setupActionBar();
		Chronometer chronometer = ((Chronometer) findViewById(R.id.chronometer1));
		chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {                      
			@Override
			public void onChronometerTick(Chronometer chronometer) {
				long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
				
				//converte o tempo definido em minutos para milis
				if(elapsedMillis> ((getModalidadeSelecionada().getTempoDaPartida()*60)*1000)){

					((Chronometer) findViewById(R.id.chronometer1)).stop();

					showMessageLong("Final de Partida! Pressione \"Parar\"");


					try {
						if(ring == null){
							Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
							ring = RingtoneManager.getRingtone(getApplicationContext(), notification);
						}
						if(!ring.isPlaying()){
							ring.play();
						}
						//em caso de erro aqui nao ha muito o que fazer...    
					} catch (Exception e) {e.printStackTrace();}

				}
			}
		});

		time1 = getTimesSelecionados().get(0);
		time2 = getTimesSelecionados().get(1);

		ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListView1);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		TimesListAdapter adapter1 = new TimesListAdapter(this,time1);
		listView.setAdapter(adapter1);

		ExpandableListView listView2 = (ExpandableListView) findViewById(R.id.expandableListView2);
		listView2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		TimesListAdapter adapter2 = new TimesListAdapter(this,time2);
		listView2.setAdapter(adapter2);
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
		getMenuInflater().inflate(R.menu.partida, menu);
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

	public void startChronometer(View view) {
		Chronometer chr = ((Chronometer) findViewById(R.id.chronometer1));
		long base = SystemClock.elapsedRealtime();
		if(pause){

			base = base-elapsedRealtimeOnPause;
			chr.setBase(base);

			pause = false;
		}else{
			chr.setBase(base);

		}
		elapsedRealtimeOnPause = base;
		chr.start();
		running = true;
		showMessageShort("O jogo começou!");
	}

	public void stopChronometer(View view) {
		
		if(running){
			((Chronometer) findViewById(R.id.chronometer1)).stop();
			running = false;
			if(ring != null){
				ring.stop();
			}
			TextView textViewA = (TextView) findViewById(R.id.textGolsA);
			int golsA = Integer.parseInt(textViewA.getText().toString());

			TextView textViewB = (TextView) findViewById(R.id.textGolsB);
			int golsB = Integer.parseInt(textViewB.getText().toString());
			String message = null;
			if (golsA > golsB){
				message = "O time: "+time1.getNome()+" venceu!";
			}else if(golsA < golsB){
				message = "O time: "+time2.getNome()+" venceu!";
			}else{
				message = "Os times empataram";
			}
			showAlert(message);
		}
	}

	public void pauseChronometer(View view) {
		((Chronometer) findViewById(R.id.chronometer1)).stop();
		running = false;
		pause = true;
		elapsedRealtimeOnPause = SystemClock.elapsedRealtime()-elapsedRealtimeOnPause;
		showMessageShort("Jogo Pausado!");
	}

	public void golA(View view) {
		if(podeAnotarGols()){
			TextView textView = (TextView) findViewById(R.id.textGolsA);
			int gols = Integer.parseInt(textView.getText().toString());
			gols = gols+1;
			textView.setText(""+gols);
		}
	}

	public void golB(View view) {
		if(podeAnotarGols()){
			TextView textView = (TextView) findViewById(R.id.textGolsB);
			int gols = Integer.parseInt(textView.getText().toString());
			gols = gols+1;
			textView.setText(""+gols);
		}	
	}

	private boolean podeAnotarGols(){
		if(!running){
			showMessageShort("O jogo está parado, não é possível anotar gols!!!");
			return false;
		}else{
			return true;
		}
	}

	public void showAlert(String title){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set dialog message
		alertDialogBuilder
		.setTitle(title)
		.setMessage("Deseja avaliar os jogadores?")
		.setCancelable(false)
		//.setCancelable(false)
		.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// if this button is clicked, close
				// current activity
				Intent intent = new Intent(PartidaActivity.this, ClassificarJogadoresActivity.class);
				startActivity(intent);

			}
		})
		.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// if this button is clicked, just close
				// the dialog box and do nothing
				PartidaActivity.this.finish();
			}
		}
				);

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}
}
