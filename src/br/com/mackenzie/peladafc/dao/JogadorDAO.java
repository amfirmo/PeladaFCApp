package br.com.mackenzie.peladafc.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.mackenzie.peladafc.exception.DAOException;
import br.com.mackenzie.peladafc.model.Jogador;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : PeladaFC
//  @ File Name : JogadorDAO.java
//  @ Date : 07/09/2013
//  @ Author : 
//
//




/** */
public class JogadorDAO {
	
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private String[] allColumns = {DbHelper.COLUNA_JOGADOR_ID, DbHelper.COLUNA_JOGADOR_NOME, 
			DbHelper.COLUNA_JOGADOR_APELIDO,DbHelper.COLUNA_JOGADOR_CLASSIFICACAO };
	
	public JogadorDAO(Context context) {          
		dbHelper = new DbHelper(context);
	}
	
	
	private Jogador cursorToJogador(Cursor cursor) {
		Jogador jogador = new Jogador();
		jogador.setId(cursor.getInt(0));
		jogador.setNome(cursor.getString(1));
		jogador.setApelido(cursor.getString(2));
		jogador.setClassificao(cursor.getInt(3));
		return jogador;
	}

	/** */
	public Jogador adiciona(Jogador jogador)  throws DAOException {
		database = dbHelper.getWritableDatabase();
		
        ContentValues values = new ContentValues(); 
        values.put(DbHelper.COLUNA_JOGADOR_NOME, jogador.getNome()); 
        values.put(DbHelper.COLUNA_JOGADOR_APELIDO,jogador.getApelido()); 
        values.put(DbHelper.COLUNA_JOGADOR_CLASSIFICACAO, jogador.getClassificao()); 
       try{
    	   long insertId = database.insert(DbHelper.TABELA_JOGADOR, null, values); 
    	   // mostrar
    	   Cursor cursor = database.query(DbHelper.TABELA_JOGADOR, allColumns, DbHelper.COLUNA_JOGADOR_ID + " = " + 
    			   insertId, null,null, null, null); 
    	   cursor.moveToFirst(); 
    	   jogador =  cursorToJogador(cursor); 
      
       }catch(Exception ex){
    	   jogador = new Jogador();
    	   throw  new DAOException("N�o foi poss�vel adicionar o Jogador.");

       }finally{
    	   if(database != null && database.isOpen()){
				database.close();
			}
       }
       
       return jogador;
       
       
	}
	
	/** */
	public void atualiza(Jogador jogador)  throws DAOException {
		database = dbHelper.getWritableDatabase();
		
        ContentValues values = new ContentValues(); 
        values.put(DbHelper.COLUNA_JOGADOR_NOME, jogador.getNome()); 
        values.put(DbHelper.COLUNA_JOGADOR_APELIDO,jogador.getApelido()); 
        values.put(DbHelper.COLUNA_JOGADOR_CLASSIFICACAO, jogador.getClassificao()); 
       try{
    	  database.update(DbHelper.TABELA_JOGADOR, values, DbHelper.COLUNA_JOGADOR_ID + " = " + jogador.getId(), null);
    	       
       }catch(Exception ex){
    	   throw  new DAOException("N�o foi poss�vel atualizar o Jogador.");
       }finally{
    	   if(database != null && database.isOpen()){
				database.close();
			}
       }
       
       
	}
	
	/** */
	public List<Jogador> obterListaJogadores()  throws DAOException {
		List<Jogador> jogadores = new ArrayList<Jogador>();
		database = dbHelper.getWritableDatabase();
		
		String s =  "SELECT " + DbHelper.COLUNA_JOGADOR_ID + " , " + DbHelper.COLUNA_JOGADOR_NOME+ " , " +
				DbHelper.COLUNA_JOGADOR_APELIDO + " , " +DbHelper.COLUNA_JOGADOR_CLASSIFICACAO + " FROM " + DbHelper.TABELA_JOGADOR ; 
		try{
			Cursor cursor = database.rawQuery(s, null );
	    	 while(cursor.moveToNext()){
	 	    	jogadores.add(cursorToJogador(cursor));
	 		}
	 	  
		    }catch(Exception ex){
		    	throw  new DAOException("N�o foi poss�vel obter a lista de Jogadores.");
			}finally{
				if(database != null && database.isOpen()){
					database.close();
				}
			}
		return jogadores;
	
	}
	
	/** */
	public Jogador obterJogador(int id)  throws DAOException {
		Jogador jogador = new Jogador();
		database = dbHelper.getWritableDatabase();
		try{
			Cursor cursor = database.query(DbHelper.TABELA_JOGADOR, allColumns, DbHelper.COLUNA_JOGADOR_ID + " = " + 
				      id, null,null, null, null); 
	    	 if(cursor.moveToFirst()){
	 	    	jogador = cursorToJogador(cursor);
	 		}
	 	  
		    }catch(Exception ex){
		    	throw  new DAOException("N�o foi poss�vel obter o Jogador.");
			}finally{
				if(database != null && database.isOpen()){
					database.close();
				}
			}
		return jogador;
    }
	
	/** */
	public List<Jogador> obterListaJogadorApelido(String apelido) throws DAOException  {
		List<Jogador> jogadores = new ArrayList<Jogador>();
		database = dbHelper.getWritableDatabase();
		String sql = "SELECT * FROM " + DbHelper.TABELA_JOGADOR + " WHERE "+ DbHelper.COLUNA_JOGADOR_APELIDO  + " like  '%" + apelido + "%' ";
		try{
			Cursor cursor = database.rawQuery (sql, null); 
	    	while(cursor.moveToNext()){
	 	    	jogadores.add(cursorToJogador(cursor));
	    	} 	  
		    }catch(Exception ex){
		    	throw  new DAOException("N�o foi poss�vel obter os Jogadores pelo Apelido.");
			}finally{
				if(database != null && database.isOpen()){
					database.close();
				}
			}
		return jogadores;
	}
	
	/**
	 * @throws DAOException  */
	public List<Jogador> obterListaJogadorNome(String nome) throws DAOException {
		List<Jogador> jogadores = new ArrayList<Jogador>();
		database = dbHelper.getWritableDatabase();
		try{
			Cursor cursor = database.query(DbHelper.TABELA_JOGADOR, allColumns, DbHelper.COLUNA_JOGADOR_NOME + " like '%" + 
				      nome + "%' ", null,null, null, null); 
	    	while(cursor.moveToNext()){
	 	    	jogadores.add(cursorToJogador(cursor));
	    	} 	  
		    }catch(Exception ex){
		    	throw  new DAOException("N�o foi poss�vel obter os Jogadores por Nome.");
			}finally{
				if(database != null && database.isOpen()){
					database.close();
				}
			}
		return jogadores;
	}
	

}
