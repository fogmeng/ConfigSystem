package com.hoho.mapper;

import com.hoho.entity.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Silence on 2016/9/19.
 */
@Mapper
public interface ProjectMapper {

    @Select("SELECT count(*) FROM project")
    int count();

    @Select("SELECT count(*) FROM project WHERE name_en = #{nameEn}")
    int countByNameEn(@Param("nameEn") String nameEn);

    @Select("<script>SELECT count(*) FROM project where 1 = 1"
            +"<if test='nameEn != \"\"'> and name_en = #{nameEn} </if>"
            +"<if test='status != \"\"'> and status = #{status} </if>"
            +"<if test='name != \"\"'> and name = #{name} </if></script>")
    int countBySearch(@Param("nameEn") String nameEn, @Param("name") String name, @Param("status") String status);

    @Select("<script>SELECT * FROM project where 1 = 1"
            +"<if test='nameEn != \"\"'> and name_en = #{nameEn} </if>"
            +"<if test='status != \"\"'> and status = #{status} </if>"
            +"<if test='name != \"\"'> and name = #{name} </if> LIMIT #{begin},#{end} </script>")
    List<Project> findBySearch(@Param("nameEn") String nameEn, @Param("name") String name, @Param("status") String status, @Param("begin") long begin, @Param("end") long end);

    @Select("SELECT * FROM project where id = #{id}")
    Project findById(@Param("id") long id);

    @Select("SELECT * FROM project LIMIT #{begin},#{end}")
    List<Project> findAll(@Param("begin") long begin, @Param("end") long end);

    @Insert("INSERT INTO project(name_en, name, description, status)"+
            "VALUES(#{nameEn}, #{name}, #{description}, #{status})")
    int insert(Project project);

    @Delete("DELETE FROM project WHERE id = #{id}")
    int deleteById(@Param("id") long id);

    @Update("UPDATE project SET name_en = #{nameEn}, name = #{name}, status = #{status}, description = #{description} where id = #{id}")
    int update(Project project);
}
