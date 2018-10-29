package com.spring.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
public class Criteria {

    private int pageNum;
    private int amount;

    private String type;
    private String keyword;

    public Criteria(){
        this(1,10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    //검색조건(T,W,C)을 배열로 한번에 처리
    public String[] getTypeArr() {
        return type == null ? new String[]{} : type.split("");
    }

    //매번 파라미터를 유지하는 일이 번거롭다면 UriComponentsBuilder를 사용
    // 여러개의 파라미터들을 연결해서 URL의 형태로 만들어줌
    // ?pageNum=3&amount=10&type=T&keyword="제목'
    public String getListLink(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum",this.pageNum)
                .queryParam("amount", this.amount)
                .queryParam("type",this.type)
                .queryParam("keyword",this.keyword);
        return builder.toUriString();
    }
}
