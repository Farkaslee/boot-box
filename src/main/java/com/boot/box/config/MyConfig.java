package com.boot.box.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Farkas on 2018/10/7.
 */
@Component
@ConfigurationProperties
@Data
public class MyConfig {
    private String name;
}
