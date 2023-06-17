package com.example.commentintents;

import static java.net.Proxy.Type.HTTP;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn);

        EditText search_text = (EditText) findViewById(R.id.search_text);
        Button search =(Button) findViewById(R.id.btn1);

        EditText sms = (EditText) findViewById(R.id.SMS);
        EditText phone = (EditText) findViewById(R.id.phone);
        Button sendto = (Button) findViewById(R.id.sendto);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = search_text.getText().toString();
                searchWeb(s);
            }
        });

        sendto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = sms.getText().toString();
                String sms_phone = phone.getText().toString();
                composeMmsMessage(message, sms_phone);
            }
        });
    }
    public void searchWeb(String query) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeMmsMessage(String message,String phone) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("smsto:"+phone));
        intent.setType("vnd.android-dir/mms-sms");
        intent.putExtra("address",phone);
        intent.putExtra("sms_body", message);
        startActivity(intent);
    }

}