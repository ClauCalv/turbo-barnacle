package ufabc.projeto.moduloconfigurador;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class AlterarTomVoz extends AppCompatActivity {

    private TextView textView;

    private TextToSpeech mTTS;
    private SeekBar seekBar;
    private ImageView iconeBotao;
    private ImageView iconeSom;

    private final String REQUEST_ID = ""; //Ohar pq foi alterado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_tom_voz);

        iconeSom = (ImageView) findViewById(R.id.icone_som);
        iconeBotao = (ImageView) findViewById(R.id.botaoIconeId);
        textView = (TextView) findViewById(R.id.textView);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS){
                    int resukt = mTTS.setLanguage(Locale.forLanguageTag("BR"));
                    if (resukt == TextToSpeech.LANG_MISSING_DATA
                            || resukt == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Language não suportada");
                    }else {
                        iconeSom.setEnabled(true);
                    }
                }else {
                    Log.e("TTS","Falha");
                }
            }
        });
/*
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {




            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
*/

        iconeSom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });

        iconeBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AlterarTomVoz.this,FalarPontuacao.class));
            }
        });



    }

    public void speak(){
        String text = "Alterar oo Toomm";
        float pitch = (float) seekBar.getProgress() /50;
        if (pitch < 0.1) pitch = 0.1f;
        mTTS.setPitch(pitch);
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, REQUEST_ID);
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null){
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
}
