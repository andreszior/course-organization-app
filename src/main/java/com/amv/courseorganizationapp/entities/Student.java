package com.amv.courseorganizationapp.entities;

public class Student {

    private Long id;
    private String dni;
    private String firstName;
    private String middleName;
    private String firstSurname;
    private String lastSurname;
    private String email;
    private String phone;
    private String address;
    private String course;

    public Student() {}


    public Student(String dni, Long id, String firstName, String firstSurname, String lastSurname, String email, String phone, String address, String course) {
        this.dni = dni;
        this.id = id;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.lastSurname = lastSurname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.course = course;
    }

    public Student(Long id, String dni, String firstName, String middleName, String firstSurname, String lastSurname, String email, String phone, String address, String course) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.middleName = middleName;
        this.firstSurname = firstSurname;
        this.lastSurname = lastSurname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getLastSurname() {
        return lastSurname;
    }

    public void setLastSurname(String lastSurname) {
        this.lastSurname = lastSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
