package br.ufabc.gravador.views;
import br.ufabc.gravador.*;
import br.ufabc.gravador.models.Gravacao;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.media.MediaRecorder;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AudioRecordActivity extends MyMenuActivity implements AnnotationsFragment.AnnotationFragmentListener {

    public static int AUDIO_REQUEST = 1111, VIDEO_REQUEST = 2222; //TODO video

    public final String start = "Iniciar Gravação", stop = "Terminar gravação", save = "Salvar Gravação"; //TODO hardcoded
    private boolean recording = false, finished = false;
    private Button startStop;
    private TextView finishedLabel, recordTimeText;
    private Gravacao gravacao = null;
    private File fileLocation, audioLocation;
    private  AnnotationsFragment fragment = null;

    // --- TIME ---
    private Handler timeHandler = null;
    private Runnable timeRunnable = new Runnable() {
        @Override
        public void run () {
            recordTime = SystemClock.uptimeMillis() - startTime;
            timeHandler.postDelayed(this, 500);
            recordTimeText.setText(Gravacao.formatTime(recordTime));
        }
    };
    private long recordTime = 0, startTime = 0;
    void startTimer(){
        startTime = SystemClock.uptimeMillis();
        timeHandler.postDelayed(timeRunnable,0);
        recordTimeText.setVisibility(View.VISIBLE);
    }
    void stopTimer(){ timeHandler.removeCallbacks(timeRunnable); }
    public long getGravacaoTime(){ return recordTime; }
    // --- END TIME ---


    // --- AUDIO RECORD ---
    private MediaRecorder recorder = null;
    private boolean startRecording() {
        String tempname = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        gravacao.setFileLocation(audioLocation.getAbsolutePath());
        gravacao.setFileName(tempname);
        gravacao.setFileExtension(".3gp");

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(gravacao.getFilePath());
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e("AudioRecord", "prepare() failed", e);
            Toast.makeText(null, "Falha em iniciar gravação", Toast.LENGTH_LONG);
            gravacao = null;
            return false;
        }

        recorder.start();
        return true;
    }
    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;
    }
    // --- END AUDIO RECORD ---

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_audio_record, R.id.my_toolbar, true);

        fileLocation = new File(getExternalFilesDir(null),"Gravacao");
        fileLocation.mkdirs();

        String tempname = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        gravacao = Gravacao.CreateEmpty(fileLocation.getAbsolutePath(), tempname);

        audioLocation = new File(fileLocation, "Audio");
        audioLocation.mkdirs();

        startStop = findViewById(R.id.startRecording);
        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { startStopOnClick(view);
            }
        });
        startStop.setText(start);

        finishedLabel = findViewById(R.id.finishedLabel);
        finishedLabel.setVisibility(View.INVISIBLE);

        timeHandler = new Handler();

        recordTimeText = findViewById(R.id.recordTimeText);
        recordTimeText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void receiveFragment ( AnnotationsFragment f ) {
        fragment = f;
    }

    @Override
    public Gravacao getGravacao () {
        return gravacao;
    }

    void startStopOnClick(View view) {
        if(!recording){
            if(startRecording()) {
                startTimer();
                startStop.setText(stop);
                recording = !recording;
            } else {
                Toast.makeText(this,"Falha em iniciar gravação", Toast.LENGTH_LONG); //TODO hardcoded
            }
        }
        else if (!finished) {
            stopRecording();
            stopTimer();
            startStop.setText(save);
            finishedLabel.setVisibility(View.VISIBLE);
            finished = true;
            gravacao.sucess();
        }
        else{
            gravacao.post();
            fragment.alertSave(true);
        }
    }

    @Override
    public void alertSaveReturn () {
        Intent intent = new Intent(this, SaveGravacaoActivity.class);
        intent.putExtra("RequestCode", AUDIO_REQUEST);
        startActivityForResult(intent, AUDIO_REQUEST);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }
        if (timeHandler != null)
            timeHandler.removeCallbacks(timeRunnable);
    }


    @Override
    public void onBackPressed() {
        if(gravacao == null || gravacao.isLastSaved())
        new AlertDialog.Builder(this)
                .setMessage("Descartar tudo? Não poderá desfazer esta ação")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == AUDIO_REQUEST)
            if(resultCode == RESULT_OK){
                setResult(RESULT_OK);
                finish();
            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gravacao.abortIfFailed();
    }
}
