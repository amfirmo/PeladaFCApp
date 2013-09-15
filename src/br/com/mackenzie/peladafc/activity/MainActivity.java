package br.com.mackenzie.peladafc.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

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
    	
    	if(getJogadoresSelecionados().isEmpty()){
    		Context context = getApplicationContext();
    		CharSequence text = "Não há jogadores selecionados!";

    		Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
    		toast.show();
    	}else if(getModalidadeSelecionada() == null){
    		Context context = getApplicationContext();
    		CharSequence text = "Não há modalidade selecionada!";

    		Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
    		toast.show();
    		//TODO o correto é no mínimo o dobro de jogadores
    	}else if(getJogadoresSelecionados().size() < getModalidadeSelecionada().getJogadoresPorTime()){
    		Context context = getApplicationContext();
    		CharSequence text = "A quantidade de jogadores selecionados " +
    				            "é menor que o esperado pela modalidade selecionada!";
    		Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
    		toast.show();
    	}else{
            Intent intent = new Intent(this, CriarTimesActivity.class);
            startActivity(intent);
    	}
    }
}
