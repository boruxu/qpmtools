package cn.edu.buaa.g305.qpm.spc.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import cn.edu.buaa.g305.qpm.spc.domain.spcxmr.SpcXMR;
import cn.edu.buaa.g305.qpm.system.domain.Project;

public interface SpcXMRRepository extends CrudRepository<SpcXMR
, String> {
	
	SpcXMR findByName(String name);
	List<SpcXMR> findByProject(Project project);

}