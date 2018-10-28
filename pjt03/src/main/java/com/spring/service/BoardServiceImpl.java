package com.spring.service;

import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    private BoardMapper mapper;

    @Override
    public void register(BoardVO board) {
        log.info("register()" + board);

        mapper.insertSelectKey(board);
    }

    @Override
    public BoardVO get(Long bno) {
        log.info("get()");

        return mapper.read(bno);
    }

    @Override
    public boolean modify(BoardVO board) {
        log.info("modify()" + board);

        return mapper.update(board) == 1;
    }

    @Override
    public boolean remove(Long bno) {
        log.info("remove()");

        return mapper.delete(bno) == 1;
    }

    @Override
    public int getTotal(Criteria cri) {
        log.info("getTotal()");
        return mapper.getTotalCount(cri);
    }

    @Override
    public List<BoardVO> getList(Criteria cri) {
        log.info("getList() "+cri);

        return mapper.getListWithPaging(cri);
    }
}
