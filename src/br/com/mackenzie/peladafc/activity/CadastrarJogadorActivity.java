package br.com.mackenzie.peladafc.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import br.com.mackenzie.peladafc.exception.DAOException;

public class CadastrarJogadorActivity extends PeladaFCActivity {

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
		
		//float classificao = classificacaoRatingBar.getRating();
		//adiciona o jogador pelo facade que usa o JogadorController	
		try {
			getFacadeController().adicionarJogador(nomeEditText.getText().toString(),
					                          apelidoEditText.getText().toString());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//limpar o formulario para um nova insercao de dados
		nomeEditText.setText("");
		nomeEditText.requestFocus();
		apelidoEditText.setText("");
		classificacaoRatingBar.setRating(3);
		//exibe mensagem que o jogador foi adicionado
		showMessageShort("Jogador Gravado!");
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
