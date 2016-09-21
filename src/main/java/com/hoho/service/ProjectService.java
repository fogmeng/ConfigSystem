package com.hoho.service;

import com.hoho.dto.Pager;
import com.hoho.entity.Project;
import com.hoho.entity.System;
import com.hoho.mapper.ProjectMapper;
import com.hoho.mapper.SystemMapper;
import com.hoho.util.StatusEnum;
import com.hoho.util.TypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Silence on 2016/9/19.
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public Pager<Project> getProject(int index){
        Pager<Project> pager = null;
        int allPage = projectMapper.count() / 10 + 1;
        if (index <= allPage){
            pager = new Pager<Project>(index,allPage);
            pager.setData(projectMapper.findAll(pager.getBegin(), pager.getEnd()));
        }else {
            pager = new Pager<Project>(1,allPage);
            pager.setData(projectMapper.findAll(pager.getBegin(), pager.getEnd()));
        }
        return  pager;
    }

    public Pager<Project> getProjectBySearch(int index, String nameEn, String name, String status){
        Pager<Project> pager = null;
        int allPage = projectMapper.countBySearch(nameEn, name,status) / 10 + 1;
        if (index <= allPage){
            pager = new Pager<Project>(index,allPage);
            pager.setData(projectMapper.findBySearch(nameEn, name, status, pager.getBegin(), pager.getEnd()));
        }else {
            pager = new Pager<Project>(1,allPage);
            pager.setData(projectMapper.findBySearch(nameEn, name, status, pager.getBegin(), pager.getEnd()));
        }
        return  pager;
    }

    public Project getProjectById(long id){
        Project project = projectMapper.findById(id);
        return project;
    }

    public int addProject(String nameEn, String name, String description, String status){
        int result = -1;
        if (projectMapper.countByNameEn(nameEn)>=1){
            return result;
        }else {
            result = projectMapper.insert(new Project(nameEn, name, description, status));
            return result;
        }
    }

    public int updateProject(long id, String nameEn, String name, String description, String status){
        int result = projectMapper.update(new Project(id, nameEn, name, description, status));
        return result;
    }

    public int deleteProject(long id){
        int result = projectMapper.deleteById(id);
        return result;
    }
}
