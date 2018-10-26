package com.spring.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j
public class BoardMapperTests {

    @Setter(onMethod_ = {@Autowired})
    private BoardMapper mapper;

    @Test
    public void testGetList(){
        mapper.getList().forEach(board -> log.info(board));
    }
}
