package ufabc.projeto.moduloconfigurador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpacidadeTextoVideo extends AppCompatActivity {

    private Button simBotao;
    private Button naoBotao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opacidade_texto_video);

        simBotao = (Button) findViewById(R.id.simId);
        naoBotao = (Button) findViewById(R.id.naoId);


        simBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OpacidadeTextoVideo.this,ReconhecimentoToque.class));
            }
        });

        naoBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OpacidadeTextoVideo.this,ReconhecimentoToque.class));
            }
        });
    }
}
