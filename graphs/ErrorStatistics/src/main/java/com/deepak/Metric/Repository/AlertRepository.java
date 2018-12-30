package com.paypal.Metric.Repository;

import java.util.Date;
import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paypal.Metric.Model.Alert;
import java.lang.String;

@Repository
public interface AlertRepository extends CrudRepository<Alert, String> {
	List<Alert> findAll();
	Optional<Alert> findByAlertid(String alertid);

	@Query(value="SELECT COUNT(*)  FROM  `myusers` WHERE DATE_FORMAT(`iris_detect_ts`, '%Y-%m-%d')=?1 AND `auto_remediation_status`=?2", nativeQuery = true)
		int findByDateAndStatus(Date datearg,int status);
	
	@Query(value="SELECT COUNT(*) FROM `myusers` WHERE DATE_FORMAT(`iris_detect_ts`, '%Y-%m-%d')=?1", nativeQuery = true)
	  	int findByDate(Date date);

	@Query(value="SELECT DISTINCT DATE_FORMAT(`iris_detect_ts`, '%Y-%m-%d') FROM  `myusers` ORDER BY DATE_FORMAT(`iris_detect_ts`, '%Y-%m-%d')", nativeQuery = true)
	List<String> findDistinctDates();
}



