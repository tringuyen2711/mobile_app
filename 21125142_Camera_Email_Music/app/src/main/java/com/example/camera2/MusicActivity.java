package com.example.camera2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MusicActivity extends AppCompatActivity {
    Button switch_cam, playing_music;
    private static final int REQUEST_CODE_FILE_PICKER = 1;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        switch_cam = findViewById(R.id.Camera);
        playing_music = findViewById(R.id.play_music);

        switch_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myint);
            }
        });

        playing_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickMp3File();
            }
        });
    }

    public void pickMp3File() {
        Intent mp3intent = new Intent(Intent.ACTION_GET_CONTENT);
        mp3intent.setType("audio/mpeg"); // MIME type for MP3 files
        mp3intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityMP3Intent.launch(mp3intent);
        } catch (ActivityNotFoundException e) {
            // Handle exception if no file picker app is available
            e.printStackTrace();
        }
    }

    private void playMedia(String fileUri) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                .build());
        // this is for changing songs without overlaps.


        try {
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(fileUri));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ActivityResultLauncher<Intent> startActivityMP3Intent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK && REQUEST_CODE_FILE_PICKER == 1) {
                Intent intent = result.getData();
                assert intent != null;
                Uri selectedFileUri = intent.getData();

                if (selectedFileUri != null) {
                    // Process the selected MP3 file
                    playMedia(selectedFileUri.toString());
                }
            }
        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}





