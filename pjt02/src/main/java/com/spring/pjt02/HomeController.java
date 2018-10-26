package com.spring.pjt02;

import com.spring.domain.SampleDTO;
import com.spring.domain.SampleDTOList;
import com.spring.domain.TodoDTO;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    //Java Beans 규칙에 맞는 객체는 다시 화면으로 전달된다, 기본자료형은 전달이 안됨
    // (/sample/test07?name=aaa&age=11&page=9)
    @RequestMapping("/test07")
    public String test07(SampleDTO dto, int page){

        log.info("dto=" + dto);
        log.info("page=" + page);

        return "test07";
    }

    //기본자료형을 파라미터로 뷰에 보내는 방법
    // (/sample/test08?name=aaa&age=11&page=9)
    @RequestMapping("/test08")
    public String test08(SampleDTO dto,@ModelAttribute("page") int page){

        log.info("dto=" + dto);
        log.info("page=" + page);

        return "test07";
    }

    //(void 리턴타입) 일경우 url의 경로로 jsp파일을 사용
    // (/sample/test09)
    @RequestMapping("/test09")
    public void test09(){

        log.info("test09................");
    }

    //(객체 리턴타입) jackson-databind 라이브러리사용시 json데이터로 리턴
    // (/sample/test10)
    @RequestMapping("/test10")
    public @ResponseBody SampleDTO test10(){

        SampleDTO dto = new SampleDTO();
        dto.setAge(11);
        dto.setName("홍길동");

        return dto;
    }

    //ResponseEntity를 사용하여 헤더정보나 데이터 전달 가능
    // (/sample/test11)
    @RequestMapping("/test11")
    public ResponseEntity<String> test11(){

        String msg = "{\"name\": \"홍길동\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(msg, headers, HttpStatus.OK);
    }
}