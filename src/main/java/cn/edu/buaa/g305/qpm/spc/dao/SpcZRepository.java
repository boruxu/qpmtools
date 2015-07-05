package cn.edu.buaa.g305.qpm.spc.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.spc.domain.spcz.Z;
import cn.edu.buaa.g305.qpm.system.domain.Project;

public interface SpcZRepository extends CrudRepository<Z
, String> {
	
	Z findByName(String name);
	List<Z> findByProject(Project project);

}