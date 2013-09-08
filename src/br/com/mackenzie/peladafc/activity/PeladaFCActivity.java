package br.com.mackenzie.peladafc.activity;

import br.com.mackenzie.peladafc.facade.FacadeController;
import android.app.Activity;

public abstract class PeladaFCActivity extends Activity{
	
	private FacadeController facadeController;
	
	public FacadeController getFacadeController(){
		if(facadeController == null){
			facadeController = new FacadeController(getApplicationContext());
		}
		return facadeController;
	}
	
}
