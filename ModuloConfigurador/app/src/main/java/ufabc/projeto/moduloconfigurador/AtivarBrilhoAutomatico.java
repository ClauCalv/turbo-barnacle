package ufabc.projeto.moduloconfigurador;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;

public class AtivarBrilhoAutomatico extends AppCompatActivity {

    private Button simBotao;
    private Button naoBotao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ativar_brilho_automatico);

        simBotao = (Button) findViewById(R.id.simId);
        naoBotao = (Button) findViewById(R.id.naoId);


        simBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.System.putInt(getBaseContext().getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);

            }
        });

        naoBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AtivarBrilhoAutomatico.this,PontoBranco.class));
            }
        });
    }

}
