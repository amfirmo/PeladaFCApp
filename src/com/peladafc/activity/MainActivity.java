package com.peladafc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.peladafc.activity.R;

public class MainActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.peladafc.activity.MESSAGE";

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
}
