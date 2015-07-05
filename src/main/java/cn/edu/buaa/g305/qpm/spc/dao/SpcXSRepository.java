package cn.edu.buaa.g305.qpm.spc.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.spc.domain.spcxr.XR;
import cn.edu.buaa.g305.qpm.spc.domain.spcxs.XS;
import cn.edu.buaa.g305.qpm.system.domain.Project;

public interface SpcXSRepository extends CrudRepository<XS
, String> {
	
	XS findByName(String name);
	List<XR> findByProject(Project project);

}