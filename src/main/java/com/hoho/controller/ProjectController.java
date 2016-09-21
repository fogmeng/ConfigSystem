package com.hoho.controller;

import com.hoho.dto.Pager;
import com.hoho.entity.Project;
import com.hoho.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Silence on 2016/9/19.
 */
@Controller
@RequestMapping("/manage")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/project")
    public String getSystemList(HttpServletRequest request, Model model){
        Pager<Project> pager = null;
        if(request.getParameter("p")==null){
            pager = projectService.getProject(1);
        }else {
            int p = Integer.valueOf(request.getParameter("p"));
            if (request.getParameter("nameEn") != "" || request.getParameter("name") != "" || request.getParameter("status") != ""){
                pager = projectService.getProjectBySearch(p, request.getParameter("nameEn").trim(), request.getParameter("name").trim(), request.getParameter("status").trim());
                model.addAttribute("nameEn",request.getParameter("nameEn"));
                model.addAttribute("name",request.getParameter("name"));
                model.addAttribute("status",request.getParameter("status"));
            }else {
                pager = projectService.getProject(p);
            }
        }
        model.addAttribute("pager",pager);
        return "manage/project";
    }

    @RequestMapping(value = "/api/project/add",method = RequestMethod.POST)
    @ResponseBody
    public String addSystem(HttpServletRequest request){
        int result = projectService.addProject(request.getParameter("nameEn"), request.getParameter("name"), request.getParameter("description"), request.getParameter("status"));
        if(result == 1){
            return "添加成功";
        }else if(result == -1){
            return "添加失败，此项目已存在";
        }else {
            return "添加失败，未知错误";
        }
    }

    @RequestMapping(value = "/api/project/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Project getProjectById(@PathVariable long id){
        Project project = projectService.getProjectById(id);
        return project;
    }

    @RequestMapping(value = "/api/project/{id}/update",method = RequestMethod.POST)
    @ResponseBody
    public String updateProject(HttpServletRequest request){
        int result = projectService.updateProject(Long.valueOf(request.getParameter("id")),request.getParameter("nameEn"), request.getParameter("name"), request.getParameter("description"), request.getParameter("status"));
        if(result == 1){
            return "修改成功";
        }else {
            return "修改失败，未知错误";
        }
    }

    @RequestMapping(value = "/api/project/{id}/delete")
    @ResponseBody
    public String deleteProject(@PathVariable long id){
        int result = projectService.deleteProject(id);
        if(result == 1){
            return "删除成功";
        }else {
            return "删除失败，项目不存在";
        }
    }

}
