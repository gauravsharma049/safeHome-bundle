package com.example.safehome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.safehome.util.SwipeMovement;

public class SwipeUpDetectorActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_up_detector);
        textView = findViewById(R.id.textView5);
        textView.setText("x movement: "+ textView.getX() +"\ny movement: "+textView.getY());
        button = findViewById(R.id.button);
        new SwipeMovement(button, textView, this);
    }
}

