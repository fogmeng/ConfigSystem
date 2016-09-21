package com.hoho.controller;

import com.hoho.dto.Pager;
import com.hoho.entity.Config;
import com.hoho.entity.System;
import com.hoho.service.ConfigService;
import com.hoho.service.SystemService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Silence on 2016/9/19.
 */
@Controller()
@RequestMapping("/manage")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @RequestMapping("/config")
    public String getConfigList(HttpServletRequest request, Model model){
        Pager<Config> pager = null;
        if(request.getParameter("p")==null){
            pager = configService.getConfig(1);
        }else {
            int p = Integer.valueOf(request.getParameter("p"));
            if (request.getParameter("key") != "" || request.getParameter("value") != "" || request.getParameter("status") != "" || request.getParameter("type") != "" || request.getParameter("env") != ""){
                pager = configService.getConfigBySearch(p, request.getParameter("key").trim(), request.getParameter("value").trim(), request.getParameter("type").trim(), request.getParameter("env").trim(), request.getParameter("status").trim());
                model.addAttribute("key",request.getParameter("key"));
                model.addAttribute("value",request.getParameter("value"));
                model.addAttribute("type",request.getParameter("type"));
                model.addAttribute("status",request.getParameter("status"));
                model.addAttribute("env",request.getParameter("env"));

            }else {
                pager = configService.getConfig(p);
            }
        }
        model.addAttribute("pager",pager);
        return "manage/config";
    }

    @RequestMapping(value = "/api/config/add",method = RequestMethod.POST)
    @ResponseBody
    public String addConfig(HttpServletRequest request){
        int result = configService.addConfig(request.getParameter("key"), request.getParameter("value"), request.getParameter("type"),
                            request.getParameter("environment"), request.getParameter("status"), request.getParameter("description"));
        if(result == 1){
            return "添加成功";
        }else if(result == -1){
            return "添加失败，此配置已存在";
        }else {
            return "添加失败，未知错误";
        }
    }

    @RequestMapping(value = "/api/config/{id}/update",method = RequestMethod.POST)
    @ResponseBody
    public String updateConfig(HttpServletRequest request){
        int result = configService.updateConfig(Long.valueOf(request.getParameter("id")),request.getParameter("key"), request.getParameter("value"), request.getParameter("type"),
                request.getParameter("environment"), request.getParameter("status"), request.getParameter("description"));
        if(result == 1){
            return "修改成功";
        }else {
            return "修改失败，未知错误";
        }
    }

    @RequestMapping(value = "/api/config/{id}/delete")
    @ResponseBody
    public String deleteConfig(@PathVariable long id){
        int result = configService.deleteConfig(id);
        if(result == 1){
            return "删除成功";
        }else {
            return "删除失败，配置不存在";
        }
    }

}
