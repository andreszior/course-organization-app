package com.amv.courseorganizationapp.entities;



public class Teacher extends Person{

    public Teacher() {}

    public Teacher(String dni, String name, String firstSurname, String lastSurname, String email, String phone, String address) {
        //this.id = id;
        super(dni, name, firstSurname, lastSurname, email, phone, address);
    }

}
