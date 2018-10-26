package com.spring.excpetion;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

//ControllerAdvice는 AOP를 이용하는 방식
@ControllerAdvice //해당객체가 컨트롤러에서 발생하는 예외를 처리하는 존재임을 명시
@Log4j
public class CommonExceptionAdvice  {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException ex){
        log.error("404!!!");
        return "custom404";
    }

    @ExceptionHandler(Exception.class)  //예외처리 메서드 지정
    public String except(Exception ex, Model model){
        log.error("Exception........." + ex.getMessage());
        model.addAttribute("exception" + ex);
        log.error(model);

        return "error_page";
    }
}
