package ufabc.projeto.moduloconfigurador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class ConfTamanhoFonteTexto extends AppCompatActivity {



    private Button simBotao;
    private Button naoBotao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_tamanho_fonte_texto);

        simBotao = (Button) findViewById(R.id.simId);
        naoBotao = (Button) findViewById(R.id.naoId);

        simBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfTamanhoFonteTexto.this,ExemploFonte.class));
            }
        });

        naoBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfTamanhoFonteTexto.this,AutoDescricaoMidia.class));
            }
        });
    }
}
