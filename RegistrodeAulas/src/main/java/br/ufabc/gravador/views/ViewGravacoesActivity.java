package br.ufabc.gravador.views;
import br.ufabc.gravador.*;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ViewGravacoesActivity extends AppCompatActivity {

    public static String loading ="Recuperando Gravações", loaded = "Gravacoes existentes:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recordings);
    }
}
