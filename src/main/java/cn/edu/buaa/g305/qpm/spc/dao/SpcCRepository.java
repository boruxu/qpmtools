package cn.edu.buaa.g305.qpm.spc.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.spc.domain.spcc.C;
import cn.edu.buaa.g305.qpm.system.domain.Project;

public interface SpcCRepository extends CrudRepository<C
, String> {
	
	C findByName(String name);
	List<C> findByProject(Project project);

}