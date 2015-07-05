package cn.edu.buaa.g305.qpm.spc.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.spc.domain.spcxr.XR;
import cn.edu.buaa.g305.qpm.system.domain.Project;


public interface SpcXRRepository extends CrudRepository<XR
, String> {
	
	XR findByName(String name);
	List<XR> findByProject(Project project);

}