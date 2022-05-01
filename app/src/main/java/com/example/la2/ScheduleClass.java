package com.example.la2;

public class ScheduleClass {
    private int id;
    private String studentName;
    private String day;
    private String time;

    public ScheduleClass(int id, String studentName, String day, String time) {
        this.id = id;
        this.studentName = studentName;
        this.day = day;
        this.time = time;
    }

    @Override
    public String toString() {
        return
               "        " + time + " | " + studentName + '\n';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
