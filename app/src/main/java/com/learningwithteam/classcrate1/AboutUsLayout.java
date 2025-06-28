package com.learningwithteam.classcrate1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AboutUsLayout extends AppCompatActivity {

    ImageButton btnInstaTgy, btnGmailTgy, btnLinkedInTgy, btnInstaGau, btnGmailGau, btnLinkedInGau;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_us_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_about_us), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnInstaTgy = findViewById(R.id.btnInstagramTGY);
        btnGmailTgy = findViewById(R.id.btnGmailTGY);
        btnLinkedInTgy = findViewById(R.id.btnLinkedInTGY);

        btnInstaGau = findViewById(R.id.btnInstagramGau);
        btnGmailGau = findViewById(R.id.btnGmailGau);
        btnLinkedInGau = findViewById(R.id.btnLinkedInGau);


        btnInstaTgy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.instagram.com/yatendrak111/");
            }
        });
        btnLinkedInTgy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.linkedin.com/in/yatendra-kumar-1820432a7/");
            }
        });
        btnGmailTgy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGmail("codebytgy@gmail.com");
            }
        });



        btnInstaGau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.instagram.com/gaurav224k/");
            }
        });
        btnLinkedInGau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.linkedin.com/in/gaurav-kumar-a23a05351/");
            }
        });
        btnGmailGau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGmail("70784141gauravkumar@gmail.com");
            }
        });

    }


    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private void openGmail(String emailAddress) {
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:your_email@gmail.com"));  // replace with your email
//        startActivity(intent);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + emailAddress));
        // optionally add subject or body
        // intent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
        // intent.putExtra(Intent.EXTRA_TEXT, "Body text here");
        startActivity(intent);
    }


}