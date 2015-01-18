package cn.edu.buaa.g305.qpm.system.server.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.system.dao.OrganizationRepository;
import cn.edu.buaa.g305.qpm.system.dao.ProjectRepository;
import cn.edu.buaa.g305.qpm.system.server.SystemFind;
@Component
public class SystemFindImp implements SystemFind {
	
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private OrganizationRepository organizationRepository;

	public String findProductAffiliation(String name) {
		
		String defaultName="无从属项目";
		if(name==null)
		{
			return defaultName;
		}
		else if(projectRepository.findByName(name)==null)
		{
			return defaultName;
		}
		return name;
		
	}

	public String findProjectAffiliation(String name) {
		
		String defaultName="无从属项目";
		if(name==null)
		{
			return defaultName;
		}
		else if(organizationRepository.findByName(name)==null)
		{
			return defaultName;
		}
		return name;

	}

}
