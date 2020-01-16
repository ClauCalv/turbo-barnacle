package ufabc.projeto.moduloconfigurador;

import android.Manifest;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.ActivityManager;
import android.app.Instrumentation;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.UiAutomation;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.os.Build;
import android.preference.PreferenceActivity;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.security.cert.Extension;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AtivarLeitorTela extends AppCompatActivity {

    private Button simBotao;
    private Button naoBotao;
    private Button mostrarBotao;
    private ImageView iconeBotao;
    NotificationChannel mNotificationChannel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ativar_leitor_tela);

        simBotao = (Button) findViewById(R.id.simId);
        naoBotao = (Button) findViewById(R.id.naoId);
        mostrarBotao = (Button) findViewById(R.id.mostrarId);
        iconeBotao = (ImageView) findViewById(R.id.botaoIconeId);




//  new Timer().scheduleAtFixedRate(notf, 100,5000);





        simBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              // startActivityForResult(new Intent(Settings.ACTION_CAPTIONING_SETTINGS),0); LEGENDA
                startActivity(new Intent(AtivarLeitorTela.this,VelocidadeFala.class));

               // startActivityForResult(new Intent(Settings.ACTION_DISPLAY_SETTINGS),0);  FONT TIPO NEGRITO
               
               // startActivityForResult(new Intent(Settings.ACTION_NIGHT_DISPLAY_SETTINGS),0); Night Light





                /*
                Intent intent = new Intent();
                intent.setClassName("com.android.enabled.accessibility.services",
                        "com.android.settings.Settings");
                intent.setAction(Intent.ACTION_MAIN);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT,
                        "the fragment which you want show");
                intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT_ARGUMENTS, "Leitor de tela");
                startActivity(intent);

*/



            }
        });

        naoBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AtivarLeitorTela.this,AtivarZoom.class));

            }
        });



        iconeBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.texto_info_leitor_tela);
            }
        });

        mostrarBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(AtivarLeitorTela.this,DisponivelLegendaSurdo.class));
               // startActivityForResult(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS),0);


                try {
                    Process proc = Runtime.getRuntime().exec("su");
                    DataOutputStream os = new DataOutputStream(proc.getOutputStream());

                    os.writeBytes("service call phone 5\n");
                    os.flush();

                    os.writeBytes("exit\n");
                    os.flush();

                    if (proc.waitFor() == 255) {
                        // TODO handle being declined root access
                        // 255 is the standard code for being declined root for SU
                    }
                } catch (IOException e) {
                    // TODO handle I/O going wrong
                    // this probably means that the device isn't rooted
                } catch (InterruptedException e) {
                    // don't swallow interruptions
                    Thread.currentThread().interrupt();
                }





            }
        });



    }

   

}
