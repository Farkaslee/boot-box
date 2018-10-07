package com.boot.box.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

/**
 * Created by Farkas on 2018/10/7.
 */
@Profile("dev")//支持数组:@Profile({"dev","test"})
@Configuration
@Slf4j
public class DevBean {
    @PostConstruct
    public void init() {
        log.info("dev环境下激活");
    }
}
