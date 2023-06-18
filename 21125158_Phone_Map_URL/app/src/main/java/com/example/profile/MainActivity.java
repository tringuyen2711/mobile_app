package com.example.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Navigation bar transparent
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);;
        }
        TextView emailText = findViewById(R.id.mail);
        clickedEmail(emailText, "truongtho3345@gmail.com");

        Button phoneBtn = findViewById(R.id.phone);
        clickedPhoneBtn(phoneBtn);

        Button locationBtn = findViewById(R.id.location);
        clickedLocationBtn(locationBtn);

        Button facebookBtn = (Button) findViewById(R.id.btnFacebook);
        clickedWebBtn(facebookBtn, "https://www.facebook.com/profile.php?id=100004822392657");


        Button instagramBtn = findViewById(R.id.btnInstagram);
        clickedWebBtn(instagramBtn, "https://www.instagram.com/truongtho3345/");

        Button zaloBtn = findViewById(R.id.btnZalo);
        clickedWebBtn(zaloBtn, "https://chat.zalo.me/");

    }

    private void clickedEmail(TextView emailText, String address) {
        emailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail(address, "New mail");
            }
        });
    }

    private void clickedWebBtn(Button facebookBtn, String s) {
        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                openWebPage(s);
            }
        });
    }

    private void clickedLocationBtn(Button locationBtn) {
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                showMap("10.763109631835947","106.68252531166624");
            }
        });
    }

    private void clickedPhoneBtn(Button phoneBtn) {
        phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                dialPhoneNumber("0334593964");
            }
        });
    }

    //Ham nhan biet doi huong(Nho them android:configChanges="orientation" trong AndroidManifest)
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){

        }
        else if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){

        }
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        intent.setData(webpage);
        startActivity(intent);
    }

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    public void showMap(String lat, String lng) {
        String strUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lng + " (" + "Label which you want" + ")";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);
    }

    public void composeEmail(String addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // Only email apps handle this.
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(intent);
    }

}

