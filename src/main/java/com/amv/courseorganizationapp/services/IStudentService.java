package com.amv.courseorganizationapp.services;

import com.amv.courseorganizationapp.entities.Student;

import java.util.Map;

public interface IStudentService {

    Student createStudent(Student student);

    boolean studentExists(String dni);

    boolean deleteStudent(String dni);

    boolean updateStudent(String dni, Map<String, Object> updates);

    Student getStudent(String dni);

    Student getStudentById(Long id);
}
