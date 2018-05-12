package com.br.helder.agendaescolar.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.br.helder.agendaescolar.model.bean.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String TABELA = "Professor";
    private static final String BANCO_DE_DADOS = "AgendaEscolar";
    //constante para logcat
    private static final String TAG_LOGCAT = "PROFESSOR-DAO";


    public ProfessorDAO(Context context){

        super(context,BANCO_DE_DADOS,null,VERSAO);
        Log.d(TAG_LOGCAT,"método construtor");

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Instrução DDL a ser executada
        String ddl = "CREATE TABLE " + TABELA + "( " +
                " id INTEGER PRIMARY KEY," +
                " nome TEXT, telefone TEXT, endereco TEXT," +
                " site TEXT, email TEXT, foto TEXT)";
        //execução da instrução do sqlite
        sqLiteDatabase.execSQL(ddl);
        Log.i(TAG_LOGCAT,"Criação da Tabela: "+TABELA );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Instrução DDL para destruir a tabela
        String ddl = "DROP TABLE IF EXISTS " + TABELA;
        //Execuçao da instrução no SQLite
        sqLiteDatabase.execSQL(ddl);
        //chamada do metodo de reconstrução
        onCreate(sqLiteDatabase);
        Log.i(TAG_LOGCAT, "Atualização da versão da tabela: "+TABELA);

    }

    public void cadastrar(Professor professor){
        //objeto para armazenar os valores dos campos
        ContentValues values = new ContentValues();

        //definição de valores dos campos da tabela
        values.put("nome", professor.getNome());
        values.put("telefone", professor.getTelefone());
        values.put("endereco", professor.getEndereco());
        values.put("site", professor.getSite());
        values.put("email", professor.getEmail());
        values.put("foto", professor.getFoto());
        //inserir dados do professor no DB
        getWritableDatabase().insert(TABELA,null,values);
        Log.i(TAG_LOGCAT, "Professor Cadastrado: "+ professor.getNome());

    }

    public void excluir(Professor professor){

        String[] args = {professor.getId().toString()};

        getWritableDatabase().delete(TABELA,"id=?",args);

        Log.i(TAG_LOGCAT, "Professor Excluído: "+professor.getNome());

    }

   public List<Professor> listar(){
        //definição da coleção de professores
       List<Professor> lista = new ArrayList<>();

       //Definição da instrução SQL
       String sql = "select * from " +TABELA+ " order by nome";

       //Cursor que recebe os registros do banco de dados
       Cursor cursor = getReadableDatabase().rawQuery(sql,null);

       try {
           //percorre os registros do banco de dados
           while (cursor.moveToNext()){

               //criação da nova referência para professor
               Professor professor = new Professor();
               //Carregar os atributos de professor com campos de tabela
               professor.setId(cursor.getLong(0));
               professor.setNome(cursor.getString(1));
               professor.setTelefone(cursor.getString(2));
               professor.setEndereco(cursor.getString(3));
               professor.setSite(cursor.getString(4));
               professor.setEmail(cursor.getString(5));
               professor.setFoto(cursor.getString(6));

               lista.add(professor);

           }

       }catch (android.database.SQLException e){
           Log.e(TAG_LOGCAT,e.getMessage());
       }finally {
           //Fecha a lista de registros do DB
            cursor.close();
       }
        return lista;
   }


}
