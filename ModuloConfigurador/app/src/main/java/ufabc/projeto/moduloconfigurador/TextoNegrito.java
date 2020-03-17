package ufabc.projeto.moduloconfigurador;

import android.content.Intent;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Locale;
import me.anwarshahriar.calligrapher.Calligrapher;

public class TextoNegrito extends ConfigAll implements TextToSpeech.OnInitListener {

    private Button simBotao;
    private Button naoBotao;
    private Button testeBotao;
    private Button mostrarBotao;

    //Cor do fundo e texto
    private int corTela;
    private int corText;
    private LinearLayout linearLayout;
    private TextView Tv;
    private String fonte;
    //TTS mostrando a velocidade e tom
    private TextToSpeech textToSpeech;
    public Boolean teste;
    public float testeVelocidadeFala;
    public float testeTomFala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto_negrito);

        Tv = (TextView) findViewById(R.id.textView2);
        simBotao = (Button) findViewById(R.id.simId);
        naoBotao = (Button) findViewById(R.id.naoId);
        testeBotao = (Button) findViewById(R.id.testeId);
        mostrarBotao = (Button) findViewById(R.id.mostrarId);
        linearLayout = (LinearLayout) findViewById(R.id.layoutId);

        //cor texto e fundo
        if(sharedPreferences.getInt(KEY_COR_TELA, corTela) != 0) {
            corTela = sharedPreferences.getInt(KEY_COR_TELA, corTela);
            linearLayout.setBackgroundColor(corTela);
        }
        if (sharedPreferences.getInt(KEY_COR_TEXTO, corText) != 0){
        corText = sharedPreferences.getInt(KEY_COR_TEXTO, corText);
        Tv.setTextColor(corText);
        }
        //som
        teste = ((ConfigHelper) this.getApplication()).getAtivarOLeitorTela();
        testeVelocidadeFala = ((ConfigHelper) this.getApplication()).getVelocidadeFala();
        testeTomFala = ((ConfigHelper) this.getApplication()).getTomFala();

        if (teste != false) {
            textToSpeech = new TextToSpeech(this,  this);
        }

        simBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                long milliseconds = 350;
                vibrator.vibrate(milliseconds);

                Calligrapher calligrapher = new Calligrapher(TextoNegrito.this);
                calligrapher.setFont(TextoNegrito.this, "futura-extra-bold-italic.ttf", true);
                Log.i("@testeNegrito", "NegritoTexto - "+calligrapher);
                fonte = "futura-extra-bold-italic.ttf";
                click(fonte);

                startActivity(new Intent(TextoNegrito.this,ConfTamanhoFonteTexto.class));
            }
        });

        naoBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                long milliseconds = 350;
                vibrator.vibrate(milliseconds);
                fonte = null;
                click(fonte);
                startActivity(new Intent(TextoNegrito.this,ConfTamanhoFonteTexto.class));
               // startActivityForResult(new Intent(Settings.ACTION_DISPLAY_SETTINGS),0); teste de ir para as configurações direto do celular
            }
        });


        testeBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calligrapher calligrapher = new Calligrapher(TextoNegrito.this);
                calligrapher.setFont(TextoNegrito.this, "futura-extra-bold-italic.ttf", true);

            }
        });

    }

    public void click(String fonte){
        //Para armazenar um valor
        sharedPreferences.edit().putString(KEY_NEGRITO, fonte).commit();
    }
    @Override
    public void onInit(int status) { //FALANDO assim que abre a atividade
        String falar = "Deseja colocar o texto em negrito"+ "\n Botão de teste" + "\n Botão sim" + "\n Botão não";
        if (status != TextToSpeech.ERROR) {
            textToSpeech.setLanguage(Locale.getDefault());
            textToSpeech.setSpeechRate(testeVelocidadeFala);
            textToSpeech.setPitch(testeTomFala);
            textToSpeech.speak(falar, TextToSpeech.QUEUE_FLUSH, null, null);
        }

    }

}
