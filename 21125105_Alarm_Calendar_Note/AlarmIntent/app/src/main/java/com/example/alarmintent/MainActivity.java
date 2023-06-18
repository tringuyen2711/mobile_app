package com.example.alarmintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlarmScreen();
    }

    private void AlarmScreen() {
        setContentView(R.layout.activity_main);
        Button setButton = findViewById(R.id.setAlarmButton);
        setButton.setOnClickListener(setAlarm);
        Button showButton = findViewById(R.id.showAlarmButton);
        showButton.setOnClickListener(showAlarm);
        Button switchTimer = findViewById(R.id.switchToTimer);
        switchTimer.setOnClickListener(switchToTimer);
    }

    View.OnClickListener setAlarm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TimePicker time = findViewById(R.id.time);
            EditText editMessage = findViewById(R.id.message);
            String message = editMessage.getText().toString();
            createAlarm(message, time.getHour(),time.getMinute());
        }
    };

    View.OnClickListener showAlarm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
            startActivity(intent);
        }
    };

    View.OnClickListener switchToTimer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.timer);
            Button setButton = findViewById(R.id.setTimerButton);
            setButton.setOnClickListener(setTimer);
            Button switchAlarm = findViewById(R.id.switchToAlarm);
            switchAlarm.setOnClickListener(switchToAlarm);


            NumberPicker seconds = findViewById(R.id.numpicker_seconds);
            seconds.setMinValue(0);
            seconds.setMaxValue(3600);

        }
    };

    View.OnClickListener switchToAlarm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlarmScreen();
        }
    };

    View.OnClickListener setTimer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NumberPicker seconds = findViewById(R.id.numpicker_seconds);
            startTimer(seconds.getValue());
        }
    };


    public void startTimer(int seconds) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        startActivity(intent);
    }

    public void createAlarm(String message, int hour, int minutes) {

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        startActivity(intent);
    }
}

