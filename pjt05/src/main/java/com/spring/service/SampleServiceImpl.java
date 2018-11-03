package com.spring.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

    @Override
    public Integer doAdd(String str1, String str2) throws Exception {

        //반복적이면서 핵심로직은 아니지만 필요하기는 한 기능인 log.info()를 뺌
        return Integer.parseInt(str1) + Integer.parseInt(str2);

    }
}
