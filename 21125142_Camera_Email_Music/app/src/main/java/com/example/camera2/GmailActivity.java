package com.example.camera2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.camera2.databinding.ActivityMainBinding;

public class GmailActivity extends AppCompatActivity {
    private Button camera_button, music_player, send;
    EditText emailinput, subjectinput, contentinput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail);


        camera_button = findViewById(R.id.Camera);
        music_player = findViewById(R.id.music_player);
        send = findViewById(R.id.Send);

        emailinput = findViewById(R.id.emailinput);
        subjectinput = findViewById(R.id.subject_input);
        contentinput = findViewById(R.id.Content_input);

        camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent change_camera = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(change_camera);
            }
        });
        music_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent change_mp = new Intent(getApplicationContext(), MusicActivity.class);
                startActivity(change_mp);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailinput.getText().toString();
                String subject = subjectinput.getText().toString();
                String content = contentinput.getText().toString();

                String[] address = email.split(",");
                Intent sendemail = new Intent(Intent.ACTION_SENDTO);
                sendemail.setData(Uri.parse("mailto:"));

                sendemail.putExtra(Intent.EXTRA_EMAIL,address);
                sendemail.putExtra(Intent.EXTRA_SUBJECT,subject);
                sendemail.putExtra(Intent.EXTRA_TEXT,content);

                if (sendemail.resolveActivity(getPackageManager())!=null){
                    startActivity(sendemail);
                }
            }
        });
    }
}