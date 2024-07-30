package com.amv.courseorganizationapp.mappers;

import com.amv.courseorganizationapp.entities.Subject;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SubjectMapper {

    @Insert("INSERT INTO subject (subject_name, subject_teacher) VALUES " +
            "(#{name}, #{idTeacher})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "subject_id")
    void createSubject(Subject subject);

    @Select("SELECT EXISTS((SELECT 1 FROM subject WHERE subject_id = #{id}))")
    boolean subjectExists(Long id);

    @Update("UPDATE subject SET subject_name = #{name}, subject_teacher = #{idTeacher} WHERE subject_id=#{id}")
    boolean updateSubject(Subject subject);

    @Delete("DELETE FROM subject WHERE subject_id = #{id}")
    boolean deleteSubject(Long id);

    @Select("SELECT subject_id AS id, subject_name AS name, subject_teacher AS idTeacher FROM subject WHERE subject_id=#{id}")
    Subject getSubjectById(Long id);

}

