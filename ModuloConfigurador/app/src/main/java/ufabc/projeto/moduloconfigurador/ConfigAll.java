package ufabc.projeto.moduloconfigurador;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

import me.anwarshahriar.calligrapher.Calligrapher;

public abstract class ConfigAll extends AppCompatActivity implements TextToSpeech.OnInitListener{



    //Valores das configurações
    public static final String KEY_COR_TELA = "cor_tela";
    public static final String KEY_COR_TEXTO = "cor_texto";
    public static final String KEY_NEGRITO = "negrito";
    public static final String KEY_ATIVAR_LEITOR_TELA = "leitor_tela";
    private static final String KEY_VELOCIDADE_FALA = "velocidade_fala";
    private static final String KEY_TOM_FALA = "tom_fala";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_tamanho_fonte_texto);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    }


}
