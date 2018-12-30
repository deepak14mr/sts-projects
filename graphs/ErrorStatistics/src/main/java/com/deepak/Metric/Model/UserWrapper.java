package com.paypal.Metric.Model;

import java.sql.Timestamp;
import java.util.List;

public class UserWrapper {
	long timecount;
	List<String> alerts;
	List<String> colors;
	List<Integer> data;
	List<Timestamp> ed_ts;
	List<Timestamp> ed_tsend; 
	List<Timestamp> er_ts;
	List<Timestamp> er_tsend;
	List<Timestamp> ah_ts; 
	List<Timestamp> ah_tsend;

	public List<Timestamp> getEd_ts() {
		return ed_ts;
	}


	public void setEd_ts(List<Timestamp> ed_ts) {
		this.ed_ts = ed_ts;
	}


	public List<Timestamp> getEd_tsend() {
		return ed_tsend;
	}


	public void setEd_tsend(List<Timestamp> ed_tsend) {
		this.ed_tsend = ed_tsend;
	}


	public List<Timestamp> getEr_ts() {
		return er_ts;
	}


	public void setEr_ts(List<Timestamp> er_ts) {
		this.er_ts = er_ts;
	}


	public List<Timestamp> getEr_tsend() {
		return er_tsend;
	}


	public void setEr_tsend(List<Timestamp> er_tsend) {
		this.er_tsend = er_tsend;
	}


	public List<Timestamp> getAh_ts() {
		return ah_ts;
	}


	public void setAh_ts(List<Timestamp> ah_ts) {
		this.ah_ts = ah_ts;
	}


	public List<Timestamp> getAh_tsend() {
		return ah_tsend;
	}


	public void setAh_tsend(List<Timestamp> ah_tsend) {
		this.ah_tsend = ah_tsend;
	}


	public long getTimecount() {
		return timecount;
	}


	public void setTimecount(long timecount) {
		this.timecount = timecount;
	}


	public List<String> getAlerts() {
		return alerts;
	}


	public void setAlerts(List<String> alerts) {
		this.alerts = alerts;
	}


	public List<String> getColors() {
		return colors;
	}


	public void setColors(List<String> colors) {
		this.colors = colors;
	}


	public List<Integer> getData() {
		return data;
	}


	public void setData(List<Integer> data) {
		this.data = data;
	}


	


	

	public UserWrapper(long timecount, List<String> alerts, List<String> colors, List<Integer> data,
			List<Timestamp> ed_ts, List<Timestamp> ed_tsend, List<Timestamp> er_ts, List<Timestamp> er_tsend,
			List<Timestamp> ah_ts, List<Timestamp> ah_tsend) {
		super();
		this.timecount = timecount;
		this.alerts = alerts;
		this.colors = colors;
		this.data = data;
		this.ed_ts = ed_ts;
		this.ed_tsend = ed_tsend;
		this.er_ts = er_ts;
		this.er_tsend = er_tsend;
		this.ah_ts = ah_ts;
		this.ah_tsend = ah_tsend;
	}




	
	
}
