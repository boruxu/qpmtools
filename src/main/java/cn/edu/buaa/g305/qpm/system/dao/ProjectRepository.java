package cn.edu.buaa.g305.qpm.system.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.system.domain.Organization;
import cn.edu.buaa.g305.qpm.system.domain.Project;

public interface  ProjectRepository  extends CrudRepository<Project, String>{
	
	Project findByName(String name);
	List<Project> findByOrganization(Organization organization);

}
