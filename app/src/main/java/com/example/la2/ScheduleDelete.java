package com.example.la2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScheduleDelete extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    Button btn_schedule_del_back;
    RecyclerView rv_schedule_del;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_delete);

        dataBaseHelper = new DataBaseHelper(ScheduleDelete.this);

        btn_schedule_del_back = findViewById(R.id.btn_schedule_del_back);
        rv_schedule_del = findViewById(R.id.rv_schedule_del);

        btn_schedule_del_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleDelete.this, Schedule.class);
                startActivity(intent);
            }
        });



        recyclerView = (RecyclerView) findViewById(R.id.rv_schedule_del);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        String day = intent.getStringExtra("day");

        mAdapter = new ScheduleRecyclerVievAdapter(dataBaseHelper.getEveryScheduleForDelete(day),ScheduleDelete.this);
        recyclerView.setAdapter(mAdapter);
    }
}