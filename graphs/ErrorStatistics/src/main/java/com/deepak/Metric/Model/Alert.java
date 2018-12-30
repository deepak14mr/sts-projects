package com.paypal.Metric.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "myusers")
public class Alert {

	@Id
	@Column(name = "IRIS_ID", unique = true)
	private String alertid;

	@Column(name = "JOB_NAME")
	private String jobName;

	@Column (name = "JOB_RUN_ID")
	private String suborigin;

	@Column(name = "ORIGIN")
	private String origin;

	@Column(name = "CDP_UNIT")
	private String cdpUnit;

	@Column(name = "TECH_CATEGORY")
	private String technology;

	@Column(name = "SEVERITY")
	private Integer severity;

	@Column(name = "HOST_NAME")
	private String hostname;

	@Column(name = "IRIS_ERROR_MSG")
	private String message;

	@Column(name = "IRIS_DETECT_TS")
	private Date display_ts;

	@Column(name = "JOB_LOGICAL_TS")
	private Date logical_ts;

	@Column(name = "JOB_START_TS")
	private Date start_ts;

	@Column(name = "AUTO_REMEDIATION_ERROR_CODE")
	private String autoRemediationErrorCode;

	@Column(name = "AUTO_REMEDIATION_STATUS")
	private Integer autoRemediationStatus;

	@Column(name = "AUTO_REMEDIATION_DESC")
	private String autoRemediationDescription;

	@Column(name = "STATUS")
	private Integer status;

	@Transient
	private Date first_ts;

	@Transient
	private Date last_ts;

	@Transient
	private Date ack_ts;

	@Transient
	private String ackuid;

	@Transient
	private String displayuid;

	@Transient
	private String ticketid;

	@Transient
	private Date ticket_ts;

	@Transient
	private String ticketuid;

	@Transient
	private Integer alertProgress;

	@OneToMany(mappedBy="alert")
	private List<Metric> metric;

	public String getAlertid() {
		return alertid;
	}

	public void setAlertid(String alertid) {
		this.alertid = alertid;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getSuborigin() {
		return suborigin;
	}

	public void setSuborigin(String suborigin) {
		this.suborigin = suborigin;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getCdpUnit() {
		return cdpUnit;
	}

	public void setCdpUnit(String cdpUnit) {
		this.cdpUnit = cdpUnit;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public Integer getSeverity() {
		return severity;
	}

	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDisplay_ts() {
		return display_ts;
	}

	public void setDisplay_ts(Date display_ts) {
		this.display_ts = display_ts;
	}

	public Date getLogical_ts() {
		return logical_ts;
	}

	public void setLogical_ts(Date logical_ts) {
		this.logical_ts = logical_ts;
	}

	public Date getStart_ts() {
		return start_ts;
	}

	public void setStart_ts(Date start_ts) {
		this.start_ts = start_ts;
	}

	public String getAutoRemediationErrorCode() {
		return autoRemediationErrorCode;
	}

	public void setAutoRemediationErrorCode(String autoRemediationErrorCode) {
		this.autoRemediationErrorCode = autoRemediationErrorCode;
	}

	public Integer getAutoRemediationStatus() {
		return autoRemediationStatus;
	}

	public void setAutoRemediationStatus(Integer autoRemediationStatus) {
		this.autoRemediationStatus = autoRemediationStatus;
	}

	public String getAutoRemediationDescription() {
		return autoRemediationDescription;
	}

	public void setAutoRemediationDescription(String autoRemediationDescription) {
		this.autoRemediationDescription = autoRemediationDescription;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getFirst_ts() {
		return first_ts;
	}

	public void setFirst_ts(Date first_ts) {
		this.first_ts = first_ts;
	}

	public Date getLast_ts() {
		return last_ts;
	}

	public void setLast_ts(Date last_ts) {
		this.last_ts = last_ts;
	}

	public Date getAck_ts() {
		return ack_ts;
	}

	public void setAck_ts(Date ack_ts) {
		this.ack_ts = ack_ts;
	}

	public String getAckuid() {
		return ackuid;
	}

	public void setAckuid(String ackuid) {
		this.ackuid = ackuid;
	}

	public String getDisplayuid() {
		return displayuid;
	}

	public void setDisplayuid(String displayuid) {
		this.displayuid = displayuid;
	}

	public String getTicketid() {
		return ticketid;
	}

	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}

	public Date getTicket_ts() {
		return ticket_ts;
	}

	public void setTicket_ts(Date ticket_ts) {
		this.ticket_ts = ticket_ts;
	}

	public String getTicketuid() {
		return ticketuid;
	}

	public void setTicketuid(String ticketuid) {
		this.ticketuid = ticketuid;
	}

	public Integer getAlertProgress() {
		return alertProgress;
	}

	public void setAlertProgress(Integer alertProgress) {
		this.alertProgress = alertProgress;
	}

	public List<Metric> getMetric() {
		return metric;
	}

	public void setMetric(List<Metric> metric) {
		this.metric = metric;
	}
}

