package com.example.la2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_Students, btn_Lessons, btn_Schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Students = findViewById(R.id.btn_add_lessons);
        btn_Lessons = findViewById(R.id.btn_Lessons);
        btn_Schedule = findViewById(R.id.btn_Schedule);

        btn_Students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_add_lessons:
                        Intent intent = new Intent(MainActivity.this, Students.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });

        btn_Lessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_Lessons:
                        Intent intent = new Intent(MainActivity.this, Lessons.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });

        btn_Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Schedule.class);
                startActivity(intent);
            }
        });
    }
}