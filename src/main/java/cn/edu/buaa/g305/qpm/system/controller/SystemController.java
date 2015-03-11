package cn.edu.buaa.g305.qpm.system.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.buaa.g305.qpm.system.domain.Organization;
import cn.edu.buaa.g305.qpm.system.domain.OrganizationList;
import cn.edu.buaa.g305.qpm.system.domain.Project;
import cn.edu.buaa.g305.qpm.system.domain.ProjectList;
import cn.edu.buaa.g305.qpm.system.domain.User;
import cn.edu.buaa.g305.qpm.system.server.SystemFind;

@Controller
@RequestMapping("/api/system")
public class SystemController {
	
	@Autowired
	private SystemFind systemFind;
	

	@RequestMapping(value="/project",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<Project> create(@RequestBody ProjectAndOrganizationVO projectVO)
	{
		Project project=new Project();
		project.setName(projectVO.getName());
		project.setDescription(projectVO.getDescription());
		project=systemFind.save(project, projectVO.getOrganization());
		if(project.getError()==null)
		{
			addAllLink(project, project.getId(), project.getName());
		}
		project.add(linkTo(methodOn(SystemController.class).create(projectVO)).withRel("create"));
		
		return new ResponseEntity<Project>(project,project.getHttpStatus());
		
	}
	@RequestMapping(value="/organization",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<Organization> createO(@RequestBody ProjectAndOrganizationVO vo)
	{
		Organization organization=new Organization();
		organization.setName(vo.getName());
		organization.setDescription(vo.getDescription());
		organization=systemFind.save(organization);
		if(organization.getError()==null)
		{
			addAllLink(organization, organization.getId(), organization.getName());
		}
		organization.add(linkTo(methodOn(SystemController.class).createO(null)).withRel("create"));
		
		return new ResponseEntity<Organization>(organization,organization.getHttpStatus());
		
	}
	@RequestMapping(value="/user",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<User> create(@RequestBody User user)
	{

		user=systemFind.save(user);
		if(user.getError()==null)
		{
			addAllLink(user);
		}
		user.add(linkTo(methodOn(SystemController.class).create(user)).withRel("create"));
		
		return new ResponseEntity<User>(user,user.getHttpStatus());
	
	}
	@RequestMapping(value="/project/{id}",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<Project> update(@RequestBody ProjectAndOrganizationVO projectVO
			,@PathVariable String id)
	{
		Project project=new Project();
		project.setName(projectVO.getName());
		project.setDescription(projectVO.getDescription());
		project=systemFind.update(project, id, projectVO.getOrganization());

		if(project.getError()==null)
		{
			addAllLink(project, project.getId(), project.getName());
		}
		project.add(linkTo(methodOn(SystemController.class).create(projectVO)).withRel("create"));
		return new ResponseEntity<Project>(project,project.getHttpStatus());
		
	}
	@RequestMapping(value="/organization/{id}",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<Organization> updateO(@RequestBody ProjectAndOrganizationVO vo
			,@PathVariable String id)
	{
		Organization organization=new Organization();
		organization.setName(vo.getName());
		organization.setDescription(vo.getDescription());
		organization=systemFind.update(organization, id);

		if(organization.getError()==null)
		{
			addAllLink(organization, organization.getId(), organization.getName());
		}
		organization.add(linkTo(methodOn(SystemController.class).createO(null)).withRel("create"));
		return new ResponseEntity<Organization>(organization,organization.getHttpStatus());
		
	}
	@RequestMapping(value="/user/{id}",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<User> update(@RequestBody User user
			,@PathVariable String id)
	{
		
		user=systemFind.update(user, id);

		if(user.getError()==null)
		{
			addAllLink(user);
		}
		user.add(linkTo(methodOn(SystemController.class).create(user)).withRel("create"));
		return new ResponseEntity<User>(user,user.getHttpStatus());
		
	}
	@RequestMapping(value="/project/{id}",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<Project> getById(@PathVariable String id)
	{
		Project project=new Project();
		project=systemFind.findProjectById(id);

		if(project.getError()==null)
		{
			addAllLink(project, project.getId(), project.getName());
		}
		project.add(linkTo(methodOn(SystemController.class).create(new ProjectAndOrganizationVO())).withRel("create"));
		return new ResponseEntity<Project>(project,project.getHttpStatus());
		
	}
	@RequestMapping(value="/organization/{id}",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<Organization> getByIdO(@PathVariable String id)
	{
		Organization organization=new Organization();
		organization=systemFind.getOrganizationById(id);

		if(organization.getError()==null)
		{
			addAllLink(organization, organization.getId(), organization.getName());
		}
		organization.add(linkTo(methodOn(SystemController.class).createO(null)).withRel("create"));
		return new ResponseEntity<Organization>(organization,organization.getHttpStatus());
		
	}
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<User> getUserById(@PathVariable String id)
	{
		User user=new User();
		user=systemFind.getUserById(id);

		if(user.getError()==null)
		{
			addAllLink(user);
		}
		user.add(linkTo(methodOn(SystemController.class).create(user)).withRel("create"));
		return new ResponseEntity<User>(user,user.getHttpStatus());
		
	}
	
	@RequestMapping(value="/project/byName/{name}",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<Project> getByName(@PathVariable String name)
	{
		Project project=new Project();
		project=systemFind.findProjectByName(name);

		if(project.getError()==null)
		{
			addAllLink(project, project.getId(), project.getName());
		}
		project.add(linkTo(methodOn(SystemController.class).create(new ProjectAndOrganizationVO())).withRel("create"));
		return new ResponseEntity<Project>(project,project.getHttpStatus());
		
	}
	@RequestMapping(value="/organization/byName/{name}",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<Organization> getByNameO(@PathVariable String name)
	{
		Organization organization=new Organization();
		organization=systemFind.getOrganizationByName(name);

		if(organization.getError()==null)
		{
			addAllLink(organization, organization.getId(), organization.getName());
		}
		organization.add(linkTo(methodOn(SystemController.class).createO(null)).withRel("create"));
		return new ResponseEntity<Organization>(organization,organization.getHttpStatus());
		
	}
	@RequestMapping(value="/user/byName/{name}",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<User> getUserByName(@PathVariable String name)
	{
		User user=new User();
		user=systemFind.getUserByName(name);

		if(user.getError()==null)
		{
			addAllLink(user);
		}
		user.add(linkTo(methodOn(SystemController.class).create(user)).withRel("create"));
		return new ResponseEntity<User>(user,user.getHttpStatus());
		
	}
	
	@RequestMapping(value="/project/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public  HttpEntity<Project> delete(@PathVariable String id)
	{
		Project project=new Project();
		project=systemFind.deleteProjectById(id);

		if(project.getError()==null)
		{
			addAllLink(project, project.getId(), project.getName());
		}
		project.add(linkTo(methodOn(SystemController.class).create(new ProjectAndOrganizationVO())).withRel("create"));
		return new ResponseEntity<Project>(project,project.getHttpStatus());
		
	}
	@RequestMapping(value="/organization/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public  HttpEntity<Organization> deleteO(@PathVariable String id)
	{
		Organization organization=new Organization();
		organization=systemFind.deleteOrganizationById(id);

		if(organization.getError()==null)
		{
			addAllLink(organization, organization.getId(), organization.getName());
		}
		organization.add(linkTo(methodOn(SystemController.class).createO(null)).withRel("create"));
		return new ResponseEntity<Organization>(organization,organization.getHttpStatus());
		
	}
	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public  HttpEntity<User> deleteUser(@PathVariable String id)
	{
		User user=new User();
		user=systemFind.deleteUserById(id);

		if(user.getError()==null)
		{
			addAllLink(user);
		}
		user.add(linkTo(methodOn(SystemController.class).create(user)).withRel("create"));
		return new ResponseEntity<User>(user,user.getHttpStatus());
		
	}
	@RequestMapping(value="/project/byName/{name}",method=RequestMethod.DELETE)
	@ResponseBody
	public  HttpEntity<Project> deleteByName(@PathVariable String name)
	{
		Project project=new Project();
		project=systemFind.deleteProjectByName(name);

		if(project.getError()==null)
		{
			addAllLink(project, project.getId(), project.getName());
		}
		project.add(linkTo(methodOn(SystemController.class).create(new ProjectAndOrganizationVO())).withRel("create"));
		return new ResponseEntity<Project>(project,project.getHttpStatus());
		
	}
	@RequestMapping(value="/organization/byName/{name}",method=RequestMethod.DELETE)
	@ResponseBody
	public  HttpEntity<Organization> deleteByNameO(@PathVariable String name)
	{
		Organization organization=new Organization();
		organization=systemFind.deleteOrganizationByName(name);

		if(organization.getError()==null)
		{
			addAllLink(organization, organization.getId(), organization.getName());
		}
		organization.add(linkTo(methodOn(SystemController.class).createO(null)).withRel("create"));
		return new ResponseEntity<Organization>(organization,organization.getHttpStatus());
		
	}
	@RequestMapping(value="/user/byName/{name}",method=RequestMethod.DELETE)
	@ResponseBody
	public  HttpEntity<User> deleteUserByname(@PathVariable String  name)
	{
		User user=new User();
		user=systemFind.deleteUserByName(name);

		if(user.getError()==null)
		{
			addAllLink(user);
		}
		user.add(linkTo(methodOn(SystemController.class).create(user)).withRel("create"));
		return new ResponseEntity<User>(user,user.getHttpStatus());
		
	}
	
	@RequestMapping(value="/project/list",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<ProjectList> getProjectList()
	{
		ProjectList projectList=new ProjectList();
		projectList=systemFind.getProjectList();
		
		setProjectListLink(projectList);

		projectList.add(linkTo(methodOn(SystemController.class).getProjectList()).withSelfRel());
		return new ResponseEntity<ProjectList>(projectList,projectList.getHttpStatus());
		
	}
	@RequestMapping(value="/organization/list",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<OrganizationList> getOrganizationList()
	{
		OrganizationList organizationList=new OrganizationList();
		organizationList=systemFind.getOrganizationList();
		
		setOrganizationListLink(organizationList);

		organizationList.add(linkTo(methodOn(SystemController.class).getProjectList()).withSelfRel());
		return new ResponseEntity<OrganizationList>(organizationList,organizationList.getHttpStatus());
		
	}
	
	
	@RequestMapping(value="/project/list/{name}",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<ProjectList> getProjectList(@PathVariable String name)
	{
		ProjectList projectList=new ProjectList();
		projectList=systemFind.getProjectistByOrganizationName(name);

		if(projectList.getError()==null)
		{
			projectList.add(linkTo(methodOn(SystemController.class).getProjectList(name)).withSelfRel());
			setProjectListLink(projectList);
		}
		return new ResponseEntity<ProjectList>(projectList,projectList.getHttpStatus());
		
	}
	
	private void addAllLink(Project project,String id,String name)
	{
		project.add(linkTo(methodOn(SystemController.class).getById(id)).withSelfRel());
		project.add(linkTo(methodOn(SystemController.class).getByName(name)).withRel("getByName"));
		project.add(linkTo(methodOn(SystemController.class).delete(id)).withRel("delete"));
		project.add(linkTo(methodOn(SystemController.class).deleteByName(name)).withRel("deleteByName"));
		project.add(linkTo(methodOn(SystemController.class).update(new ProjectAndOrganizationVO(), id)).withRel("update"));
			
	}
	private void addAllLink(Organization organization,String id,String name)
	{
		organization.add(linkTo(methodOn(SystemController.class).getByIdO(id)).withSelfRel());
		organization.add(linkTo(methodOn(SystemController.class).getByNameO(name)).withRel("getByName"));
		organization.add(linkTo(methodOn(SystemController.class).deleteO(id)).withRel("delete"));
		organization.add(linkTo(methodOn(SystemController.class).deleteByNameO(name)).withRel("deleteByName"));
		organization.add(linkTo(methodOn(SystemController.class).updateO(null, id)).withRel("update"));
			
	}
	private void addAllLink(User user)
	{
		user.add(linkTo(methodOn(SystemController.class).getUserById(user.getId())).withSelfRel());
		user.add(linkTo(methodOn(SystemController.class).deleteUserByname(user.getName())).withRel("delete"));
		user.add(linkTo(methodOn(SystemController.class).update(user, user.getId())).withRel("update"));
			
	}
	private void setOrganizationListLink(OrganizationList organizationList) {
		for (Organization organization :organizationList.getList()) {
			organization.add(linkTo(methodOn(SystemController.class).getById(organization.getId())).withSelfRel());
			organization.add(linkTo(methodOn(SystemController.class).update(new ProjectAndOrganizationVO(),organization.getId())).withRel("update"));
			organization.add(linkTo(methodOn(SystemController.class).delete( organization.getId())).withRel("delete"));			
		}
		
	}
	private void setProjectListLink(ProjectList projectList)
	{
		for (Project project :projectList.getList()) {
			project.add(linkTo(methodOn(SystemController.class).getById(project.getId())).withSelfRel());
			project.add(linkTo(methodOn(SystemController.class).update(  new ProjectAndOrganizationVO(),project.getId())).withRel("update"));
			project.add(linkTo(methodOn(SystemController.class).delete( project.getId())).withRel("delete"));			
		}

	}
	

}
