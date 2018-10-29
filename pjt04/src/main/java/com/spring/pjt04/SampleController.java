package com.spring.pjt04;

import com.spring.domain.SampleVO;
import com.spring.domain.Ticket;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {

    //단순 문자열 반환
    @GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
    public String getText(){
        log.info("MIME TYPE = " + MediaType.TEXT_PLAIN_VALUE);

        return "안녕하세요";
    }

    //객체 반환 (/sample/getSample => xml 타입반환, /sample/getSample.json => json타입 반환) (produces 생략가능)
    @GetMapping(value = "/getSample", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SampleVO getSample(){

        return new SampleVO(112,"스타","로드");
    }

    //컬렉션 타입 반환
    @GetMapping("/getList")
    public List<SampleVO> getList(){

        return IntStream.range(1,10).mapToObj(i -> new SampleVO(i,i+"First", i+"Lsat")).collect(Collectors.toList());
    }

    //ResponseEntity를 사용해 데이터와 HTTP헤더의 상태 메세지 같이 전달
    @GetMapping(value = "/check",params = {"heiget","weight"})
    public ResponseEntity<SampleVO> check(Double heiget, Double weight){
        SampleVO vo = new SampleVO(0, ""+heiget, ""+weight);

        ResponseEntity<SampleVO> result = null;

        if(heiget < 150){
            result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
        }else {
            result = ResponseEntity.status(HttpStatus.OK).body(vo);
        }
        return result;
    }

    //@PathVariable (/product/bags/1234)
    @GetMapping("/product/{cat}/{pid}")
    public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") String pid){
        return new String[] {"category: " + cat, "productid: " + pid};
    }

    //@RequestBody 리퀘스트의 바디를 이용해 파라미터 타입으로 변환 (POST)
    @PostMapping("/ticket")
    public Ticket convert(@RequestBody Ticket ticket){
        log.info("convert.................ticket " + ticket);

        return ticket;
    }

}
