package com.paypal.optimus.exec.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class CtrlMService {
	@Autowired
	private RestTemplate restTemplate;
	@Value("${controlm.endPoint}")
	private String endPoint;
	@Value("${controlm.username}")
	private String username;
	@Value("${controlm.password}")
	private String password;

	private final static Logger logger = LoggerFactory.getLogger(CtrlMService.class);
	/**
	 * 
	 * Calls reRun(String jobId) method 
	 * verifies SOP
	 * @param technology
	 * @param action
	 * @param script
	 * @param jobId
	 * @return  Parameters of the job(json)
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String runTask(String technology, String action, String script, String jobId) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		logger.info("[EXECUTOR-CTRLM] Started CTRLM runTask service.");
		String token = loginGetToken();		
        if(technology=="CONTROL-M"){
        	String valueOfStatus = getOnlyField(token,jobId,"status");
        	logout(token);
            if(valueOfStatus!=null && action=="RESTART" && valueOfStatus.equalsIgnoreCase("Ended Not OK")){
            	return reRun(token,jobId);  	 
            }
        }
        return "Invalid Request";
	}
	/**
	 * Calls getJobStatus(String jobID)
	 * verifies SOP
	 * @param technology
	 * @param action
	 * @param script
	 * @param jobId
	 * @return parameters of the job (json)
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String runGetStatus(String technology, String action, String script, String jobId) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		logger.info("[EXECUTOR-CTRLM] Started CTRLM runGetStatus service.");
				
        if(technology=="CONTROL-M"){
            if(action=="STATUS"){
            	return trimJson(getJobStatus(jobId));  	 
            }
        }
        return "Invalid Request";
	}
	/**
	 * Logs in
	 * Verifies SOP
	 * Calls getJobInfoByFolder(token,folderName)
	 * getJobInfoByFolder calls logout(String token)
	 * @param technology
	 * @param action
	 * @param script
	 * @param jobId
	 * @return Parameter of Folder (json)
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String getRunAs(String technology, String action, String script, String jobId) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		logger.info("[EXECUTOR-CTRLM] Started CTRLM getInfo service.");
		String token = loginGetToken();		
        if(technology=="CONTROL-M"){
        	String folderName = getOnlyField(token, jobId, "folder");
        	String jobName = getOnlyField(token, jobId, "name");
            if(folderName!=null && action=="INFO"){
            	return trimJsonRunAs(getJobInfoByFolder(token,folderName), folderName, jobName);  	 
            }
        }
        return "Invalid Request";
	}
	
	/**
	 * Returns only the value referred to by "field" key
	 * 
	 * @param token
	 * @param jobId
	 * @param field
	 * @return String 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private String getOnlyField(String token,String jobId,String field) throws JsonParseException, JsonMappingException, IOException{
		try{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		return mapper.readValue(getJobStatus(jobId), JsonNode.class).get(field).asText();
		}catch(NullPointerException e){
			return e.getMessage();
		}
	}
	
	/**
	 * Logs in 
	 * @return  String token
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private  String loginGetToken() throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        JsonNode irisJSON = mapper.readValue("{'username': '"+username+"', 'password': '"+password+"'}", JsonNode.class);
	    HttpEntity<JsonNode> entity = new HttpEntity<JsonNode>(irisJSON);
	    ResponseEntity<String> response = restTemplate.exchange(endPoint+"/session/login", HttpMethod.POST, entity, String.class);
	   
	    if (response.getStatusCode() == HttpStatus.OK) {
	    	
	        return mapper.readValue(response.getBody(), JsonNode.class).get("token").asText();
	    }
	    logger.info("[control m API] token get failed: HttpsStatus not Ok");
	    
		return null;
	}
	
	/**
	 * Logout for the given token;
	 * @param token
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public  String logout(String token) throws JsonParseException, JsonMappingException, IOException{
		HttpHeaders httpHeaders = getHttpHeaders(token);
		HttpEntity<JsonNode> entity = new HttpEntity<JsonNode>(httpHeaders);
		ResponseEntity<String> response = restTemplate.exchange(endPoint+"/session/logout", HttpMethod.POST, entity, String.class);
   
		if (response.getStatusCode() == HttpStatus.OK) {
    	
			return response.getBody();
		}
		logger.info("[control m API] token get failed: HttpsStatus not Ok");
    
		return null;
    
}
	/**
	 * returns details of job if jobName 
	 * @param token
	 * @param Name
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private String getJobInfoByName(String token, String Name) throws JsonParseException, JsonMappingException, IOException{
		
		HttpHeaders httpHeaders = getHttpHeaders(token);
		HttpEntity<JsonNode> entity = new HttpEntity<JsonNode>(httpHeaders);
		try{
			ResponseEntity<String> response = restTemplate.exchange(endPoint+"/run/jobs/status?jobname="+Name, HttpMethod.GET, entity, String.class);
	    if (response.getStatusCode() == HttpStatus.OK) {
	        System.out.println(response.getBody());
	        return response.getBody();
	    }
		}catch(HttpServerErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
	        
		}catch(HttpClientErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
		}
	    logger.info("[control m API] jobInfo get failed: HttpsStatus not Ok");
		return null;
	}
	/**
	 * parses String and returns string with only 
	 * relevant parameters
	 * @param json
	 * @return String json
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private String trimJson(String json) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		ObjectNode objNode= mapper.createObjectNode();
		JsonNode node = mapper.readValue(json, JsonNode.class);
		objNode.put("jobId",node.get("jobId").asText());
		objNode.put("status",node.get("status").asText());
        return objNode.toString();
	}
	/**
	 * Logs in 
	 * gets information a job Folder
	 * Logs out
	 * @param jobId
	 * @return Parameters of all jobs in a Folder in JSON
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private String getJobInfoByFolder(String token,String folderName) throws JsonParseException, JsonMappingException, IOException{
		HttpHeaders httpHeaders = getHttpHeaders(token);
	    HttpEntity<JsonNode> entity = new HttpEntity<JsonNode>(httpHeaders);
	    ResponseEntity<String> response=null;
	    try{
	    	response = restTemplate.exchange(endPoint+"/deploy/jobs?ctm=*&folder="+folderName, HttpMethod.GET, entity, String.class);
        	logout(token);
	    	if (response.getStatusCode() == HttpStatus.OK){
		        String jsonFormattedString = response.getBody().replaceAll("\\\\n","\\\n").replaceAll("\\\\","").replaceAll("\"\\{","{").replaceAll("\\}\"","}");
				System.out.println(jsonFormattedString);
		        return jsonFormattedString;
		    }
		    logger.info("[control m API] jobInfo get failed: HttpsStatus not Ok");
	    }catch(HttpServerErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
		}catch(HttpClientErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
		}
	    if(response!=null)System.out.println(response.getBody());
		return null;
	    
	}
	/**
	 * Logs in 
	 * Gets status of a job
	 * Logs out
	 * @param jobId
	 * @return Parameters of Job in JSON
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private  String getJobStatus(String jobId) throws JsonParseException, JsonMappingException, IOException{
		String token = loginGetToken();
		HttpHeaders httpHeaders = getHttpHeaders(token);
	    HttpEntity<JsonNode> entity = new HttpEntity<JsonNode>(httpHeaders);
		try{
		    ResponseEntity<String> response = restTemplate.exchange(endPoint+"/run/job/"+ jobId+"/status", HttpMethod.GET, entity, String.class);
	    	logout(token);
		    if (response.getStatusCode() == HttpStatus.OK) {
		    	
		        System.out.println(response.getBody());
		        return response.getBody();
		    }
		    logger.info("[control m API] jobStatus get failed: HttpsStatus not Ok");
	    }catch(HttpServerErrorException e) {
	    	String responseBody = e.getResponseBodyAsString();
	        return(responseBody);
		}catch(HttpClientErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
		}
		return "Failed";	    
	}
	
	/**
	 * Logs in 
	 * Reruns a job
	 * Logs out
	 * @param jobId
	 * @return Parameters of Job in JSON
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private  String reRun(String token,String jobId) throws JsonParseException, JsonMappingException, IOException{
		
		HttpHeaders httpHeaders = getHttpHeaders(token);
	    HttpEntity<JsonNode> entity = new HttpEntity<JsonNode>(httpHeaders);
		try{
		    ResponseEntity<String> response = restTemplate.exchange(endPoint+"/run/job/"+ jobId+"/rerun", HttpMethod.POST, entity, String.class);
	    	logout(token);
		    if (response.getStatusCode() == HttpStatus.OK) {
		        System.out.println(response.getBody());
		        return trimJson(response.getBody());
		    }
		    logger.info("[control m API] reRun get failed: HttpsStatus not Ok");
	    }catch(HttpServerErrorException e) {
	    	String responseBody = e.getResponseBodyAsString();
//	        String statusText = e.getStatusText();
	        return(responseBody);
		}catch(HttpClientErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
		}
		return "Failed";
	}
	/**Logs in
	 * Kills Job
	 * Logs Out
	 * @param jobId 
	 * @return Parameters of Job in JSON
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private  String killJob(String jobId) throws JsonParseException, JsonMappingException, IOException{
		String token = loginGetToken();		
		HttpHeaders httpHeaders = getHttpHeaders(token);
	    HttpEntity<JsonNode> entity = new HttpEntity<JsonNode>(httpHeaders);
	   
		try{
		    ResponseEntity<String> response = restTemplate.exchange(endPoint+"/run/job/"+ jobId+"/kill", HttpMethod.POST, entity, String.class);
	    	logout(token);
		    if (response.getStatusCode() == HttpStatus.OK) {
		    	
		        System.out.println(response.getBody());
		        return trimJson(response.getBody());
		    }
		    logger.info("[control m API] jobKill get failed: HttpsStatus not Ok");
	    }catch(HttpServerErrorException e) {
	    	String responseBody = e.getResponseBodyAsString();
//	        String statusText = e.getStatusText();
	        return(responseBody);
		}catch(HttpClientErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
		}
		return "Failed";	
	    
	}
	/**
	 * 
	 * @param token
	 * @param jobId
	 * @return  String Output file
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private  String getJobOutput(String token,String jobId) throws JsonParseException, JsonMappingException, IOException{
		
		HttpHeaders httpHeaders = getHttpHeaders(token);
	    HttpEntity<JsonNode> entity = new HttpEntity<JsonNode>(httpHeaders);
	    try{
		    ResponseEntity<String> response = restTemplate.exchange(endPoint+"/run/job/"+ jobId+"/output", HttpMethod.GET, entity, String.class);
		   
		    if (response.getStatusCode() == HttpStatus.OK) {
		    	System.out.println(response.getBody());
		        return response.getBody();
		    }
		    logger.info("[control m API] getJobOutput failed: HttpsStatus not Ok");	    
	    }catch(HttpServerErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
	        
		}catch(HttpClientErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
		}catch(NullPointerException e){
			return "NO OUTPUT PRESENT";
		}
	    return "Failed";
	}
	/**
	 * 
	 * @param token
	 * @param jobId
	 * @return  String Log File
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private  String getJobLog(String token,String jobId) throws JsonParseException, JsonMappingException, IOException{
		HttpHeaders httpHeaders = getHttpHeaders(token);
	    HttpEntity<JsonNode> entity = new HttpEntity<JsonNode>(httpHeaders);
	    try{
		    ResponseEntity<String> response = restTemplate.exchange(endPoint+"/run/job/"+ jobId+"/log", HttpMethod.GET, entity, String.class);
		   
		    if (response.getStatusCode() == HttpStatus.OK) {
		    	System.out.println(response.getBody());
		    	return response.getBody();
		    }
		    logger.info("[control m API] getJobLog failed: HttpsStatus not Ok");	    
			return "Failed";
		}catch(HttpServerErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
	        
		}catch(HttpClientErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
		}catch(NullPointerException e){
			return "NO lOG PRESENT";
		}
	    return "Failed";
	}
	/**
	 * Logs In
	 * Blocks Job
	 * Logs out
	 * @param jobId
	 * @return String Message from API
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private  String unblockJob(String jobId) throws JsonParseException, JsonMappingException, IOException{
		String token = loginGetToken();		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		HttpHeaders httpHeaders = getHttpHeaders(token);
	    HttpEntity<JsonNode> entity = new HttpEntity<JsonNode>(httpHeaders);
	    try{
		    ResponseEntity<String> response = restTemplate.exchange(endPoint+"/run/job/"+ jobId+"/free", HttpMethod.POST, entity, String.class);
		    logout(token);
		    if (response.getStatusCode() == HttpStatus.OK) {
		    	JsonNode node = mapper.readValue(response.getBody(), JsonNode.class);
		        JsonNode jobStatusNode = node.get("message");
		        String status = jobStatusNode.asText();
		        System.out.println(status);
		        return status;
		    }
		    logger.info("[control m API] unblockJob failed: HttpsStatus not Ok");	    
			return null;
	    }catch(HttpServerErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
	        
		}catch(HttpClientErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
		}
		return "Failed";
	}
	/**
	 * Logs in
	 * blocks Job
	 * Logs Out
	 * @param jobId
	 * @return message from API String
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private  String blockJob(String jobId) throws JsonParseException, JsonMappingException, IOException{
		String token = loginGetToken();		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		HttpHeaders httpHeaders = getHttpHeaders(token);
	    HttpEntity<JsonNode> entity = new HttpEntity<JsonNode>(httpHeaders);
	   try{
		    ResponseEntity<String> response = restTemplate.exchange(endPoint+"/run/job/"+ jobId+"/hold", HttpMethod.POST, entity, String.class);
		    logout(token);
		    if (response.getStatusCode() == HttpStatus.OK) {
		    	JsonNode node = mapper.readValue(response.getBody(), JsonNode.class);
	
		        JsonNode jobStatusNode = node.get("message");
		        String status = jobStatusNode.asText();
		        System.out.println(status);
		        return status;
		    }
		    logger.info("[control m API] blockJob failed: HttpsStatus not Ok");	    
			return null;
	   }catch(HttpServerErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
	        
		}catch(HttpClientErrorException e){
			String responseBody = e.getResponseBodyAsString();
	        String statusText = e.getStatusText();
	        System.out.println(responseBody);
	        System.out.println(statusText);
		}
	return "Failed";
	    
	}
	/**
	 * adds token as the header
	 * @param token
	 * @return
	 */
	private HttpHeaders getHttpHeaders(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.set("Authorization", "Bearer "+token);
    	httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    	return httpHeaders;
    }
    /**
     * overrides ssl verification
     * @return
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    @Bean
    private RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                   .register("http", new PlainConnectionSocketFactory())
                   .register("https", sslConnectionSocketFactory)
                   .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(100);
        CloseableHttpClient httpclient = HttpClients.custom()
                   .setSSLSocketFactory(sslConnectionSocketFactory)
                   .setConnectionManager(cm)
                   .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

                requestFactory.setHttpClient(httpclient);
                RestTemplate restTemplate = new RestTemplate(requestFactory);
                return restTemplate;

    }
    
    /**
     * unblocks given job
     * @param technology
     * @param action
     * @param script
     * @param jobId
     * @return String Message from API
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws InterruptedException
     */
	public String unblockTask(String technology, String action, String script, String jobId) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		logger.info("[EXECUTOR-CTRLM] Started CTRLM runTask service.");
        if(technology=="CONTROL-M"){
            if(action=="UNBLOCK"){
            	return unblockJob(jobId);  	 
            }
        }
        return "Invalid_Request";
	}
	
	/**
	 *  kills task
	 * @param technology
	 * @param action
	 * @param script
	 * @param jobId
	 * @return String  message if failed or status if successful
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String killTask(String technology, String action, String script, String jobId) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		logger.info("[EXECUTOR-CTRLM] Started CTRLM runTask service.");
        if(technology=="CONTROL-M"){
            if(action=="KILL"){
            	return killJob( jobId);  	 
            }
        }
        return "Invalid_Request";
	}
	/**
	 * blocks given job
	 * @param technology
	 * @param action
	 * @param script
	 * @param jobId
	 * @return Message from API
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String blockTask(String technology, String action, String script, String jobId) throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		logger.info("[EXECUTOR-CTRLM] Started CTRLM runTask service.");
        if(technology=="CONTROL-M"){
            if(action=="BLOCK"){
            	return blockJob(jobId);  	 
            }
        }
        return "Invalid_Request";
	}

	private String trimJsonRunAs(String jobInfoByFolder,String folderName,String jobName) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		ObjectMapper jsonMapper = new ObjectMapper();
		ObjectNode objNode= mapper.createObjectNode();
		objNode.put("RunAs",mapper.readValue(jobInfoByFolder, JsonNode.class).get(folderName).get(jobName).get("RunAs").asText());
		return objNode.toString();
	}
}


