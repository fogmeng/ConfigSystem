package com.hoho.service;

import com.hoho.dto.Pager;
import com.hoho.entity.Config;
import com.hoho.entity.System;
import com.hoho.entity.SystemAndConfig;
import com.hoho.mapper.ConfigMapper;
import com.hoho.mapper.SystemConfigMapper;
import com.hoho.mapper.SystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Silence on 2016/9/19.
 */
@Service
public class SystemService {

    @Autowired
    private SystemMapper systemMapper;

    @Autowired
    private ConfigMapper configMapper;

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    public Pager<System> getSystem(int index){
        Pager<System> pager = null;
        int allPage = systemMapper.count() / 10 + 1;
        if (index <= allPage){
            pager = new Pager<System>(index,allPage);
            pager.setData(systemMapper.findAll(pager.getBegin(), pager.getEnd()));
        }else {
            pager = new Pager<System>(1,allPage);
            pager.setData(systemMapper.findAll(pager.getBegin(), pager.getEnd()));
        }
        return  pager;
    }

    public Pager<System> getSystemBySearch(int index, String nameEn, String name){
        Pager<System> pager = null;
        int allPage = systemMapper.countBySearch(nameEn, name) / 10 + 1;
        if (index <= allPage){
            pager = new Pager<System>(index,allPage);
            pager.setData(systemMapper.findBySearch(nameEn, name, pager.getBegin(), pager.getEnd()));
        }else {
            pager = new Pager<System>(1,allPage);
            pager.setData(systemMapper.findBySearch(nameEn, name, pager.getBegin(), pager.getEnd()));
        }
        return  pager;
    }

    public int addSystem(String nameEn, String name, String description, long projectId){
        int result = -1;
        if (systemMapper.countByNameEn(nameEn)>=1){
            return result;
        }else {
            result = systemMapper.insert(new System(nameEn, name, description, projectId));
            return result;
        }
    }

    @Transactional
    public int addPrivateConfig(long id, String key, String value, String env, String status,String description) throws RuntimeException{
        int result = configMapper.insert(new Config(key,value,"PRIVATE",env,status,description));
        if(result == 1){
            long configId = configMapper.findWithPrivate(key, value, env, status);
            result = systemConfigMapper.addConfig(new SystemAndConfig(id, configId));
        }else {
            throw new RuntimeException("添加私有配置失败");
        }
        return result;
    }


    public int updateSystem(long id, String nameEn, String name, String description, long projectId){
        int result  = systemMapper.update(new System(id, nameEn, name, description, projectId));
        return result;
    }

    @Transactional
    public int deleteSystem(long id){
        int result = systemMapper.deleteById(id);
        //删除所有配置的关联
        systemConfigMapper.deleteConfigs(id);
        return result;
    }

    public System getSystemByNameEn(String nameEn){
        return systemMapper.findByNameEn(nameEn);
    }

}
