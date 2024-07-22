package com.amv.courseorganizationapp.entities;

public class Course {

    private Long id;
    private String name;
    private Long idTeacher;

    public Course() {}

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course(Long id, String name, Long idTeacher) {
        this.id = id;
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
