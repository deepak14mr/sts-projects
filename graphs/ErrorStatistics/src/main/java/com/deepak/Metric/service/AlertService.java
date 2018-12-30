package com.paypal.Metric.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.Metric.Repository.AlertRepository;


@Service
public class AlertService {

	private final static Logger logger = LoggerFactory.getLogger(AlertService.class);

	@Autowired
	private AlertRepository alertRepository;
	
	
	public Map<String, int[]> barChartService() throws ParseException{
		
	
		List<String> dates =  alertRepository.findDistinctDates();
		Map<String, int[]> metricMap = new LinkedHashMap<String, int[]>(); // order need to be preserved
		for(String dat:dates){
			Date data = java.sql.Date.valueOf(LocalDate.parse(dat));
			int ar[] = {alertRepository.findByDateAndStatus(data,0),alertRepository.findByDateAndStatus(data,1),alertRepository.findByDateAndStatus(data,2),alertRepository.findByDateAndStatus(data,3),alertRepository.findByDateAndStatus(data,4)};
			metricMap.put(Long.toString(data.getTime()+19800000),ar);
			
		}
		return metricMap;
	}
	public Map<String, int[]> areaChartService() throws ParseException{
		
		
		List<String> dates =  alertRepository.findDistinctDates();
		Map<String, int[]> metricMap = new LinkedHashMap<String, int[]>(); // order need to be preserved
		for(String dat:dates){
			Date data = java.sql.Date.valueOf(LocalDate.parse(dat));
			int ar[] = {alertRepository.findByDate(data),alertRepository.findByDateAndStatus(data,2),alertRepository.findByDateAndStatus(data,3)};
			metricMap.put(Long.toString(data.getTime()+19800000),ar);
			
		}
		return metricMap;
	}
	
}



