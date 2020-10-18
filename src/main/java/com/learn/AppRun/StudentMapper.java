package com.learn.AppRun;

import com.learn.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {
    int insertStudent(Student student);
    Student findBySno(@Param("sno") int sno);
    int deleteBySno(@Param("sno") int sno);
    int updateAgeBySno(@Param("sno") int sno, @Param("age") int age);
}
