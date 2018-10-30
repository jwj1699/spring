package com.spring.pjt04;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyPageVO;
import com.spring.domain.ReplyVO;
import com.spring.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/replies")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {

    private ReplyService service;

    //댓글 달기 ({"bno":2359338, "reply":"Hello Reply","replyer":"user01"})
    @PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> create(@RequestBody ReplyVO vo){
        log.info("ReplyVO: " + vo);

        int insertCount = service.register(vo);

        return insertCount == 1
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //특정 게시물 댓글 목록 확인 (/replies/pages/2359287/1)
    @GetMapping(value = "/pages/{bno}/{page}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ReplyPageVO> getLIst(@PathVariable("page") int page,@PathVariable("bno") Long bno){
        log.info("getList......................");
        Criteria cri = new Criteria(page,10);

        return new ResponseEntity<>(service.getListPage(cri,bno),HttpStatus.OK);
    }

    @GetMapping(value = "/{rno}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
        log.info("get" + rno);

        return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
        log.info("remove" + rno);

        return service.remove(rno) == 1
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH},
            value = "/{rno}",
            consumes = "application/json",
            produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
        vo.setRno(rno);

        log.info("modify" + rno + ", " + vo);

        return service.modify(vo) == 1
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
