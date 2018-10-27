package com.spring.mapper;


import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;

import java.util.List;

public interface BoardMapper {

    public List<BoardVO> getList();

    public List<BoardVO> getListWithPaging(Criteria cri);

    //insert만 처리되고 생성된 PK값을 알 필요가 없는 경우
    public void insert(BoardVO board);

    //insert가 실행되고 생성된 PK 값을 알야야 하는 경우
    public void  insertSelectKey(BoardVO board);

    public BoardVO read(Long bno);

    public int delete(Long bno);

    public int update(BoardVO board);
}
