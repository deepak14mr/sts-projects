package com.paypal.optimus.exec.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.paypal.optimus.exec.service.CtrlMService;

@RestController
@RequestMapping("/api/ctrlm/")
public class CtrlMController {

	private final static Logger logger = LoggerFactory.getLogger(CtrlMController.class);

	@Autowired
	private CtrlMService ctrlMService;

	@RequestMapping(value="isalive", method=RequestMethod.GET)
	public ResponseEntity<String> isAlive() {
		logger.info("[EXECUTOR-CTRLM] Acknowledge request for health check.");
		return new ResponseEntity<String>("YES", HttpStatus.OK);
	}

	@RequestMapping(value="restart", method=RequestMethod.POST)
	public ResponseEntity<String> restartJob(@RequestBody Map<String, String> jobData) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		logger.info("[EXECUTOR-CTRLM] Run CTRLM task for data: " + jobData);
		if (null != jobData && !jobData.isEmpty()
				&& null != jobData.get("technology") && !jobData.get("technology").isEmpty()
				&& null != jobData.get("action") && !jobData.get("action").isEmpty()
				&& null != jobData.get("script") && !jobData.get("script").isEmpty()
				&& null != jobData.get("args") && !jobData.get("args").isEmpty()) {
			return new ResponseEntity<String>(ctrlMService.runTask(jobData.get("technology"), 
					jobData.get("action"), jobData.get("script"), jobData.get("args")), HttpStatus.OK);
		} else {
			logger.error("[EXECUTOR-CTRLM] Bad request to rerun CTRLM task(runTask) for data: " + jobData);
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="status", method=RequestMethod.POST)
	public ResponseEntity<String> getStatusJob(@RequestBody Map<String, String> jobData) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		logger.info("[EXECUTOR-CTRLM] Run CTRLM task for data: " + jobData);
		if (null != jobData && !jobData.isEmpty()
				&& null != jobData.get("technology") && !jobData.get("technology").isEmpty()
				&& null != jobData.get("action") && !jobData.get("action").isEmpty()
				&& null != jobData.get("script") && !jobData.get("script").isEmpty()
				&& null != jobData.get("args") && !jobData.get("args").isEmpty()) {
			return new ResponseEntity<String>(ctrlMService.runGetStatus(jobData.get("technology"), 
					jobData.get("action"), jobData.get("script"), jobData.get("args")), HttpStatus.OK);
		} else {
			logger.error("[EXECUTOR-CTRLM] Bad request to run CTRLM task(runGetStatus) for getting data: " + jobData);
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="info", method=RequestMethod.POST)
	public ResponseEntity<String> getInfo(@RequestBody Map<String, String> jobData) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		logger.info("[EXECUTOR-CTRLM] Run CTRLM task for data: " + jobData);
		if (null != jobData && !jobData.isEmpty()
				&& null != jobData.get("technology") && !jobData.get("technology").isEmpty()
				&& null != jobData.get("action") && !jobData.get("action").isEmpty()
				&& null != jobData.get("script") && !jobData.get("script").isEmpty()
				&& null != jobData.get("args") && !jobData.get("args").isEmpty()) {
			return new ResponseEntity<String>(ctrlMService.getRunAs(jobData.get("technology"), 
					jobData.get("action"), jobData.get("script"), jobData.get("args")), HttpStatus.OK);
		} else {
			logger.error("[EXECUTOR-CTRLM] Bad request to run CTRLM task(getInfo) for data: " + jobData);
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(value="unblock", method=RequestMethod.POST)
	public ResponseEntity<String> freeJob(@RequestBody Map<String, String> jobData) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		logger.info("[EXECUTOR-CTRLM] Run CTRLM task for data: " + jobData);
		if (null != jobData && !jobData.isEmpty()
				&& null != jobData.get("technology") && !jobData.get("technology").isEmpty()
				&& null != jobData.get("action") && !jobData.get("action").isEmpty()
				&& null != jobData.get("script") && !jobData.get("script").isEmpty()
				&& null != jobData.get("args") && !jobData.get("args").isEmpty()) {
			return new ResponseEntity<String>(ctrlMService.unblockTask(jobData.get("technology"), 
					jobData.get("action"), jobData.get("script"), jobData.get("args")), HttpStatus.OK);
		} else {
			logger.error("[EXECUTOR-CTRLM] Bad request to run CTRLM task(unblockTask) for data: " + jobData);
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(value="kill", method=RequestMethod.POST)
	public ResponseEntity<String> killJob(@RequestBody Map<String, String> jobData) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		logger.info("[EXECUTOR-CTRLM] Run CTRLM task for data: " + jobData);
		if (null != jobData && !jobData.isEmpty()
				&& null != jobData.get("technology") && !jobData.get("technology").isEmpty()
				&& null != jobData.get("action") && !jobData.get("action").isEmpty()
				&& null != jobData.get("script") && !jobData.get("script").isEmpty()
				&& null != jobData.get("args") && !jobData.get("args").isEmpty()) {
			return new ResponseEntity<String>(ctrlMService.killTask(jobData.get("technology"), 
					jobData.get("action"), jobData.get("script"), jobData.get("args")), HttpStatus.OK);
		} else {
			logger.error("[EXECUTOR-CTRLM] Bad request to run CTRLM task(killTask) for data: " + jobData);
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(value="block", method=RequestMethod.POST)
	public ResponseEntity<String> holdJob(@RequestBody Map<String, String> jobData) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		logger.info("[EXECUTOR-CTRLM] Run CTRLM task for data: " + jobData);
		if (null != jobData && !jobData.isEmpty()
				&& null != jobData.get("technology") && !jobData.get("technology").isEmpty()
				&& null != jobData.get("action") && !jobData.get("action").isEmpty()
				&& null != jobData.get("script") && !jobData.get("script").isEmpty()
				&& null != jobData.get("args") && !jobData.get("args").isEmpty()) {
			return new ResponseEntity<String>(ctrlMService.blockTask(jobData.get("technology"), 
					jobData.get("action"), jobData.get("script"), jobData.get("args")), HttpStatus.OK);
		} else {
			logger.error("[EXECUTOR-CTRLM] Bad request to run CTRLM task(blockTask) for data: " + jobData);
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
}