package com.example.la2;

public class Student {
    private  int id;
    private  String name;
    private String surname;
    private int age;
    private String parentName;
    private long parentPhone;

    public Student(int id, String name, String surname, int age,String parentName, long parentPhone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", parentPhone='" + parentPhone + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public long getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(long parentPhone) {
        this.parentPhone = parentPhone;
    }
}
