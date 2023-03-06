package com.example.appliedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class explain_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        // Find the TextView by its ID
        TextView textView1 = findViewById(R.id.textView_1);
        TextView textView2 = findViewById(R.id.textView2_1);
        TextView textView3 = findViewById(R.id.textView3_1);
        TextView textView4 = findViewById(R.id.textView4_1);

        // Set the text of the TextView


        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        int position = intent.getIntExtra("POSITION", 0);
        String title = intent.getStringExtra("TITLE");
        String subtitle = intent.getStringExtra("SUBTITLE");
        int image = intent.getIntExtra("ÏMAGE", 0);

        textView1.setText(name);
        textView2.setText(position);
        textView3.setText(title);
        textView4.setText(subtitle);
    }}


