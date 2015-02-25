package cn.edu.buaa.g305.qpm.system.dao;

import org.springframework.data.repository.CrudRepository;
import cn.edu.buaa.g305.qpm.system.domain.Organization;


public interface OrganizationRepository extends CrudRepository<Organization, String>{
	
	Organization findByName(String name);
	
}
