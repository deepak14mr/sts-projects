package com.paypal.Metric.Model;

import java.sql.Timestamp;
import java.util.List;

public class AdminWrapper {
	List<Timestamp> ed_ts;
	List<Timestamp> ed_tsend; 
	List<Timestamp> er_ts;
	List<Timestamp> er_tsend;
	List<Timestamp> ah_ts; 
	List<Timestamp> ah_tsend;
	List<String> alerts;
	List<Integer> ed;
	List<Integer> ed_fail; 
	List<Integer> er;
	List<Integer> er_fail; 
	List<Integer> ah;
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

	public void setEr_ts2(List<Timestamp> er_tsend) {
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

	public List<String> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<String> alerts) {
		this.alerts = alerts;
	}

	public List<Integer> getEd() {
		return ed;
	}

	public void setEd(List<Integer> ed) {
		this.ed = ed;
	}

	public List<Integer> getEd_fail() {
		return ed_fail;
	}

	public void setEd_fail(List<Integer> ed_fail) {
		this.ed_fail = ed_fail;
	}

	public List<Integer> getEr() {
		return er;
	}

	public void setEr(List<Integer> er) {
		this.er = er;
	}

	public List<Integer> getEr_fail() {
		return er_fail;
	}

	public void setEr_fail(List<Integer> er_fail) {
		this.er_fail = er_fail;
	}

	public List<Integer> getAh() {
		return ah;
	}

	public void setAh(List<Integer> ah) {
		this.ah = ah;
	}

	public List<Integer> getAh_fail() {
		return ah_fail;
	}

	public void setAh_fail(List<Integer> ah_fail) {
		this.ah_fail = ah_fail;
	}

	List<Integer> ah_fail;



	public AdminWrapper(List<Timestamp> ed_ts, List<Timestamp> ed_tsend, List<Timestamp> er_ts, List<Timestamp> er_tsend,
			List<Timestamp> ah_ts, List<Timestamp> ah_tsend, List<String> alerts, List<Integer> ed,
			List<Integer> ed_fail, List<Integer> er, List<Integer> er_fail, List<Integer> ah, List<Integer> ah_fail) {
		super();
		this.ed_ts = ed_ts;
		this.ed_tsend = ed_tsend;
		this.er_ts = er_ts;
		this.er_tsend = er_tsend;
		this.ah_ts = ah_ts;
		this.ah_tsend = ah_tsend;
		this.alerts = alerts;
		this.ed = ed;
		this.ed_fail = ed_fail;
		this.er = er;
		this.er_fail = er_fail;
		this.ah = ah;
		this.ah_fail = ah_fail;
	}

}
