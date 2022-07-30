package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
private Button Play;
private Button Pause;
private Button Break;
private MediaPlayer mediaPlayer;
private SeekBar seekBar;
private AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Play = findViewById(R.id.Play);
        Pause = findViewById(R.id.Pause);
        Break = findViewById(R.id.Break);

     mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.vaiquecola);
     inicializarSeekBar();
    }
private void inicializarSeekBar(){
seekBar = findViewById(R.id.seekBar);
//configurar audio manager
    audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    //recupera os valoress de volume maximo e o volume atual
int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
int volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
// configura volume maximo para seekBar
    seekBar.setMax( volumeMaximo );

    //configurar volume atual
    seekBar.setProgress(volumeAtual);

    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0 );
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    });
}
    public void executarSom(View view) {
        if(mediaPlayer != null){
            mediaPlayer.start();
        }
    }
    public void pausarMusica(View view){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
    public void pararmusica(View view){
        if(mediaPlayer.isPlaying()){
 mediaPlayer.stop();
 mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.vaiquecola);
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        if( mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    protected void onStop(){
        super.onStop();
        if ( mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
    }
