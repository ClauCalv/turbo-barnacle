package ufabc.projeto.moduloconfigurador;

import android.content.Intent;
import android.graphics.Typeface;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TextoNegrito extends AppCompatActivity {

    private Button simBotao;
    private Button naoBotao;
    private Button testeBotao;
    private TextView Tv;
    private Button mostrarBotao;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto_negrito);

        Tv = (TextView) findViewById(R.id.textView2);
        simBotao = (Button) findViewById(R.id.simId);
        naoBotao = (Button) findViewById(R.id.naoId);
        testeBotao = (Button) findViewById(R.id.testeId);
        mostrarBotao = (Button) findViewById(R.id.mostrarId);




        simBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                simBotao.setTypeface(boldTypeface);
                startActivity(new Intent(TextoNegrito.this,ConfTamanhoFonteTexto.class));
            }
        });

        naoBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                naoBotao.setTypeface(boldTypeface);
                startActivity(new Intent(TextoNegrito.this,ConfTamanhoFonteTexto.class));
                startActivityForResult(new Intent(Settings.ACTION_DISPLAY_SETTINGS),0);
            }
        });

        testeBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                Tv.setTypeface(boldTypeface);

            }
        });

        mostrarBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TextoNegrito.this,AlterarContraste.class));
                startActivityForResult(new Intent(Settings.ACTION_DISPLAY_SETTINGS),0);

            }
        });
    }

}
