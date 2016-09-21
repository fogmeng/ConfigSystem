package com.hoho.service;

import com.hoho.dto.Pager;
import com.hoho.entity.Config;
import com.hoho.entity.System;
import com.hoho.mapper.ConfigMapper;
import com.hoho.mapper.SystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Silence on 2016/9/19.
 */
@Service
public class ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    public Pager<Config> getConfig(int index){
        Pager<Config> pager = null;
        int allPage = configMapper.count() / 10 + 1;
        if (index <= allPage){
            pager = new Pager<Config>(index,allPage);
            pager.setData(configMapper.findAll(pager.getBegin(), pager.getEnd()));
        }else {
            pager = new Pager<Config>(1,allPage);
            pager.setData(configMapper.findAll(pager.getBegin(), pager.getEnd()));
        }
        return  pager;
    }

    public Pager<Config> getConfigBySearch(int index, String key, String value, String type, String env, String status){
        Pager<Config> pager = null;
        int allPage = configMapper.countBySearch(key, value, type, env, status) / 10 + 1;
        if (index <= allPage){
            pager = new Pager<Config>(index,allPage);
            pager.setData(configMapper.findBySearch(key, value, type, env, status, pager.getBegin(), pager.getEnd()));
        }else {
            pager = new Pager<Config>(1,allPage);
            pager.setData(configMapper.findBySearch(key, value, type,  env, status, pager.getBegin(), pager.getEnd()));
        }
        return  pager;
    }

    public int addConfig(String itemKey, String itemValue, String type, String environment, String status, String description){
        int result = -1;
        if (configMapper.countByKeyAndEnvironment(itemKey, environment)>=1){
            return result;
        }else {
            result = configMapper.insert(new Config(itemKey, itemValue, type, environment, status, description));
            return result;
        }
    }

    public int updateConfig(long id, String itemKey, String itemValue, String type, String environment, String status, String description){
        int result = configMapper.update(new Config(id, itemKey, itemValue, type, environment, status, description));
        return result;

    }

    public int deleteConfig(long id){
        int result = configMapper.deleteById(id);
        return result;
    }

}
