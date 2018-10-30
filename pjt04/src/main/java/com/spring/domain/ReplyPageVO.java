package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
public class ReplyPageVO {

    private int replyCnt;
    private List<ReplyVO> list;
}
