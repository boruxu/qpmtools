package cn.edu.buaa.g305.qpm.system.server.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.system.dao.OrganizationRepository;
import cn.edu.buaa.g305.qpm.system.dao.ProjectRepository;
import cn.edu.buaa.g305.qpm.system.domain.Organization;
import cn.edu.buaa.g305.qpm.system.domain.OrganizationList;
import cn.edu.buaa.g305.qpm.system.domain.Project;
import cn.edu.buaa.g305.qpm.system.domain.ProjectList;
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
		
		Project project=projectRepository.findByName(name);
		if(project==null)
		{
			project=new Project();
			//找不到资源，设置错误信息和状态码
			project.setErrorOutput("名为"+name+"的项目不存在",HttpStatus.NOT_FOUND);
			return project;
		}
		else{
			project.setHttpStatus(HttpStatus.OK);
			return project;
		}
	}

	@Override
	public Project findProjectById(String id) {
		
		Project project=projectRepository.findOne(id);
		if(project==null)
		{
			project=new Project();
			project.setErrorOutput("id为"+id+"的项目不存在",HttpStatus.NOT_FOUND);
			return project;
		}
		else {
			project.setHttpStatus(HttpStatus.OK);
			return project;
		}
	}

	@Override
	public Project deleteProjectByName(String name) {
		Project project=findProjectByName(name);
		if(project.getError()==null)
		{
			projectRepository.delete(project.getId());
		}
		
		return project;
	}

	@Override
	public Project deleteProjectById(String id) {
		Project project=findProjectById(id);
		if(project.getError()==null)
		{
			projectRepository.delete(id);
		}
		
		return project;
	}

	@Override
	public Project save(Project project,String organization) {
		
		Organization organizationDB=findProjectAffiliation(organization);
		project.setOrganization(organizationDB);
		try {
			project=projectRepository.save(project);
		} catch (DuplicateKeyException e) {
			project.setErrorOutput("名字重复，请重新命名", HttpStatus.BAD_REQUEST);
			return project;
		}
		
		project.setHttpStatus(HttpStatus.CREATED);
		return project;
	}

	@Override
	public Project update(Project project,String id,String organization) {
		Project projectDB=findProjectById(id);
		if(projectDB.getError()==null)
		{
			project.setId(id);
			project=save(project,organization);
			if(project.getError()==null)
			{
				project.setHttpStatus(HttpStatus.OK);
				return project;
			}
			else {
				return project;
			}
			 
		}
		else {
			return projectDB;
		}
	}

	@Override
	public ProjectList getProjectList() {
		List<Project> projects= (List<Project>) projectRepository.findAll();
		ProjectList projectList=new ProjectList();
		projectList.setList(projects);
		projectList.setHttpStatus(HttpStatus.OK);
		return projectList;
	}

	@Override
	public ProjectList getProjectistByOrganizationName(String name) {
		ProjectList projectList=new ProjectList();
		Organization organization=organizationRepository.findByName(name);
		if(organization==null)
		{
			projectList.setError("名为"+name+"组织不存在");
			projectList.setHttpStatus(HttpStatus.NOT_FOUND);
			projectList.setList(new ArrayList<Project>());
			return projectList;
		}
		else {
			projectList.setOrganization(name);
			projectList.setList(projectRepository.findByOrganization(organization));
			projectList.setHttpStatus(HttpStatus.OK);
			return projectList;
		}
	}

	@Override
	public Organization getOrganizationByName(String name) {
		Organization organization=organizationRepository.findByName(name);
		if(organization==null)
		{
			organization=new Organization();
			//找不到资源，设置错误信息和状态码
			organization.setErrorOutput("名为"+name+"的组织不存在",HttpStatus.NOT_FOUND);
			return organization;
		}
		else{
			organization.setHttpStatus(HttpStatus.OK);
			return organization;
		}
	}

	@Override
	public Organization getOrganizationById(String id) {
		Organization organization=organizationRepository.findOne(id);
		if(organization==null)
		{
			organization=new Organization();
			organization.setErrorOutput("id为"+id+"的组织不存在",HttpStatus.NOT_FOUND);
			return organization;
		}
		else {
			organization.setHttpStatus(HttpStatus.OK);
			return organization;
		}
	}

	@Override
	public Organization deleteOrganizationByName(String name) {
		Organization organization=getOrganizationByName(name);
		if(organization.getError()==null)
		{
			organizationRepository.delete(organization.getId());
		}
		
		return organization;
	}

	@Override
	public Organization deleteOrganizationById(String id) {
		Organization organization=getOrganizationById(id);
		if(organization.getError()==null)
		{
			organizationRepository.delete(id);
		}
		
		return organization;
	}

	@Override
	public Organization save(Organization organization) {

		try {
			organization=organizationRepository.save(organization);
		} catch (DuplicateKeyException e) {
			organization.setErrorOutput("名字重复，请重新命名", HttpStatus.BAD_REQUEST);
			return organization;
		}
		
		organization.setHttpStatus(HttpStatus.CREATED);
		return organization;
	}

	@Override
	public Organization update(Organization organization, String id) {
		Organization organizationDB=getOrganizationById(id);
		if(organizationDB.getError()==null)
		{
			organization.setId(id);
			organization=save(organization);
			if(organization.getError()==null)
			{
				organization.setHttpStatus(HttpStatus.OK);
				return organization;
			}
			else {
				return organization;
			}
			 
		}
		else {
			return organizationDB;
		}
	}

	@Override
	public OrganizationList getOrganizationList() {
		List<Organization> organizations= (List<Organization>) organizationRepository.findAll();
		OrganizationList organizationList=new OrganizationList();
		organizationList.setList(organizations);
		organizationList.setHttpStatus(HttpStatus.OK);
		return organizationList;
	}
	
	
	
}
