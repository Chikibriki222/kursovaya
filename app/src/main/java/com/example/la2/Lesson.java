package com.example.la2;

public class Lesson {
    private int id;
    private String studentName;
    private String date;
    private String time;

    public Lesson(int id, String studentName, String date, String time) {
        this.id = id;
        this.studentName = studentName;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
