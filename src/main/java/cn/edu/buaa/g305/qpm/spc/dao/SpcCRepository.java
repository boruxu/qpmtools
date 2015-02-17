package cn.edu.buaa.g305.qpm.spc.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.spc.domain.spcc.SpcC;
import cn.edu.buaa.g305.qpm.system.domain.Project;

public interface SpcCRepository extends CrudRepository<SpcC
, String> {
	
	SpcC findByName(String name);
	List<SpcC> findByProject(Project project);

}