package com.example.la2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddSchedule extends AppCompatActivity {
    EditText et_Schedule_Time;
    Spinner spinner_Student_schedule, spinner_Day_schedule;
    Button btn_cancel_schedule, btn_ok_schedule;
    DataBaseHelper dataBaseHelper;
    String name;
    String day;
    String[] week = {"Понедельник", "Вторник","Среда","Четверг","Пятница","Суббота","Воскресенье"};
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        et_Schedule_Time = findViewById(R.id.et_Schedule_Time);
        spinner_Day_schedule = findViewById(R.id.spinner_Day_schedule);
        spinner_Student_schedule = findViewById(R.id.spinner_Student_schedule);
        dataBaseHelper = new DataBaseHelper(AddSchedule.this);
        btn_ok_schedule = findViewById(R.id.btn_ok_schedule);
        btn_cancel_schedule = findViewById(R.id.btn_cancel_schedule);
        setInitialTime();

        et_Schedule_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime(view);
            }
        });

        btn_cancel_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddSchedule.this, Schedule.class);
                startActivity(intent);
            }
        });



        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dataBaseHelper.getSpinnerData());

        ArrayAdapter<String> adapterDay = new ArrayAdapter(this, android.R.layout.simple_spinner_item, week);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner_Day_schedule.setAdapter(adapterDay);
        spinner_Student_schedule.setAdapter(adapter);

        AdapterView.OnItemSelectedListener DaySelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String)adapterView.getItemAtPosition(i);
                day = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                name = "error";

            }
        };
        spinner_Day_schedule.setOnItemSelectedListener(DaySelectedListener);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item = (String)adapterView.getItemAtPosition(i);
                name = item;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                name = "error";
            }
        };
        spinner_Student_schedule.setOnItemSelectedListener(itemSelectedListener);
        btn_ok_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScheduleClass scheduleClass;

                try {
                    scheduleClass = new ScheduleClass(-1, name,day, et_Schedule_Time.getText().toString());
                    Toast.makeText(AddSchedule.this, scheduleClass.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(AddSchedule.this, "error", Toast.LENGTH_SHORT).show();
                    scheduleClass = new ScheduleClass(-1, "error", "Понедельник", "11:11");


                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(AddSchedule.this);
                boolean success = dataBaseHelper.addOneToSchedule(scheduleClass);
                Toast.makeText(AddSchedule.this, "done!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddSchedule.this, Schedule.class);
                startActivity(intent);
            }
        });
    }


    public void  setTime (View view){
        new TimePickerDialog(AddSchedule.this,t,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),true)
                .show();
    }

    private void setInitialTime(){
        et_Schedule_Time.setText(DateUtils.formatDateTime(AddSchedule.this,calendar.getTimeInMillis(),DateUtils.FORMAT_SHOW_TIME));
    }

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            calendar.set(Calendar.HOUR_OF_DAY,i);
            calendar.set(Calendar.MINUTE,i1);
            setInitialTime();
        }
    };
}
