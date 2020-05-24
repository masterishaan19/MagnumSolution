package com.example.ishaanGarg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Intent intent = getIntent();
        setTitle(intent.getStringExtra("title"));
        TextView text = findViewById(R.id.textView3);
        text.setText(intent.getStringExtra("message"));
    }
}
