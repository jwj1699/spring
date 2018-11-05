package com.spring.service;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyPageVO;
import com.spring.domain.ReplyVO;
import com.spring.mapper.BoardMapper;
import com.spring.mapper.ReplyMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j
public class ReplyServiceimpl implements ReplyService {

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    @Setter(onMethod_ = @Autowired)
    private BoardMapper boardMapper;

    @Transactional
    @Override
    public int register(ReplyVO vo) {
        log.info("register " + vo);

        boardMapper.updateReplyCnt(vo.getBno(),1);

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

    @Transactional
    @Override
    public int remove(Long rno) {
        log.info("remove " + rno);

        ReplyVO vo = mapper.read(rno);
        boardMapper.updateReplyCnt(vo.getBno(),1);

        return mapper.delete(rno);
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
