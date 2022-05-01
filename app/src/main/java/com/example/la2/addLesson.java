package com.example.la2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class addLesson extends AppCompatActivity {

    Button btn_ok_lesson, btn_cancel_lesson;
    EditText et_date, et_time;
    Spinner spinner;
    DataBaseHelper dataBaseHelper;
    String name;
    Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);


        btn_cancel_lesson = findViewById(R.id.btn_cancel_lesson);
        btn_ok_lesson = findViewById(R.id.btn_ok_lesson);
        et_date = findViewById(R.id.et_date);
        et_time = findViewById(R.id.et_time);
        spinner = findViewById(R.id.spinner);
        dataBaseHelper = new DataBaseHelper(addLesson.this);
        setInitialTime();
        setInitialDate();


        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dataBaseHelper.getSpinnerData());
        // Определяем разметку для использования при выборе элемента
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime(view);
            }
        });

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate(view);
            }
        });
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
        spinner.setOnItemSelectedListener(itemSelectedListener);

        btn_ok_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Lesson lesson;

                try {
                    lesson = new Lesson(-1, name,et_date.getText().toString(), et_time.getText().toString());
                    Toast.makeText(addLesson.this, lesson.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(addLesson.this, "error", Toast.LENGTH_SHORT).show();
                    lesson = new Lesson(-1, "error", "11.11.1111", "11:11");


                }
                boolean success = dataBaseHelper.addOneLesson(lesson);
                Toast.makeText(addLesson.this, "done!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(addLesson.this, Lessons.class);
                startActivity(intent);
            }
        });

        btn_cancel_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addLesson.this, Lessons.class);
                startActivity(intent);
            }
        });
    }

    public void  setTime (View view){
        new TimePickerDialog(addLesson.this,t,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),true)
                .show();
    }

    public void setDate (View view){
        new DatePickerDialog(addLesson.this,d,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private void setInitialTime(){
        et_time.setText(DateUtils.formatDateTime(addLesson.this,calendar.getTimeInMillis(),DateUtils.FORMAT_SHOW_TIME));
    }

    public void setInitialDate(){
        et_date.setText(DateUtils.formatDateTime(addLesson.this,calendar.getTimeInMillis(),DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_SHOW_YEAR));
    }

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            calendar.set(Calendar.HOUR_OF_DAY,i);
            calendar.set(Calendar.MINUTE,i1);
            setInitialTime();
        }
    };

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendar.set(Calendar.YEAR,i);
            calendar.set(Calendar.MONTH,i1);
            calendar.set(Calendar.DAY_OF_MONTH,i2);
            setInitialDate();
        }
    };
}