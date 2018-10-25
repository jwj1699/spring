package com.spring.pjt02;

import com.spring.domain.SampleDTO;
import com.spring.domain.SampleDTOList;
import com.spring.domain.TodoDTO;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class HomeController {

    @RequestMapping("")
    public String basic(){
        log.info("basic................");
        return "index";
    }

    //파라미터 자동수집(+타입변환까지)
    // (/sample/test03?name=AAA&age=10)
    @RequestMapping("/test02")
    public String test02(SampleDTO dto){
        log.info("" + dto);

        return "dto";
    }

    //파라미터 수집 (변수명과 파라미터명이 다를 경우 사용하기 좋다)
    // (/sample/test03?name=AAA&age=10)
    @RequestMapping("/test03")
    public String test03(@RequestParam("name") String name, @RequestParam("age") int age){
        log.info("name=" + name);
        log.info("age=" + age);

        return "dto";
    }

    //리스트나 배열도 가능!
    // (/sample/test04?ids=111&ids=222&ids=333)
    @RequestMapping("/test04")
    public String test04(@RequestParam("ids") ArrayList<String> ids){

        log.info("ids=" + ids);

        return "dto";
    }

    //객체리스트도 가능
    // (/sample/test05?list[0].name=aaa&list[0].age=10&list[1].name=bbb)
    @RequestMapping("/test05")
    public String test05(SampleDTOList list){

        log.info("list dtos=" + list);

        return "dto";
    }

    //객체리스트도 가능
    // (/sample/test06?title=test&dueDate=2018/01/01)
    @RequestMapping("/test06")
    public String test06(TodoDTO dto){

        log.info("todo=" + dto);

        return "dto";
    }
}