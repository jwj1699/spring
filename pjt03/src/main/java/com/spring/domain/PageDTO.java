package com.spring.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

    private int startPage;
    private int endPate;
    private boolean prev, next;

    private int total;
    private Criteria cri;

    public PageDTO(int total, Criteria cri) {
        this.total = total;
        this.cri = cri;

        this.endPate = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
        this.startPage = this.endPate - 9;

        int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

        //끝번호가 전체 끝번호를 초과할경우
        if(realEnd < this.endPate){
            this.endPate = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPate < realEnd;
    }
}
