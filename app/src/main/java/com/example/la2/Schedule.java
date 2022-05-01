package com.example.la2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Schedule extends AppCompatActivity {
    TextView tv_pon, tv_vt,tv_sr,tv_ch,tv_pt,tv_sub,tv_vos;
    Button btn_addSchedule, btn_schedule_menu;
    DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        btn_schedule_menu = findViewById(R.id.btn_schedule_menu);
        tv_pon = findViewById(R.id.tv_pon);
        tv_vt = findViewById(R.id.tv_vt);
        tv_sr = findViewById(R.id.tv_sr);
        tv_ch = findViewById(R.id.tv_ch);
        tv_pt = findViewById(R.id.tv_pt);
        tv_sub = findViewById(R.id.tv_sub);
        tv_vos = findViewById(R.id.tv_vos);
        btn_addSchedule = findViewById(R.id.btn_addSchedule);

        dataBaseHelper = new DataBaseHelper(Schedule.this);
        tv_pon.setText(dataBaseHelper.getScheduleByDay("'Понедельник'"));
        tv_vt.setText(dataBaseHelper.getScheduleByDay("'Вторник'"));
        tv_sr.setText(dataBaseHelper.getScheduleByDay("'Среда'"));
        tv_ch.setText(dataBaseHelper.getScheduleByDay("'Четверг'"));
        tv_pt.setText(dataBaseHelper.getScheduleByDay("'Пятница'"));
        tv_sub.setText(dataBaseHelper.getScheduleByDay("'Суббота'"));
        tv_vos.setText(dataBaseHelper.getScheduleByDay("'Воскресенье'"));

        btn_schedule_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Schedule.this, MainActivity.class);
                startActivity(intent);
            }
        });

        tv_pon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Schedule.this, ScheduleDelete.class);
                intent.putExtra("day","'Понедельник'");
                startActivity(intent);
            }
        });

        tv_vt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Schedule.this, ScheduleDelete.class);
                intent.putExtra("day","'Вторник'");
                startActivity(intent);
            }
        });

        tv_sr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Schedule.this, ScheduleDelete.class);
                intent.putExtra("day","'Среда'");
                startActivity(intent);
            }
        });

        tv_ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Schedule.this, ScheduleDelete.class);
                intent.putExtra("day","'Четверг'");
                startActivity(intent);
            }
        });

        tv_pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Schedule.this, ScheduleDelete.class);
                intent.putExtra("day","'Пятница'");
                startActivity(intent);
            }
        });

        tv_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Schedule.this, ScheduleDelete.class);
                intent.putExtra("day","'Суббота'");
                startActivity(intent);
            }
        });

        tv_vos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Schedule.this, ScheduleDelete.class);
                intent.putExtra("day","'Воскресенье'");
                startActivity(intent);
            }
        });

        btn_addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Schedule.this, AddSchedule.class);
                startActivity(intent);
            }
        });



    }
}