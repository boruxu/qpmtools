package cn.edu.buaa.g305.qpm.spc.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.spc.domain.spcu.SpcU;
import cn.edu.buaa.g305.qpm.system.domain.Project;

public interface SpcURepository extends CrudRepository<SpcU
, String> {
	
	SpcU findByName(String name);
	List<SpcU> findByProject(Project project);

}