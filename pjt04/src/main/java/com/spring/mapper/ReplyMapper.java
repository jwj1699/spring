package com.spring.mapper;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {

    public int insert(ReplyVO vo);

    public ReplyVO read(Long bno);

    public int delete(Long bno);

    public int update(ReplyVO vo);

    //두개이상 파라미터를 받는 방법중 하나인 @Param (다른방법은 객체로 구성)
    public List<ReplyVO> getListWithPaging(@Param("cri")Criteria cri, @Param("bno") Long bno);

    public int getCountByBno(Long bno);
}
