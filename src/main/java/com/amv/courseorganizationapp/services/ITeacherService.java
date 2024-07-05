package com.amv.courseorganizationapp.services;

import com.amv.courseorganizationapp.entities.Teacher;

public interface ITeacherService {

    Teacher createTeacher(Teacher teacher);

    boolean teacherExists(String dni);
}
