package com.example.createnote;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.actions.NoteIntents;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(save);
    }

    View.OnClickListener save = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText title = findViewById(R.id.title);
            EditText text = findViewById(R.id.text);
            createNote(title.getText().toString(), text.getText().toString());
        }
    };

    public void createNote(String subject, String text) {
        Intent intent = new Intent(NoteIntents.ACTION_CREATE_NOTE)
                .putExtra(NoteIntents.EXTRA_NAME, subject)
                .putExtra(NoteIntents.EXTRA_TEXT, text);
        startActivity(intent);
    }
}