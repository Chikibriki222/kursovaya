package com.example.la2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addStudent extends AppCompatActivity {

    Button btn_ok_student, btn_cancel_student;
    EditText et_name, et_surname, et_age, et_parentName, et_parentPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        btn_cancel_student = findViewById(R.id.btn_cancel_student);
        btn_ok_student = findViewById(R.id.btn_ok_student);
        et_name = findViewById(R.id.et_name2);
        et_surname = findViewById(R.id.et_surname2);
        et_age = findViewById(R.id.et_age2);
        et_parentName = findViewById(R.id.et_parentName2);
        et_parentPhone = findViewById(R.id.et_parentPhone2);

        btn_cancel_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addStudent.this, Students.class);
                startActivity(intent);
            }
        });

        btn_ok_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Student student;

                try {
                    student = new Student(-1, et_name.getText().toString(),et_surname.getText().toString(),Integer.parseInt(et_age.getText().toString()), et_parentName.getText().toString(), Long.parseLong(et_parentPhone.getText().toString()));
                    Toast.makeText(addStudent.this, student.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(addStudent.this, "error", Toast.LENGTH_SHORT).show();
                    student = new Student(-1, "error", "error", 0, "error", 0);


                }



                DataBaseHelper dataBaseHelper = new DataBaseHelper(addStudent.this);

                boolean success = dataBaseHelper.addOneStudent(student);
                Toast.makeText(addStudent.this, "done!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(addStudent.this, Students.class);
                startActivity(intent);


            }
        });
    }
}