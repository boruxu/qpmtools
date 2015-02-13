package cn.edu.buaa.g305.qpm.spc.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.spc.domain.spcxr.SpcXR;
import cn.edu.buaa.g305.qpm.system.domain.Project;


public interface SpcXRRepository extends CrudRepository<SpcXR
, String> {
	
	SpcXR findByName(String name);
	List<SpcXR> findByProject(Project project);

}