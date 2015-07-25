package cn.edu.buaa.g305.qpm.regression.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import cn.edu.buaa.g305.qpm.regression.domain.Regress;
import cn.edu.buaa.g305.qpm.system.domain.Project;

public interface RegressRepository extends CrudRepository<Regress
, String> {
	
	Regress findByName(String name);
	List<Regress> findByProject(Project project);

}