package com.paypal.Metric;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.mockito.internal.verification.Times;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.lang.Nullable;

import com.paypal.Metric.Repository.AlertRepository;

//import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;


@SpringBootApplication

public class MetricApplication implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(MetricApplication.class);
	@Autowired
	AlertRepository alertrepository;
	public static void main(String[] args) {
		SpringApplication.run(MetricApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("Application started.....");
//		LocalDate date = LocalDate.now().minusDays(6);
//		System.out.println(java.sql.Date.valueOf(date).getClass());
//		List<String> dates =  alertrepository.findDistinctDates(java.sql.Date.valueOf(date));
//		for(String dat:dates){
//			Date data = java.sql.Date.valueOf(LocalDate.parse(dat));
//			System.out.println(alertrepository.findByDate(data));
//			System.out.println(Long.toString(data.getTime()));
//		}
	}
}
