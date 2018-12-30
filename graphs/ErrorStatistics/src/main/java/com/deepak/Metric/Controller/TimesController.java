package com.paypal.Metric.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paypal.Metric.service.AlertService;
//import com.paypal.opsrobot.model.Alert;

@Controller
public class TimesController {
	@Autowired
	private AlertService alertservice;
	int c=0;
	int flag=0;

   
   @RequestMapping(value="/userchart", method = RequestMethod.GET)
   public String loadsUserChart(){
   		return "userchart.html";
	}
   @RequestMapping(value="/areastockchart", method = RequestMethod.GET)
   public String loadsAreaChart(){
   		return "areastockchart.html";
	}
   @RequestMapping(value="/barscrollchart", method = RequestMethod.GET)
   public String loadsBarChart(){
   		return "barscrollchart.html";
	}
//   @RequestMapping(value="/areachartjson", method = RequestMethod.GET)
//   @ResponseBody
//   public String sendsAreaChartJson(){
//	   ObjectMapper mapper = new ObjectMapper();
//       String json = "";
//	   try {
//		json = mapper.writeValueAsString(alertservice.areaChartService());
//	} catch (JsonProcessingException | ParseException e) {
//		e.printStackTrace();
//	}
   
//
//   		return json;
//	}
   @RequestMapping(value = { "/areachartjson" }, method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Object> loadAlertGraph() throws ParseException {
       return new ResponseEntity<Object>( alertservice.areaChartService(), HttpStatus.OK );
   }
   @RequestMapping(value = { "/barscrollchartjson" }, method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Object> loadBarScrollGraph() throws ParseException  {
       return new ResponseEntity<Object>( alertservice.barChartService(), HttpStatus.OK );
   }
   @RequestMapping(value="/adminchart", method = RequestMethod.GET)
   public String loadsAdminChart(){
   		return "adminchart.html";
	}
 
   
//   @RequestMapping(value="/adminchart", method = RequestMethod.GET)
//   public @ResponseBody String makesAreaChart(){
//   		return "areachart.html";
//	}
//	
// @RequestMapping(value= "/adminchartjson", method = RequestMethod.GET)
//	public @ResponseBody AdminWrapper makesHighcharts(){
//		List<String> alerts = timeservice.loadAlertIds();
//		List<Integer> ed = new ArrayList<>();
//		List<Integer> ed_fail = new ArrayList<>();
//		List<Integer> er = new ArrayList<>();
//		List<Integer> er_fail = new ArrayList<>();
//		List<Integer> ah = new ArrayList<>();
//		List<Integer> ah_fail = new ArrayList<>();
//		List<Timestamp> ah_ts = new ArrayList<>();
//		List<Timestamp> ah_tsend = new ArrayList<>();
//		List<Timestamp> ed_ts = new ArrayList<>();
//		List<Timestamp> ed_tsend = new ArrayList<>();
//		List<Timestamp> er_ts = new ArrayList<>();
//		List<Timestamp> er_tsend = new ArrayList<>();
//		
//		alerts.forEach(alert->{
//			int[] count = new int[3];
//			for(int i=0;i<3;i++){
//				count[i]=0;
//			}
//			List<Times> timebyAid = timeservice.loadByAlerts(alert);
//			timebyAid.forEach(time->{
//				if(time.getModule().equals("ERROR_DETECTION")){
//					//System.out.println(alert+" "+"ERROR_DETECTION"+ " "+ time.getStatus() );
//					ed.add(time.getStatus());
//					ed_fail.add(1-time.getStatus());
//					ed_ts.add(time.getStartTime());
//					ed_tsend.add(time.getEndTime());
//					count[0]=1;
//				}
//				if(time.getModule().equals("ERROR_RESOLUTION")){
//					//System.out.println(alert+" "+"ERROR_RESOLUTION"+ " "+ time.getStatus() );
//					er.add(time.getStatus());
//					er_fail.add(1-time.getStatus());
//					count[1]=1;
//					er_ts.add(time.getStartTime());
//					er_tsend.add(time.getEndTime());
//				}
//				if(time.getModule().equals("ACTION_HANDLER")){
//					//System.out.println(alert+" "+"ACTION_HANDLER"+ " "+ time.getStatus() );
//					ah.add(time.getStatus());
//					ah_fail.add(1-time.getStatus());
//					count[2]=1;
//					ah_ts.add(time.getStartTime());
//					ah_tsend.add(time.getEndTime());
//				}
//			});
//			for(int i=0;i<3;i++){
//				if(count[i]==0){
//					if(i==0){
//					//	System.out.println(alert+" "+"ERROR_DETECTION");
//						ed.add(0);
//						ed_fail.add(0);
//						ed_ts.add(timebyAid.get(0).getStartTime());
//						ed_tsend.add(timebyAid.get(0).getStartTime());
//					}else if(i==1){
//					//	System.out.println(alert+" "+"ERROR_RESOLUTION");
//						er.add(0);
//						er_fail.add(0);
//						er_ts.add(timebyAid.get(0).getStartTime());
//						er_tsend.add(timebyAid.get(0).getStartTime());
//					}else if(i==2){
//					//	System.out.println(alert+" "+"ACTION_HANDLER");
//						ah.add(0);
//						ah_fail.add(0);
//						ah_ts.add(timebyAid.get(0).getStartTime());
//						ah_tsend.add(timebyAid.get(0).getStartTime());
//					}
//				}			
//			}
//
//			
//			
//		});
//		
//		
//		AdminWrapper adminwrapper = new AdminWrapper(ed_ts,ed_tsend,er_ts,er_tsend,ah_ts,ah_tsend,alerts,ed,ed_fail,er,er_fail,ah,ah_fail);
//		return adminwrapper;
//	
//	}
   
}
