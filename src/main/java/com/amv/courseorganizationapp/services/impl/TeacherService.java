package com.amv.courseorganizationapp.services.impl;

import com.amv.courseorganizationapp.entities.Teacher;
import com.amv.courseorganizationapp.mappers.TeacherMapper;
import com.amv.courseorganizationapp.services.ITeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherService implements ITeacherService {

    private final TeacherMapper teacherMapper;

    public TeacherService(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        teacherMapper.createTeacher(teacher);
        return teacher;
    }

    @Override
    public boolean teacherExists(String dni) {
        return teacherMapper.existTeacher(dni);
    }
}
