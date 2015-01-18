package cn.edu.buaa.g305.qpm.system.dao;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.system.domain.Project;

public interface  ProjectRepository  extends CrudRepository<Project, Long>{
	
	Project findByName(String name);	

}
