package com.hoho.mapper;

import com.hoho.entity.Config;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Silence on 2016/9/12.
 */
@Mapper
public interface ConfigMapper {

    @Select("SELECT count(*) FROM config")
    int count();

    @Select("SELECT count(*) FROM config where item_key = #{itemKey} and environment = #{environment}")
    int countByKeyAndEnvironment(@Param("itemKey") String itemKey, @Param("environment") String environment);

    @Select("<script>SELECT count(*) FROM config where 1 = 1"
            +"<if test='key != \"\"'> and item_key = #{key} </if>"
            +"<if test='value != \"\"'> and item_value LIKE CONCAT('%',#{value},'%') </if>"
            +"<if test='type != \"\"'> and type = #{type} </if>"
            +"<if test='env != \"\"'> and environment = #{env} </if>"
            +"<if test='status != \"\"'> and status = #{status} </if></script>")
    int countBySearch(@Param("key") String key, @Param("value") String value, @Param("type") String type, @Param("env") String env, @Param("status") String status);

    @Select("<script>SELECT * FROM config where 1 = 1"
            +"<if test='key != \"\"'> and item_key = #{key} </if>"
            +"<if test='value != \"\"'> and item_value LIKE CONCAT('%',#{value},'%') </if>"
            +"<if test='type != \"\"'> and type = #{type} </if>"
            +"<if test='env != \"\"'> and environment = #{env} </if>"
            +"<if test='status != \"\"'> and status = #{status} </if> LIMIT #{begin},#{end}</script>")
    List<Config> findBySearch(@Param("key") String key, @Param("value") String value, @Param("type") String type, @Param("env") String env, @Param("status") String status, @Param("begin") long begin, @Param("end") long end);

    @Select("<script>SELECT * FROM config where type = 'PUBLIC' and id not in ( select config_id from system_config where system_id = #{systemId})"
            +"<if test='key != \"\"'> and item_key = #{key} </if>"
            +"<if test='value != \"\"'> and item_value LIKE CONCAT('%',#{value},'%') </if>"
            +"<if test='env != \"\"'> and environment = #{env} </if>"
            +"<if test='status != \"\"'> and status = #{status} </if> LIMIT #{begin},#{end}</script>")
    List<Config> findBySearchWithPublic(@Param("systemId") long id,    @Param("key") String key, @Param("value") String value, @Param("env") String env, @Param("status") String status, @Param("begin") long begin, @Param("end") long end);

    @Select("SELECT id from config where type='PRIVATE' and item_key = #{key} and item_value =  #{value} and environment = #{env} and status = #{status} ORDER BY id DESC")
    long findWithPrivate(@Param("key") String key, @Param("value") String value, @Param("env") String env, @Param("status") String status);

    @Select("<script>SELECT count(*) FROM config where type = 'PUBLIC' and id not in ( select config_id from system_config where system_id = #{systemId})"
            +"<if test='key != \"\"'> and item_key = #{key} </if>"
            +"<if test='value != \"\"'> and item_value LIKE CONCAT('%',#{value},'%') </if>"
            +"<if test='env != \"\"'> and environment = #{env} </if>"
            +"<if test='status != \"\"'> and status = #{status} </if></script>")
    int countBySearchWithPublic(@Param("systemId") long id, @Param("key") String key, @Param("value") String value, @Param("env") String env, @Param("status") String status);

    @Select("SELECT * FROM config where id = #{id}")
    Config findById(@Param("id") long id);

    @Select("SELECT * FROM config LIMIT #{begin},#{end}")
    List<Config> findAll(@Param("begin") long begin, @Param("end") long end);

    @Select("SELECT * FROM config WHERE type = 'PUBLIC' and id NOT IN(select config_id from system_config where system_id = #{systemId}) LIMIT #{begin}, #{end}")
    List<Config> findPublicConfig(@Param("systemId") long systemId, @Param("begin") long begin, @Param("end") long end);

    @Select("SELECT count(*) FROM config WHERE type = 'PUBLIC' and id NOT IN(select config_id from system_config where system_id = #{systemId})")
    int countPublicConfig(@Param("systemId") long systemId);

    @Insert("INSERT INTO config(item_key, item_value, description, status, type, environment, create_time, update_time, creator, updater) "+
            "VALUES(#{itemKey}, #{itemValue}, #{description}, #{status}, #{type}, #{environment}, #{createTime}, #{updateTime}, #{creator}, #{updater})")
    int insert(Config configItem);

    @Update("UPDATE config SET item_key = #{itemKey}, item_value = #{itemValue}, description = #{description}, status = #{status}, type = #{type}, "+
            "environment = #{environment},  create_time = #{createTime}, update_time = #{updateTime}, creator = #{creator}, updater = #{updater} WHERE id = #{id}")
    int update(Config configItem);

    @Delete("DELETE FROM config WHERE id = #{id}")
    int deleteById(@Param("id") long id);

}
