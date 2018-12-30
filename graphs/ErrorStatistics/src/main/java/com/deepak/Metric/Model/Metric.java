package com.paypal.Metric.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "myusers")
public class Metric {

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
    @JoinColumn(name = "iris_id")
	private Alert alert;

	@Column(name = "module_name")
	private String moduleName;

	@Column(name = "module_status")
	private Integer moduleStatus;

	@Column(name = "module_desc")
	private String moduleDescription;

	@Column(name = "module_start_ts")
	private Date moduleStartTime;

	@Column(name = "module_end_ts")
	private Date moduleEndTime;

	@Column(name = "status")
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getModuleStatus() {
		return moduleStatus;
	}

	public void setModuleStatus(Integer moduleStatus) {
		this.moduleStatus = moduleStatus;
	}

	public String getModuleDescription() {
		return moduleDescription;
	}

	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

	public Date getModuleStartTime() {
		return moduleStartTime;
	}

	public void setModuleStartTime(Date moduleStartTime) {
		this.moduleStartTime = moduleStartTime;
	}

	public Date getModuleEndTime() {
		return moduleEndTime;
	}

	public void setModuleEndTime(Date moduleEndTime) {
		this.moduleEndTime = moduleEndTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}