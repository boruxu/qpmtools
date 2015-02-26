package cn.edu.buaa.g305.qpm.risk.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.risk.domain.RiskType;
import cn.edu.buaa.g305.qpm.system.domain.Organization;


public interface RiskTypeRepository extends CrudRepository<RiskType
, String> {
	
	RiskType findByName(String name);
	List<RiskType> findByOrganization(Organization organization);

}