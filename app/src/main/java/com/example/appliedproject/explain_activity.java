package com.example.appliedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class explain_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        // Find the TextView by its ID
        TextView textView1 = findViewById(R.id.textView_1);
        TextView textView2 = findViewById(R.id.textView2_1);
        TextView textViewExplain = findViewById(R.id.explain_text);
        ImageView image = findViewById(R.id.image_explain);

        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String title = intent.getStringExtra("TITLE");
        String subtitle = intent.getStringExtra("SUBTITLE");
        String explain = intent.getStringExtra("EXPLAIN");
        int image_source = intent.getIntExtra("IMAGE", 0);

        // Set the text of the TextView
        textView1.setText(title);
        textView2.setText(subtitle);
        textViewExplain.setText(explain);
        image.setImageResource(image_source);
    }}


