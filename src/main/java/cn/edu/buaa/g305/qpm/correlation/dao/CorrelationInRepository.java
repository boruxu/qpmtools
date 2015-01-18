package cn.edu.buaa.g305.qpm.correlation.dao;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationIn;


public interface CorrelationInRepository extends CrudRepository<CorrelationIn
, Long> {
	
	CorrelationIn findByName(String name);

}
