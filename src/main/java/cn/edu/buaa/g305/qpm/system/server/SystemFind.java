package cn.edu.buaa.g305.qpm.system.server;

import cn.edu.buaa.g305.qpm.system.domain.Organization;
import cn.edu.buaa.g305.qpm.system.domain.Project;
import cn.edu.buaa.g305.qpm.system.domain.ProjectList;

public interface SystemFind {
	
	//确定产品所属的项目，有返回原名称(原名不为空)，数据库无此项目(或原名为空)，则返回默认名“无归属项目”
	Project findProductAffiliation(String name);
	//确定项目所属的组织，有返回原名称(原名不为空)，数据库无此组织(或原名为空)，则返回默认名“无归属组织”
	Organization findProjectAffiliation(String name);
	
	Project findProjectByName(String name);
	Project findProjectById(String id);
	Project deleteProjectByName(String name);
	Project deleteProjectById(String id);
	Project save(Project project,String organization);
	Project update(Project project,String id,String organization);
	
	ProjectList getProjectList();
	ProjectList getProjectistByOrganizationName(String name);
	
	


	
}
