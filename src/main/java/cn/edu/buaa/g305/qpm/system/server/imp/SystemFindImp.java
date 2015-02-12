package cn.edu.buaa.g305.qpm.system.server.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.spc.dao.SpcXRRepository;
import cn.edu.buaa.g305.qpm.system.dao.OrganizationRepository;
import cn.edu.buaa.g305.qpm.system.dao.ProjectRepository;
import cn.edu.buaa.g305.qpm.system.server.SystemFind;
@Component
public class SystemFindImp implements SystemFind {
	
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private OrganizationRepository organizationRepository;
	@Autowired
	private SpcXRRepository spcxrRepository;

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
		
		String defaultName="无从属组织";
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
	
	public boolean exsitOrganization(String organization,String error)
	{
		if(organizationRepository.findByName(organization)==null)
		{
			error="该组织不存在，请创建！";
			return false;
		}
		return true;
	}
	
	public boolean exsitProject(String project,String error)
	{
		String[] nameSpaceStrings=project.split(".");
		if(nameSpaceStrings.length!=2)
		{
			error="名字不合法,名字格式为{组织名}.{项目名}";
			return false;
		}
		if(exsitOrganization(nameSpaceStrings[0],error)==true)
		{
			if(projectRepository.findByName(project)==null)
			{
				error="该项目不存在，请创建!";
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}



	



}
