package com.boot.box;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value="classpath:my.properties",encoding="utf-8")
@Slf4j
public class BootBoxApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootBoxApplication.class, args);
		log.debug("log4j2 success ===== debug");
		log.warn("log4j2 success ===== warn");
		log.info("log4j2 success ===== info");
		log.error(System.getProperty("user.home") + " ===== error");
	}
}
