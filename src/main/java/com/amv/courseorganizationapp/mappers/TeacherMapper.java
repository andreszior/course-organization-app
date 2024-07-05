package com.amv.courseorganizationapp.mappers;
import com.amv.courseorganizationapp.entities.Teacher;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TeacherMapper {

    @Insert("INSERT INTO teacher (dni, name, lastname_first, lastname_second, email, phone, address) " +
            "VALUES (#{dni}, #{name}, #{lastnameFirst}, #{lastnameLast}, #{email}, #{phone}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "idteacher")
    void createTeacher(Teacher teacher);


    //@Select("SELECT t.dni FROM teacher t WHERE t.dni = #{dni}")
    @Select("SELECT EXISTS(SELECT 1 FROM teacher WHERE dni = #{dni})")
    boolean existTeacher(String dni);

}
