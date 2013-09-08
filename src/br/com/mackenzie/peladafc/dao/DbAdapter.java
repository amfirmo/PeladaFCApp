package br.com.mackenzie.peladafc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.mackenzie.peladafc.model.Jogador;

public class DbAdapter {
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private String[] allColumns = {DbHelper.COLUNA_JOGADOR_ID, DbHelper.COLUNA_JOGADOR_NOME, 
			DbHelper.COLUNA_JOGADOR_APELIDO,DbHelper.COLUNA_JOGADOR_CLASSIFICACAO };
	
	public DbAdapter(Context context) {          
		dbHelper = new DbHelper(context);
	}
	
	public Jogador createJogador(String nome, String apelido, int classificacao) { 
        ContentValues values = new ContentValues(); 
        values.put(DbHelper.COLUNA_JOGADOR_NOME, nome); 
        values.put(DbHelper.COLUNA_JOGADOR_APELIDO,apelido); 
        values.put(DbHelper.COLUNA_JOGADOR_CLASSIFICACAO, classificacao); 
        long insertId = database.insert(DbHelper.TABELA_JOGADOR, null, values); 
       // To show how to query 
       Cursor cursor = database.query(DbHelper.TABELA_JOGADOR, allColumns, DbHelper.COLUNA_JOGADOR_ID + " = " + 
       insertId, null,null, null, null); 
       cursor.moveToFirst(); 
       return cursorToJogador(cursor); 
	}
	
	private Jogador cursorToJogador(Cursor cursor) {
		Jogador jogador = new Jogador();
		jogador.setId(cursor.getInt(0));
		jogador.setNome(cursor.getString(1));
		jogador.setApelido(cursor.getString(2));
		jogador.setClassificao(cursor.getInt(3));
		return jogador;
	}
}


	