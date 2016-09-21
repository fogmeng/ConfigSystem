package com.hoho.mapper;

import com.hoho.entity.Config;
import com.hoho.entity.System;
import com.hoho.entity.SystemAndConfig;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Silence on 2016/9/12.
 */
@Mapper
public interface SystemConfigMapper {

    @Select("SELECT * FROM system_config_item where id = #{id}")
    SystemAndConfig findById(@Param("id") long id);

    @Select("SELECT * FROM system_config_item LIMIT #{begin},#{end}")
    List<SystemAndConfig> findAll(@Param("begin") long begin, @Param("end") long end);

    @Select("select * from system where id in (select system_id from system_config_item where config_item_id = 18)")
    List<System> findSystemByConfigItemId(@Param("id") long id);

    @Select("SELECT * FROM config WHERE id IN ( SELECT config_id FROM system_config WHERE system_id = #{id})")
    List<Config> findConfigItemBySystemId(@Param("id") long id);

    @Insert("INSERT INTO system_config(system_id, config_id) "+
            "VALUES(#{systemId}, #{configId})")
    int addConfig(SystemAndConfig systemConfigItem);

    @Update("UPDATE system_config_item SET system_id = #{systemId}, config_item_id = #{configItemId} WHERE id = #{id}")
    int update(SystemAndConfig systemConfigItem);

    @Delete("DELETE FROM system_config_item WHERE id = #{id}")
    int deleteById(@Param("id") long id);

    @Delete("DELETE FROM system_config WHERE system_id = #{systemId} and config_id = #{configId}")
    int removeConfig(@Param("systemId") long id, @Param("configId") long cid);

    @Delete("DELETE FROM system_config WHERE system_id = #{id}")
    int deleteConfigs(@Param("id") long id);

}
