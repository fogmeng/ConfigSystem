package com.hoho.service;

import com.hoho.dto.Pager;
import com.hoho.entity.Config;
import com.hoho.entity.SystemAndConfig;
import com.hoho.mapper.ConfigMapper;
import com.hoho.mapper.SystemConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Silence on 2016/9/20.
 */
@Service
public class SystemConfigService {

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Autowired
    private ConfigMapper configMapper;

    public List<Config> getConfigs(long id){
        List<Config> configs = systemConfigMapper.findConfigItemBySystemId(id);
        return configs;
    }


    @Transactional
    public int removeConfig(long id, long cid){
        int result = systemConfigMapper.removeConfig(id, cid);
        //如果此配置是私有配置，则在配置表中删除此配置
        if(configMapper.findById(cid).getType().getNameEn().equals("PRIVATE")) {
            configMapper.deleteById(cid);
        }
        return result;
    }

    public int addConfig(long id, long cid){
        int result = systemConfigMapper.addConfig(new SystemAndConfig(id,cid));
        return result;
    }


    public Pager<Config> findBySearchWithPublic(int index, long id, String key, String value, String env, String status){
        Pager<Config> pager = null;
        int allPage = configMapper.countBySearchWithPublic(id, key, value, env, status) / 10 + 1;
        if (index <= allPage){
            pager = new Pager<Config>(index,allPage);
            pager.setData(configMapper.findBySearchWithPublic(id, key, value, env, status, pager.getBegin(), pager.getEnd()));
        }else {
            pager = new Pager<Config>(1,allPage);
            pager.setData(configMapper.findBySearchWithPublic(id, key, value, env, status, pager.getBegin(), pager.getEnd()));
        }
        return  pager;
    }

    public Pager<Config> getConfigWithPublic(int index, long id){
        Pager<Config> pager = null;
        int allPage = configMapper.countPublicConfig(id) / 10 + 1;
        if (index <= allPage){
            pager = new Pager<Config>(index,allPage);
            pager.setData(configMapper.findPublicConfig(id, pager.getBegin(), pager.getEnd()));
        }else {
            pager = new Pager<Config>(1,allPage);
            pager.setData(configMapper.findPublicConfig(id, pager.getBegin(), pager.getEnd()));
        }
        return  pager;
    }

}
