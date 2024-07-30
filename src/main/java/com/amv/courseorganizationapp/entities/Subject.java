package com.amv.courseorganizationapp.entities;

public class Subject {

    private Long id;
    private String name;
    private Long idTeacher;


    public Subject() {}

    public Subject(String name) {
        this.name = name;
    }

    public Subject(String name, Long idTeacher) {
        this.name = name;
        this.idTeacher = idTeacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Long idTeacher) {
        this.idTeacher = idTeacher;
    }
}
