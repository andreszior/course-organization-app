package com.amv.courseorganizationapp.services;

import com.amv.courseorganizationapp.entities.Course;
import com.amv.courseorganizationapp.entities.Teacher;

import java.util.Map;

public interface ICourseService {

    Course createCourse(Course course);

    boolean courseExists(Long idCourse);
    boolean deleteCourse(Long idCourse);
    Long getMentorId(Long idCourse);
    boolean updateCourse(Long idCourse, Map<String, Object> updates);

    Course getCourse(Long idCourse);
}