package com.hoho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Silence on 2016/9/12.
 */
@Controller
public class IndexController {

    @RequestMapping({"/", "/index"})
    public String index(){
        return "index";
    }

}
