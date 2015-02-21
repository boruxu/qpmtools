package cn.edu.buaa.g305.qpm.spc.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.spc.domain.spcz.SpcZ;
import cn.edu.buaa.g305.qpm.system.domain.Project;

public interface SpcZRepository extends CrudRepository<SpcZ
, String> {
	
	SpcZ findByName(String name);
	List<SpcZ> findByProject(Project project);

}