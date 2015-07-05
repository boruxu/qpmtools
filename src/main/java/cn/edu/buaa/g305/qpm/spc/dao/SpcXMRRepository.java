package cn.edu.buaa.g305.qpm.spc.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import cn.edu.buaa.g305.qpm.spc.domain.spcxmr.XmR;
import cn.edu.buaa.g305.qpm.system.domain.Project;

public interface SpcXMRRepository extends CrudRepository<XmR
, String> {
	
	XmR findByName(String name);
	List<XmR> findByProject(Project project);

}