package com.amv.courseorganizationapp.entities;

public class Subject {

    private Long id;
    private String name;
    private Long idMentor;


    public Subject() {}

    public Subject(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Subject(Long id, String name, Long idMentor) {
        this.id = id;
        this.name = name;
        this.idMentor = idMentor;
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

    public Long getIdMentor() {
        return idMentor;
    }

    public void setIdMentor(Long idMentor) {
        this.idMentor = idMentor;
    }
}
