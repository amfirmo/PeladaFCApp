package br.com.mackenzie.peladafc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
	 
	private static final String DB_NAME = "PELADAFC";
	private static final int DATABASE_VERSION = 1;
	public static final String TABELA_JOGADOR = "JOGADOR";
	 
	public static final String COLUNA_JOGADOR_ID = "jogador_id";
	public static final String COLUNA_JOGADOR_APELIDO = "jogador_apelido";
	public static final String COLUNA_JOGADOR_NOME = "jogador_nome";
	public static final String COLUNA_JOGADOR_CLASSIFICACAO = "jogador_classificacao";

	public static final String TABELA_MODALIDADE = "MODALIDADE";
	public static final String COLUNA_MODALIDADE_ID = "modalidade_id";
	public static final String COLUNA_MODALIDADE_DESCRICAO = "modalidade_descricao";
	public static final String COLUNA_MODALIDADE_QTDE_JOGADORES = "modalidade_qtde_jogadores";
	
	private static final String JOGADOR_CREATE_TABLE = "CREATE TABLE "
			+ TABELA_JOGADOR + "  (" + COLUNA_JOGADOR_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
			+ COLUNA_JOGADOR_NOME + " text not null, "
			+ COLUNA_JOGADOR_APELIDO + " text not null ," 
			+ COLUNA_JOGADOR_CLASSIFICACAO + " INTEGER );";

		private static final String MODALIDADE_CREATE_TABLE = "CREATE TABLE "
			+ TABELA_MODALIDADE + "  ( " + COLUNA_MODALIDADE_ID
			+ " INTEGER PRIMARY KEY ,  "
			+ COLUNA_MODALIDADE_DESCRICAO + " text not null ,  "
			+ COLUNA_MODALIDADE_QTDE_JOGADORES + " INTEGER );";

		public DbHelper(Context context) {
			super(context, DB_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onOpen(SQLiteDatabase db)
		  {
			super.onOpen(db);
			if (!db.isReadOnly())
			{
			  db.execSQL("PRAGMA foreign_keys=ON;");
			}
		  }
	 
		@Override
		public void onCreate(SQLiteDatabase db) {
	 
			//cria as tabelas
			db.execSQL(JOGADOR_CREATE_TABLE);
			db.execSQL(MODALIDADE_CREATE_TABLE );
			
			//Insere valores iniciais na tabela MODALIDADE
			ContentValues values = new ContentValues();
			values.put(COLUNA_MODALIDADE_ID, 1);
			values.put(COLUNA_MODALIDADE_DESCRICAO, "CAMPO");
			values.put(COLUNA_MODALIDADE_QTDE_JOGADORES, 11);
			db.insert(TABELA_MODALIDADE, null, values);
			values.clear();
			values.put(COLUNA_MODALIDADE_ID, 2);
			values.put(COLUNA_MODALIDADE_DESCRICAO, "QUADRA");
			values.put(COLUNA_MODALIDADE_QTDE_JOGADORES, 5);
			db.insert(TABELA_MODALIDADE, null, values);
			values.clear();
			values.put(COLUNA_MODALIDADE_ID, 3);
			values.put(COLUNA_MODALIDADE_DESCRICAO, "SOCIETY");
			values.put(COLUNA_MODALIDADE_QTDE_JOGADORES, 7);
			db.insert(TABELA_MODALIDADE, null, values);
			values.clear();
			
			Log.w("DbAdapter","DB criado com sucesso!");
		}
	 
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(DbAdapter.class.getName(),  "Atualizando o banco de dados da versão " + oldVersion
					+ " para " + newVersion
					+ ", todos os dados serão perdidos!");
			//TODO ADICIONAR TODAS AS TABELAS AQUI 
			db.execSQL("DROP TABLE IF EXISTS " + TABELA_JOGADOR);
			onCreate(db);
		}
	}


