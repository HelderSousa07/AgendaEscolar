package com.br.helder.agendaescolar.helper;

import android.widget.EditText;
import android.widget.ImageView;

import com.br.helder.agendaescolar.view.Activitys.ProfessorFormActivity;
import com.br.helder.agendaescolar.R;
import com.br.helder.agendaescolar.model.bean.Professor;

public class ProfessorHelper {

    private EditText edtNome;
    private EditText edtTelefone;
    private EditText edtEndereco;
    private EditText edtSite;
    private EditText edtEmail;
    private ImageView ivFotoProfessor;

    private Professor professor;

    public ProfessorHelper(ProfessorFormActivity formulario) {

        edtNome = formulario.findViewById(R.id.edt_nome);
        edtTelefone = formulario.findViewById(R.id.edt_telefone);
        edtEndereco = formulario.findViewById(R.id.edt_endereco);
        edtSite = formulario.findViewById(R.id.edt_site);
        edtEmail = formulario.findViewById(R.id.edt_email);
        ivFotoProfessor = formulario.findViewById(R.id.iv_FotoProfessor);

        professor = new Professor();

    }

    public Professor getProfessor() {
        professor.setNome(edtNome.getText().toString());
        professor.setTelefone(edtTelefone.getText().toString());
        professor.setEndereco(edtEndereco.getText().toString());
        professor.setSite(edtSite.getText().toString());
        professor.setEmail(edtEmail.getText().toString());

        return professor;
    }

    public void setProfessor(Professor professor){

    edtNome.setText(professor.getNome());
    edtTelefone.setText(professor.getTelefone());
    edtEndereco.setText(professor.getEndereco());
    edtSite.setText(professor.getSite());
    edtEmail.setText(professor.getEmail());
    this.professor = professor;
    }

}
