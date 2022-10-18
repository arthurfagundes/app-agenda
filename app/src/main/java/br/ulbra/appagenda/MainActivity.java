package br.ulbra.appagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ednome,edcpf,edtelefone;
    Button btsalvar;
    PessoaDAO dao;
    Pessoa pessoa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ednome = findViewById(R.id.ednome);
        edcpf = findViewById(R.id.edcpf);
        edtelefone = findViewById(R.id.edtelefone);
        btsalvar = findViewById(R.id.btsalvar);
        dao = new PessoaDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("pessoa")){
            pessoa = (Pessoa) it.getSerializableExtra("pessoa");
            ednome.setText(pessoa.getNome());
            edcpf.setText(pessoa.getCpf());
            edtelefone.setText(pessoa.getTelefone());
        }
    }
    public void salvar(View view){
        if(pessoa == null){
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(ednome.getText().toString());
            pessoa.setCpf(edcpf.getText().toString());
            pessoa.setTelefone(edtelefone.getText().toString());
            long id = dao.inserir(pessoa);
            Toast.makeText(this, "Pessoa inserida no ID nº:" + id,Toast.LENGTH_LONG).show();
        }else {
            pessoa.setNome(ednome.getText().toString());
            pessoa.setCpf(edcpf.getText().toString());
            pessoa.setTelefone(edtelefone.getText().toString());
            dao.atualizar(pessoa);
            Toast.makeText(this, pessoa.getNome() + ", atualizado com sucesso!", Toast.LENGTH_LONG).show();
        }
    }
}