package com.paypal.Metric.Model;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
 


@Entity
@Table(name = "optimus_metric")
public class Times {
	@Id
	@Column(name = "ID")
	private int id;
	@Column(name = "ALERT_ID")
	private String alertId;
	@Column(name = "START_TS")
	private Timestamp startTime;
	
	@Column(name = "END_TS")
	private Timestamp endTime;
	
	@Column(name = "MODULE")
	private String module;
	
	@Column(name = "STATUS")
	private int status;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "TOTAL_TS")
	private Timestamp net_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlertId() {
		return alertId;
	}
	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp start_time) {
		this.startTime = start_time;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp end_time) {
		this.endTime = end_time;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getNet_time() {
		return net_time;
	}
	public void setNet_time(Timestamp net_time) {
		this.net_time = net_time;
	}
	public String toString(){
		System.out.println(id + " " + status);
		return module;
	}
	
}