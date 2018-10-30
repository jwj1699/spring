package com.spring.service;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyPageVO;
import com.spring.domain.ReplyVO;
import com.spring.mapper.ReplyMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class ReplyServiceimpl implements ReplyService {

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    @Override
    public int register(ReplyVO vo) {
        log.info("register " + vo);

        return mapper.insert(vo);
    }

    @Override
    public ReplyVO get(Long bno) {
        log.info("get " + bno);

        return mapper.read(bno);
    }

    @Override
    public int modify(ReplyVO vo) {
        log.info("modify " + vo);

        return mapper.update(vo);
    }

    @Override
    public int remove(Long bno) {
        log.info("remove " + bno);

        return mapper.delete(bno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno) {
        log.info("get Reply List of a Board " + bno);

        return mapper.getListWithPaging(cri, bno);
    }

    @Override
    public ReplyPageVO getListPage(Criteria cri, Long bno) {

        return new ReplyPageVO(
                mapper.getCountByBno(bno),
                mapper.getListWithPaging(cri,bno));
    }
}
