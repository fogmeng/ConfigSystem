package com.hoho.mapper;

import com.hoho.entity.System;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Silence on 2016/9/12.
 */
@Mapper
public interface SystemMapper {

    @Select("SELECT count(*) FROM system")
    int count();

    @Select("SELECT count(*) FROM system WHERE name_en = #{nameEn}")
    int countByNameEn(@Param("nameEn") String nameEn);

    @Select("SELECT * FROM system where id = #{id}")
    System findById(@Param("id") long id);

    @Select("SELECT * FROM system where name_en = #{nameEn}")
    System findByNameEn(@Param("nameEn") String nameEn);

    @Select("SELECT * FROM system LIMIT #{begin},#{end}")
    List<System> findAll(@Param("begin") long begin, @Param("end") long end);

    @Select("<script>SELECT * FROM system where 1 = 1"
            +"<if test='nameEn != \"\"'> and name_en = #{nameEn} </if>"
            +"<if test='name != \"\"'> and name = #{name} </if> LIMIT #{begin},#{end} </script>")
    List<System> findBySearch(@Param("nameEn") String nameEn, @Param("name") String name, @Param("begin") long begin, @Param("end") long end);

    @Select("<script>SELECT count(*) FROM system where 1 = 1"
            +"<if test='nameEn != \"\"'> and name_en = #{nameEn} </if>"
            +"<if test='name != \"\"'> and name = #{name} </if></script>")
    int countBySearch(@Param("nameEn") String nameEn, @Param("name") String name);

    @Insert("INSERT INTO system(name_en, name, description, project_id) "+
            "VALUES(#{nameEn}, #{name},#{description}, #{projectId})")
    int insert(System system);

    @Update("UPDATE system SET name_en = #{nameEn}, name = #{name}, description = #{description}, project_id = #{projectId} WHERE id = #{id}")
    int update(System system);

    @Delete("DELETE FROM system WHERE id = #{id}")
    int deleteById(@Param("id") long id);

}
