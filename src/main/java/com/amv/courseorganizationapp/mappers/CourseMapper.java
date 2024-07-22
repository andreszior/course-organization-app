package com.amv.courseorganizationapp.mappers;
import com.amv.courseorganizationapp.entities.Course;
import com.amv.courseorganizationapp.entities.Teacher;
import org.apache.ibatis.annotations.*;


@Mapper
public interface CourseMapper {

    @Insert("INSERT INTO course (course_id, course_name, course_mentor)" +
    "VALUES (#{id}, #{name}, #{idTeacher})")
    void createCourse(Course course);

    @Select("SELECT  EXISTS(SELECT 1 FROM course WHERE course_id=#{id})")
    boolean existsCourse(Long id);

    @Delete("DELETE FROM course WHERE id_course=#{id}")
    boolean deleteCourse(Long id);

    @Update( "UPDATE course SET course_id= #{id}, course_name=#{name}, course_mentor=#{idTeacher}" +
            "WHERE course_id={id}")
    boolean updateCourse(Course course);

    @Select("SELECT course_mentor as idTeacher FROM course c INNER JOIN teacher t " +
            "on c.course_mentor = t.idteacher WHERE c.course_id=#{idCourse}")
    Long getMentorId(Long idCourse);

    @Select("SELECT course_id AS id, course_name AS name, course_mentor AS idTeacher FROM course WHERE course_id=#{id}")
    Course getCourse(Long id);
}