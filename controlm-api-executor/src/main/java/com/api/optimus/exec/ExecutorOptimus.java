package com.paypal.optimus.exec;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExecutorOptimus {

	private static final Logger logger = LoggerFactory.getLogger(ExecutorOptimus.class);

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-7"));
        logger.info("[EXECUTOR] Running in GMT-7 TimeZone:  " + new Date());
    }

	public static void main(String[] args) {
		SpringApplication.run(ExecutorOptimus.class, args);
	}
}
