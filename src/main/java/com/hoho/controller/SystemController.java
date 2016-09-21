package com.hoho.controller;

import com.hoho.dto.Pager;
import com.hoho.entity.Config;
import com.hoho.entity.System;
import com.hoho.service.ConfigService;
import com.hoho.service.SystemConfigService;
import com.hoho.service.SystemService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Silence on 2016/9/19.
 */
@Controller()
@RequestMapping("/manage")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private ConfigService configService;

    @RequestMapping("/system")
    public String getSystemList(HttpServletRequest request, Model model){
        Pager<System> pager = null;
        if(request.getParameter("p")==null){
            pager = systemService.getSystem(1);
        }else {
            int p = Integer.valueOf(request.getParameter("p"));
            if (request.getParameter("nameEn") != "" || request.getParameter("name") != ""){
                pager = systemService.getSystemBySearch(p, request.getParameter("nameEn").trim(), request.getParameter("name").trim());
                model.addAttribute("nameEn",request.getParameter("nameEn"));
                model.addAttribute("name",request.getParameter("name"));
            }else {
                pager = systemService.getSystem(p);
            }
        }
        model.addAttribute("pager",pager);
        return "manage/system";
    }

    @RequestMapping("/system/{id}/config")
    public String getSystemConfigList(@PathVariable long id, Model model){
        List<Config> configs = systemConfigService.getConfigs(id);
        model.addAttribute("sc",configs);
        model.addAttribute("sysId",id);
        return "manage/system-config";
    }

    @RequestMapping(value = "/api/system/add",method = RequestMethod.POST)
    @ResponseBody
    public String addSystem(HttpServletRequest request){
        int result = systemService.addSystem(request.getParameter("nameEn"), request.getParameter("name"), request.getParameter("description"), Long.valueOf(request.getParameter("projectId")));
        if(result == 1){
            return "添加成功";
        }else if(result == -1){
            return "添加失败，此系统已存在";
        }else {
            return "添加失败，未知错误";
        }
    }

    @RequestMapping(value = "/api/system/{id}/private",method = RequestMethod.POST)
    @ResponseBody
    public String addPrivateSystem(@PathVariable long id, HttpServletRequest request){
        int result = systemService.addPrivateConfig(id, request.getParameter("key"), request.getParameter("value"), request.getParameter("environment"), request.getParameter("status"), request.getParameter("description"));
        if(result == 1){
            return "添加成功";
        }else if(result == -1){
            return "添加失败，此私有配置已存在";
        }else {
            return "添加失败，未知错误";
        }
    }

    @RequestMapping(value = "/api/system/{id}/update",method = RequestMethod.POST)
    @ResponseBody
    public String updateSystem(HttpServletRequest request){
        int result = systemService.updateSystem(Long.valueOf(request.getParameter("id")),request.getParameter("nameEn"), request.getParameter("name"), request.getParameter("description"), Long.valueOf(request.getParameter("projectId")));
        if(result == 1){
            return "修改成功";
        }else {
            return "修改失败，未知错误";
        }
    }

    @RequestMapping(value = "/api/system/{id}/delete")
    @ResponseBody
    public String deleteSystem(@PathVariable long id){
        int result = systemService.deleteSystem(id);
        if(result == 1){
            return "删除成功";
        }else {
            return "删除失败，系统不存在";
        }
    }

    @RequestMapping(value = "/api/system/{id}/config/{cid}/remove")
    @ResponseBody
    public String removeConfig(@PathVariable long id, @PathVariable long cid){
        int result = systemConfigService.removeConfig(id, cid);
        if(result == 1){
            return "移除成功";
        }else {
            return "移除失败，配置不存在于此系统中";
        }
    }

    @RequestMapping(value = "/api/system/{id}/config/{cid}/add")
    @ResponseBody
    public String addConfig(@PathVariable long id, @PathVariable long cid){
        int result = systemConfigService.addConfig(id, cid);
        if(result == 1){
            return "添加成功";
        }else {
            return "添加失败，配置已存在于此系统中";
        }
    }

    @RequestMapping("/system/{id}/public")
    public String addPublicConfig(@PathVariable long id, HttpServletRequest request, Model model){
        model.addAttribute("sysId",id);
        Pager<Config> pager = null;
        if(request.getParameter("p")==null){
            pager = systemConfigService.getConfigWithPublic(1, id);
        }else {
            int p = Integer.valueOf(request.getParameter("p"));
            if (request.getParameter("key") != "" || request.getParameter("value") != "" || request.getParameter("status") != "" || request.getParameter("type") != "" || request.getParameter("env") != ""){
                pager = systemConfigService.findBySearchWithPublic(p, id, request.getParameter("key").trim(), request.getParameter("value").trim(), request.getParameter("env").trim(), request.getParameter("status").trim());
                model.addAttribute("key",request.getParameter("key"));
                model.addAttribute("value",request.getParameter("value"));
                model.addAttribute("type",request.getParameter("type"));
                model.addAttribute("status",request.getParameter("status"));
                model.addAttribute("env",request.getParameter("env"));

            }else {
                pager = systemConfigService.getConfigWithPublic(p, id);
            }
        }
        model.addAttribute("pager",pager);
        return "manage/addconfig";
    }

    @RequestMapping(value = "/properties/{system}/{env}",produces = "application/json")
    @ResponseBody
    public String systemApi(@PathVariable String system, @PathVariable String env){
        System sys = systemService.getSystemByNameEn(system);
        List<Config> configs = systemConfigService.getConfigs(sys.getId());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < configs.size(); i++) {
            if (configs.get(i).getEnvironment().getNameEn().equals(env)){
                sb.append(configs.get(i).getItemKey());
                sb.append("=");
                sb.append(configs.get(i).getItemValue());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
