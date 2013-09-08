package br.com.mackenzie.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class CadastrarJogadorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_cadastar_jogador);
		// Show the Up button in the action bar.
		setupActionBar();
		
//		Intent intent = getIntent();
		
//		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
/*		
	    // Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    textView.setText(message);

	    // Set the text view as the activity layout
	    setContentView(textView);
*/	    
	}
	
    public void gravarJogador(View view){
		
		EditText nomeEditText = (EditText) findViewById(R.id.nomeText);
		EditText apelidoEditText = (EditText) findViewById(R.id.apelidoText);
		RatingBar classificacaoRatingBar = (RatingBar)  findViewById(R.id.classificacaoBar);
		
		float classificao = classificacaoRatingBar.getRating();
		
		System.out.println("nomeEditText : "+nomeEditText.getText());
		System.out.println("apelidoEditText : "+apelidoEditText.getText());
		System.out.println("classificao : "+classificao);
		
		nomeEditText.setText("");
		nomeEditText.requestFocus();
		apelidoEditText.setText("");
		classificacaoRatingBar.setRating(3);
		
		Context context = getApplicationContext();
		CharSequence text = "Jogador Gravado!";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
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

}
