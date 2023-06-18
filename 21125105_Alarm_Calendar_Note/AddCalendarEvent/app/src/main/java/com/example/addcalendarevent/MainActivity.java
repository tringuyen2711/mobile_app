package com.example.addcalendarevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addButton = findViewById(R.id.addEventButton);
        addButton.setOnClickListener(addEventToCalendar);

    }

    View.OnClickListener addEventToCalendar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText editName = findViewById(R.id.title);
            EditText editLoc = findViewById(R.id.location);
            DatePicker date = findViewById(R.id.date);
            TimePicker time = findViewById(R.id.time);

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
            cal.set(Calendar.MONTH, date.getMonth());
            cal.set(Calendar.YEAR, date.getYear());
            cal.set(Calendar.HOUR, time.getHour());
            cal.set(Calendar.MINUTE, time.getMinute());

            addEvent(editName.getText().toString(), editLoc.getText().toString(), cal.getTimeInMillis());
        }
    };
    public void addEvent(String title, String location, long begin) {
        Intent intent = new Intent(Intent.ACTION_EDIT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin);
        startActivity(intent);
    }
}

