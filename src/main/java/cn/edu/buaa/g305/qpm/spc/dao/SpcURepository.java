package cn.edu.buaa.g305.qpm.spc.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.spc.domain.spcu.U;
import cn.edu.buaa.g305.qpm.system.domain.Project;

public interface SpcURepository extends CrudRepository<U
, String> {
	
	U findByName(String name);
	List<U> findByProject(Project project);

}