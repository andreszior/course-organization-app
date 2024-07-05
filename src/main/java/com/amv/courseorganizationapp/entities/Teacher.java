package com.amv.courseorganizationapp.entities;



public class Teacher {

    private Long id;
    private String dni;
    private String name;
    private String lastnameFirst;
    private String lastnameLast;
    private String email;
    private String phone;
    private String address;


    public Teacher() {}

    public Teacher(Long id, String dni, String name, String lastnameFirst, String lastnameLast, String email, String phone, String address) {
        //this.id = id;
        this.dni = dni;
        this.name = name;
        this.lastnameFirst = lastnameFirst;
        this.lastnameLast = lastnameLast;
        this.email = email;
        this.phone = phone;
        this.address = address;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastnameFirst() {
        return lastnameFirst;
    }

    public void setLastnameFirst(String lastnameFirst) {
        this.lastnameFirst = lastnameFirst;
    }

    public String getLastnameLast() {
        return lastnameLast;
    }

    public void setLastnameLast(String lastnameLast) {
        this.lastnameLast = lastnameLast;
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
}
