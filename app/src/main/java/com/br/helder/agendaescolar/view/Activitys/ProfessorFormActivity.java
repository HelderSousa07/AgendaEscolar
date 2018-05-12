package com.br.helder.agendaescolar.view.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.br.helder.agendaescolar.R;
import com.br.helder.agendaescolar.model.bean.Professor;
import com.br.helder.agendaescolar.helper.ProfessorHelper;
import com.br.helder.agendaescolar.model.dao.ProfessorDAO;

public class ProfessorFormActivity extends AppCompatActivity {

    private ProfessorHelper helper;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_form_layout);

        //configuação para exibir icone da aplicacao
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        //permissao para exibir o ícone
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //definicao do titulo da tela
        getSupportActionBar().setTitle(R.string.titulo);

        btnSalvar = findViewById(R.id.btn_salvar);
        helper = new ProfessorHelper(this);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Botão-Salvar","clicou no botão salvar");

                //solicitando serviços do helper
                Professor professor = helper.getProfessor();

                //criação do objeto DAO e inicio da conexao com o DB
                ProfessorDAO dao = new ProfessorDAO(ProfessorFormActivity.this);

                //chamada do metodo de cadastro de um novo professor
                dao.cadastrar(professor);

                //encerra conexao
                dao.close();

                //feedback para usuario com a mensagem de sucesso
                Toast.makeText(ProfessorFormActivity.this,"Professor Cadastrado: "+professor.getNome(),
                        Toast.LENGTH_SHORT).show();

                helper.setProfessor(new Professor());

                finish();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("CICLO DE VIDA", "EXECUTAR ONSTART");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CICLO DE VIDA","EXECUTAR ONRESUME");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("CICLO DE VIDA","EXECUTAR ONPAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CICLO DE VIDA","EXECUTAR ONRESUME");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("CICLO DE VIDA","EXECUTAR ONDESTROY");
    }
}
