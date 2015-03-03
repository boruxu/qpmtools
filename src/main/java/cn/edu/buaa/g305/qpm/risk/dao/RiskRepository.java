package cn.edu.buaa.g305.qpm.risk.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.risk.domain.Risk;
import cn.edu.buaa.g305.qpm.system.domain.Organization;
import cn.edu.buaa.g305.qpm.system.domain.Project;


public interface RiskRepository extends CrudRepository<Risk
, String> {
	
	Risk findByName(String name);
	List<Risk> findByProject(Project project);

}