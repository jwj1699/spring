package com.spring.mapper;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j
public class ReplyMapperTests {

    private Long[] bnoArr = {2359301L, 2359288L, 2359287L, 2359286L, 2359285L};

    @Setter(onMethod_ = {@Autowired})
    private ReplyMapper mapper;

    public void testMapper(){
        log.info(mapper);
    }

    public void testCreate(){
        IntStream.range(1,10).forEach(i->{
            ReplyVO vo = new ReplyVO();

            vo.setBno(bnoArr[i%5]);
            vo.setReply("댓글테스트" + i);
            vo.setReplyer("replyer" + i);

            mapper.insert(vo);
        });
    }

    public void testRead(){
        Long targetRno = 5L;

        ReplyVO vo = mapper.read(targetRno);

        log.info(vo);
    }

    public void testDelete(){
        Long targetRno = 1L;

        log.info(mapper.delete(targetRno));
    }

    public void testUpdate(){
        Long targetRno = 10L;

        ReplyVO vo = mapper.read(targetRno);
        vo.setReply("Update Reply");

        log.info(mapper.update(vo));
    }

    public void testList(){
        Criteria cri = new Criteria();

        log.info(bnoArr[3]);
        List<ReplyVO> replies = mapper.getListWithPaging(cri,bnoArr[3]);
        replies.forEach(reply -> log.info(reply));
    }

    @Test
    public void testList2(){
        Criteria cri = new Criteria(1, 10);

        List<ReplyVO> replies = mapper.getListWithPaging(cri,2359288L);
        replies.forEach(reply -> log.info(reply));
    }
}
