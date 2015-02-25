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

import cn.edu.buaa.g305.qpm.spc.controller.SPCController;
import cn.edu.buaa.g305.qpm.spc.domain.Spc;
import cn.edu.buaa.g305.qpm.spc.domain.SpcList;
import cn.edu.buaa.g305.qpm.system.domain.Project;
import cn.edu.buaa.g305.qpm.system.domain.ProjectList;
import cn.edu.buaa.g305.qpm.system.server.SystemFind;

@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private SystemFind systemFind;
	

	@RequestMapping(value="/project",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<Project> create(@RequestBody ProjectVO projectVO)
	{
		Project project=new Project();
		project.setName(projectVO.getName());
		project.setDescription(projectVO.getDescription());
		project=systemFind.save(project, projectVO.getOrganization());
		if(project.getError()==null)
		{
			addAllLink(project, project.getId(), project.getName());
		}
		
		return new ResponseEntity<Project>(project,project.getHttpStatus());
		
	}
	@RequestMapping(value="/project/{id}",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<Project> update(@RequestBody ProjectVO projectVO
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
		return new ResponseEntity<Project>(project,project.getHttpStatus());
		
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
		return new ResponseEntity<Project>(project,project.getHttpStatus());
		
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
		return new ResponseEntity<Project>(project,project.getHttpStatus());
		
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
		return new ResponseEntity<Project>(project,project.getHttpStatus());
		
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
		return new ResponseEntity<Project>(project,project.getHttpStatus());
		
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
		project.add(linkTo(methodOn(SystemController.class).update(null, id)).withRel("update"));
			
	}
	private void setProjectListLink(ProjectList projectList)
	{
		for (Project project :projectList.getList()) {
			project.add(linkTo(methodOn(SystemController.class).getById(project.getId())).withSelfRel());
			project.add(linkTo(methodOn(SystemController.class).update(  null,project.getId())).withRel("update"));
			project.add(linkTo(methodOn(SystemController.class).delete( project.getId())).withRel("delete"));			
		}

	}
	

}
