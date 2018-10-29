package com.spring.service;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    public int register(ReplyVO vo);

    public ReplyVO get(Long bno);

    public int modify(ReplyVO vo);

    public int remove(Long bno);

    public List<ReplyVO> getList(Criteria cri, Long bno);
}
