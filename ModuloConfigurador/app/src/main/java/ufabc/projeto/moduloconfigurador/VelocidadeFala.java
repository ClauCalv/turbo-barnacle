package ufabc.projeto.moduloconfigurador;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class VelocidadeFala extends AppCompatActivity {

    private TextToSpeech mTTS;
    private TextToSpeech tts;
    private TextView textView;

    private SeekBar seekBar;
    private ImageView iconeBotao;
    private ImageView iconeSom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocidade_fala);

        iconeBotao = (ImageView) findViewById(R.id.botaoIconeId);
        iconeSom = (ImageView) findViewById(R.id.icone_som);
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
                progressBar.setProgress(progress);


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
                startActivity(new Intent(VelocidadeFala.this,EscolherVoz.class));
            }
        });

    }

    public Voice speak(){


        String text = "Configurar a velocidade de fala";
        float speed = (float) seekBar.getProgress() /50;
        if (speed < 0.1) speed = 0.1f;
        mTTS.setSpeechRate(speed);
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        return null;
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
