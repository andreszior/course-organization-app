package com.amv.courseorganizationapp.entities;

public class Student extends Person {

    private String course;

    public Student() {}

    public Student(String dni, String firstName, String firstSurname, String lastSurname, String email, String phone, String address, String course) {
        super(dni, firstName, firstSurname, firstSurname, email, phone, address);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
