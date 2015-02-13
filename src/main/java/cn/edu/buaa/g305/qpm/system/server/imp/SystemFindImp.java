package cn.edu.buaa.g305.qpm.system.server.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.system.dao.OrganizationRepository;
import cn.edu.buaa.g305.qpm.system.dao.ProjectRepository;
import cn.edu.buaa.g305.qpm.system.domain.Organization;
import cn.edu.buaa.g305.qpm.system.domain.Project;
import cn.edu.buaa.g305.qpm.system.server.SystemFind;
@Component
public class SystemFindImp implements SystemFind {
	
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private OrganizationRepository organizationRepository;


	public Project findProductAffiliation(String name) {
		
		String defaultName="缺省项目";
		if(name==null||name.equals(defaultName)||projectRepository.findByName(name)==null)
		{
			Project project=projectRepository.findByName(defaultName);
			if(project==null)
			{
				project=new Project();
				project.setName(defaultName);
				Organization organization=findProjectAffiliation(null);
				project.setOrganization(organization);
				return projectRepository.save(project);
			}
			else{
				return project;
			}			
		}
		else 
		{
			return projectRepository.findByName(name);
		}
		
	}

	public Organization findProjectAffiliation(String name) {
		
		String defaultName="缺省组织";
		if(name==null||name.equals(defaultName)||organizationRepository.findByName(name)==null)
		{
			Organization organization=organizationRepository.findByName(defaultName);
			if(organization==null)
			{
				organization=new Organization();
				organization.setName(defaultName);				
				return organizationRepository.save(organization);
			}
			else{
				return organization;
			}			
		}
		else 
		{
			return organizationRepository.findByName(name);
		}

	}

	public Project findProjectByName(String name) {
		
		return projectRepository.findByName(name);
	}
	
}
