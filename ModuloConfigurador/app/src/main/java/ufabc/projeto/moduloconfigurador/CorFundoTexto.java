package ufabc.projeto.moduloconfigurador;

import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CorFundoTexto extends AppCompatActivity {

    private Button salvarBotao;
    public LinearLayout l;
    public ScrollView s;
    public TextView text;
    public TextView text1;



    //fundo
    private Button B_vermelho1;
    private Button B_amarelo1;
    private Button B_ciano1;
    private Button B_branco1;
    private Button B_preto1;
    private Button B_azul1;
    private Button B_verde1;
    private Button B_roxo1;
    private Button B_rosa1;

    //texto
    private Button B_vermelho;
    private Button B_amarelo;
    private Button B_ciano;
    private Button B_branco;
    private Button B_preto;
    private Button B_azul;
    private Button B_verde;
    private Button B_roxo;
    private Button B_rosa;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cor_fundo_texto);
        l = (LinearLayout) findViewById(R.id.layoutId);
        s = (ScrollView) findViewById(R.id.scrollId);
        text = (TextView) findViewById(R.id.textView);
        text1 = (TextView) findViewById(R.id.textView1);

        //fundo
        B_vermelho1 = (Button) findViewById(R.id.vermelho1button);
        B_amarelo1 = (Button) findViewById(R.id.amarelo1button);
        B_ciano1 = (Button) findViewById(R.id.ciano1button);
        B_branco1 = (Button) findViewById(R.id.buttonbranco1);
        B_preto1 = (Button) findViewById(R.id.buttonpreto1);
        B_azul1 = (Button) findViewById(R.id.buttonazul1);
        B_verde1 = (Button) findViewById(R.id.buttonverde1);
        B_roxo1 = (Button) findViewById(R.id.buttonroxo1);
        B_rosa1 = (Button) findViewById(R.id.buttonrosa1);

        //texto
        B_vermelho = (Button) findViewById(R.id.vermelhobutton);
        B_amarelo = (Button) findViewById(R.id.amarelobutton);
        B_ciano = (Button) findViewById(R.id.cianobutton);
        B_branco = (Button) findViewById(R.id.buttonbranco);
        B_preto = (Button) findViewById(R.id.buttonpreto);
        B_azul = (Button) findViewById(R.id.buttonazul);
        B_verde = (Button) findViewById(R.id.buttonverde);
        B_roxo = (Button) findViewById(R.id.buttonroxo);
        B_rosa = (Button) findViewById(R.id.buttonrosa);



        salvarBotao = (Button) findViewById(R.id.botaoId);

        //fundo
        B_vermelho1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.setBackgroundResource(R.color.vermelho);
                s.setBackgroundResource(R.color.vermelho);

            }
        });

        B_amarelo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.setBackgroundResource(R.color.amarelo);
            }
        });
        B_ciano1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.setBackgroundResource(R.color.ciano);
            }
        });
        B_branco1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.setBackgroundResource(R.color.branco);
            }
        });
        B_preto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.setBackgroundResource(R.color.colorPrimaryDark);
            }
        });

        B_azul1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.setBackgroundResource(R.drawable.bg1);
            }
        });
        B_verde1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.setBackgroundResource(R.color.verde);
            }
        });
        B_roxo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.setBackgroundResource(R.color.roxo);
            }
        });
        B_rosa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l.setBackgroundResource(R.color.rosa);
            }
        });

        //texto
        B_vermelho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setTextColor(getResources().getColor(R.color.vermelho));
                text1.setTextColor(getResources().getColor(R.color.vermelho));

            }
        });
        B_amarelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setTextColor(getResources().getColor(R.color.amarelo));
                text1.setTextColor(getResources().getColor(R.color.amarelo));
            }
        });
        B_ciano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setTextColor(getResources().getColor(R.color.ciano));
                text1.setTextColor(getResources().getColor(R.color.ciano));
            }
        });
        B_branco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setTextColor(getResources().getColor(R.color.branco));
                text1.setTextColor(getResources().getColor(R.color.branco));
            }
        });
        B_preto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                text1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        });

        B_azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text.setTextColor(getResources().getColor(R.color.azul));
                text1.setTextColor(getResources().getColor(R.color.azul));


            }
        });
        B_verde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setTextColor(getResources().getColor(R.color.verde));
                text1.setTextColor(getResources().getColor(R.color.verde));
            }
        });
        B_roxo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setTextColor(getResources().getColor(R.color.roxo));
                text1.setTextColor(getResources().getColor(R.color.roxo));
            }
        });
        B_rosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setTextColor(getResources().getColor(R.color.rosa));
                text1.setTextColor(getResources().getColor(R.color.rosa));
            }
        });




        salvarBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                long milliseconds = 350;
                vibrator.vibrate(milliseconds);
                startActivity(new Intent(CorFundoTexto.this,AtivarZoom.class));
            }
        });
    }
}
