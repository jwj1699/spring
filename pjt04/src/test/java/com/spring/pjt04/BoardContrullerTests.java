package com.spring.pjt04;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration    //Test for Controller
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/root-context.xml",
        "file:src/main/webapp/WEB-INF/servlet-context.xml"})
@Log4j
public class BoardContrullerTests {

    @Setter(onMethod_ = {@Autowired})
    private WebApplicationContext ctx;
    private MockMvc mockMvc;    //가짜MVC

    @Before //테스트 전에 실행
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
                .param("PageNum","2")
                .param("amount","20"))
                .andReturn()
                .getModelAndView()
                .getModelMap();
    }

    public void testRegister() throws Exception{
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("title", "테스트 새글 제목")
                .param("content", "테스트 새글 내용")
                .param("writer", "user00")
        ).andReturn().getModelAndView().getViewName();
        log.info(resultPage);
    }

    public void testGet() throws Exception{
        log.info(mockMvc.perform(MockMvcRequestBuilders
                .get("/board/get")
                .param("bno","2"))
                .andReturn()
                .getModelAndView()
                .getModelMap());
    }

    public void testModify() throws Exception{
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
            .param("bno","1")
            .param("title", "수정된 테스트 새글 제목")
            .param("content", "수정된 테스트 새글 내용")
            .param("writer", "user00"))
            .andReturn().getModelAndView().getViewName();
        log.info(resultPage);
    }

    public void testRemove() throws Exception{
        //삭제전 디비에서 게시물 확인
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
            .param("bno","25"))
            .andReturn()
            .getModelAndView()
            .getViewName();
        log.info(resultPage);
    }

}
