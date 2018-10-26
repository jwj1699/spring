package com.spring.service;

import com.spring.domain.BoardVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j
public class BoardServiceTests {

    @Setter(onMethod_ = {@Autowired})
    private BoardService service;

    public void testExist(){

        assertNotNull(service);
        log.info(service);
    }

    public void testRegister(){
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("newbie");

        service.register(board);

        log.info("생성된 게시물의 번호: " + board.getBno());
    }

    public void testGetList(){
        service.getList().forEach(board -> log.info(board));
    }

    public void testGet(){
        log.info(service.get(1L));
    }

    public void testRemove(){
        log.info("REMOVE RESULT: " + service.remove(1L));
    }

    @Test
    public void testModify(){

        BoardVO board = service.get(1L);

        if(board == null){
            return;
        }

        board.setTitle("제목 수정합니다.");
        log.info("MODIFY RESULT: " + service.modify(board));
    }
}
