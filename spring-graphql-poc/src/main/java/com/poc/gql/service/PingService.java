package com.poc.gql.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PingService {

    public void pingMessage(String messageValue){
        log.info("Message Value: {}", messageValue);
    }
}
