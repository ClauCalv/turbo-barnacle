package br.ufabc.gravador.views;
import br.ufabc.gravador.*;
import br.ufabc.gravador.models.Gravacao;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class NameToSaveActivity extends MyMenuActivity {

    Button saveRecordName;
    TextView recordName;
    Gravacao gravacao;
    int requestCode;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_name_to_save,R.id.my_toolbar,true);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            gravacao = Gravacao.postedInstance;
            requestCode = extras.getInt("RequestCode");
        }

        saveRecordName = findViewById(R.id.saveRecordName);
        saveRecordName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { saveRecordNameOnClick(view);
            }
        });

        recordName = findViewById(R.id.recordName);
        recordName.setText(gravacao.getFileName());

    }

    void saveRecordNameOnClick(View view) {
        if(!Pattern.matches("\\w( \\w|\\w)*", recordName.getText())) {
            Toast.makeText(this, "Nome vazio ou inválido", Toast.LENGTH_LONG).show();
            return;
        }
        if(!gravacao.renameAndSave(recordName.getText().toString())){
            Toast.makeText(this, "Falha em salvar. Nome já existente?", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK);
        finish();
    }
}
