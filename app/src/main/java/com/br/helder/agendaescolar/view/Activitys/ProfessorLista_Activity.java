package com.br.helder.agendaescolar.view.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.br.helder.agendaescolar.R;
import com.br.helder.agendaescolar.model.bean.Professor;
import com.br.helder.agendaescolar.model.dao.ProfessorDAO;

import java.util.ArrayList;
import java.util.List;

public class ProfessorLista_Activity extends AppCompatActivity {

    private ListView lvProfessores;
    private List<Professor> listaProfessores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_lista_layout);

        lvProfessores = findViewById(R.id.lvProfessores);

        configurarClickDaListagem();


    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarLista();
    }

    private void configurarClickDaListagem(){

        lvProfessores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ProfessorLista_Activity.this, "Clique Curto em " + listaProfessores.get(i).getNome()
                        ,Toast.LENGTH_SHORT).show();
            }
        });

        lvProfessores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(ProfessorLista_Activity.this, "Clique Longo em "+listaProfessores.get(i).getNome()
                        ,Toast.LENGTH_SHORT).show();

                return true;
            }
        });

    }

    private void carregarLista(){
        //Componentes que converte strings em views
        ArrayAdapter<String> adapter;

        //Layout da listagem
        int adapaterLayout = android.R.layout.simple_list_item_1;

        List<String> listaTemporaria = new ArrayList<>();

        //Carregar a lista a partir do banco de dados
        ProfessorDAO dao = new ProfessorDAO(this);

        listaProfessores = dao.listar();
        dao.close();

        //atualizar listagem temporária dos nomes
        for (Professor professor:listaProfessores) {
            listaTemporaria.add(professor.getNome());
        }
        //criação do adapter
        adapter = new ArrayAdapter<String>(this,adapaterLayout,listaTemporaria);

        //Associação do adapter ao ListView
        lvProfessores.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_lista_professores,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_item_novo){

            Intent intent = new Intent(this,ProfessorFormActivity.class);
            startActivity(intent);

            return true;


        }

        return super.onOptionsItemSelected(item);
    }
}
