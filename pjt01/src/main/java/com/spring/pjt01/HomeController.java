package com.spring.pjt01;

import org.apache.ibatis.javassist.ClassPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(){
        return"index";}
}
