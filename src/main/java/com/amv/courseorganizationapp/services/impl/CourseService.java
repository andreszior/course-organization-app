package com.amv.courseorganizationapp.services.impl;

import com.amv.courseorganizationapp.entities.Course;
import com.amv.courseorganizationapp.mappers.CourseMapper;
import com.amv.courseorganizationapp.mappers.TeacherMapper;
import com.amv.courseorganizationapp.services.ICourseService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CourseService implements ICourseService {

    private final CourseMapper courseMapper;

    public CourseService(CourseMapper courseMapper, TeacherMapper teacherMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public Course createCourse(Course course) {
        courseMapper.createCourse(course);
        return course;
    }

    @Override
    public boolean courseExists(Long idCourse) {
        return courseMapper.existsCourse(idCourse);
    }


    @Override
    public boolean deleteCourse(Long idCourse) {
        return courseMapper.deleteCourse(idCourse);
    }

    @Override
    public Long getMentorId(Long idCourse) {
        return courseMapper.getMentorId(idCourse);
    }

    @Override
    public boolean updateCourse(Long idCourse, Map<String, Object> updates) {
        Course course = courseMapper.getCourse(idCourse);

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    course.setName((String) value);
                    break;
                case "idTeacher":
                    course.setIdTeacher((Long) value);
                    break;
            }
        });

        return courseMapper.updateCourse(course);
    }

    @Override
    public Course getCourse(Long idCourse) {
        return courseMapper.getCourse(idCourse);
    }

}
