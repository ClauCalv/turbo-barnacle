package ufabc.projeto.moduloconfigurador;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;


import android.view.View.OnClickListener;
import android.widget.Toast;


public class ExemploFonte extends AppCompatActivity {



    private TextView textView;

    private SeekBar seekBar;
    private ImageView iconeBotao;

    private SharedPreferences prefs;
    private Button btn;

    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private Typeface tf1, tf2, tf3, tf4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_fonte);
        iconeBotao = (ImageView) findViewById(R.id.botaoIconeId);
        btn = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.textView11);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        t1 = (TextView) findViewById(R.id.textView4);
        t2 = (TextView) findViewById(R.id.textView);
        t3 = (TextView) findViewById(R.id.textView1);
        t4 = (TextView) findViewById(R.id.textView2);

        tf1 = Typeface.createFromAsset(getAssets(), "caveat-regular-maisfontes.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "courier-new.ttf");
        tf3 = Typeface.createFromAsset(getAssets(), "lobster.ttf");
        tf4 = Typeface.createFromAsset(getAssets(), "arial.ttf");

        t1.setTypeface(tf1);
        t2.setTypeface(tf2);
        t3.setTypeface(tf3);
        t4.setTypeface(tf4);



        btn.setOnClickListener(new OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {


                //Settings.System.putString(getBaseContext().getContentResolver(),
                       // Settings.System.FONT_SCALE, "courier-new.ttf");

                // android.provider.Settings.System.getFloat(getBaseContext().getContentResolver(),
                        //testar android.provider.Settings.System.FONT_SCALE, 1f);
                        Settings.System.putInt(getBaseContext().getContentResolver(),
                        Settings.System.WINDOW_ANIMATION_SCALE,0);//pegou

            }
        });


        SharedPreferences prefs = getSharedPreferences("test", MODE_PRIVATE);
        prefs = getPreferences(MODE_PRIVATE);

        float fs = prefs.getFloat("FontSize", 12);

        seekBar.setProgress((int)fs);

        textView.setTextSize(seekBar.getProgress());




       // Settings.System.putFloat(getBaseContext().getContentResolver(),
              //  Settings.System.FONT_SCALE,(float)seekBar.getProgress()/100);

        int brilho = Settings.System.getInt(getContentResolver(),
                Settings.System.FONT_SCALE,0);
        seekBar.setProgress(brilho);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Context context = getApplicationContext();
                boolean canWrite = Settings.System.canWrite(context);
                if(canWrite) {


                           // Settings.System.putFloat(getBaseContext().getContentResolver(),
                            //Settings.System.FONT_SCALE, (float) seekBar.getProgress() / 50);
                    Settings.System.putFloat(getBaseContext().getContentResolver(),
                            Settings.System.FONT_SCALE, (float) seekBar.getProgress() / 50);

                    //textView.setTextSize(seekBar.getProgress() / 50);

                }else {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    context.startActivity(intent);
                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        iconeBotao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                long milliseconds = 350;
                vibrator.vibrate(milliseconds);
                startActivity(new Intent(ExemploFonte.this, AutoDescricaoMidia.class));
            }
        });





    }



    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();




    }
}
