package com.example.la2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class Lessons extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    Button btn_add_lessons, btn_back_lessons;
    RecyclerView rv_Lessons;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lessons);

        btn_add_lessons = findViewById(R.id.btn_add_lessons);

        btn_back_lessons = findViewById(R.id.btn_back_lessons);

        dataBaseHelper = new DataBaseHelper(Lessons.this);

        rv_Lessons = findViewById(R.id.rv_Lessons);

        btn_add_lessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lessons.this, addLesson.class);
                startActivity(intent);

            }
        });

        btn_back_lessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lessons.this, MainActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rv_Lessons);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new LessonsRecyclerViewAdapter(dataBaseHelper.getEveryLesson(),Lessons.this);
        recyclerView.setAdapter(mAdapter);
    }

}