package cn.edu.buaa.g305.qpm.processManagement.dao;

import org.springframework.data.repository.CrudRepository;
import cn.edu.buaa.g305.qpm.processManagement.domian.Activity;

public interface ActivityRepository extends CrudRepository<Activity
, String> {
	
	Activity findByName(String name);

}