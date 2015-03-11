package cn.edu.buaa.g305.qpm.mc.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import cn.edu.buaa.g305.qpm.mc.domain.MC;
import cn.edu.buaa.g305.qpm.system.domain.Project;


public interface MCRepository extends CrudRepository<MC
, String> {
	
	MC findByName(String name);
	List<MC> findByProject(Project project);


}