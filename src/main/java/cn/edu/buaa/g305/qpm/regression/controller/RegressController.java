package cn.edu.buaa.g305.qpm.regression.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.buaa.g305.qpm.regression.domain.Regress;
import cn.edu.buaa.g305.qpm.regression.server.RegressServer;
import cn.edu.buaa.g305.qpm.system.domain.Project;

@Controller
@RequestMapping("/api/regress")
public class RegressController {
	
	@Autowired
	private RegressServer regressServer;
	//创建regress
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	@ExceptionHandler
	public Regress create(@RequestBody RegressVO regressVO)
	{
		Regress regress=new Regress();
		regress.setInput(regressVO.getInput());
		regress.setName(regressVO.getName());
		Project project=new Project();
		project.setName(regressVO.getProject());
		regress.setProject(project);
		return regressServer.save(regress);			
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	@ResponseBody
	public Regress update(@RequestBody RegressVO regressVO,@PathVariable String id)
	{
		Regress regress=new Regress();
		regress.setInput(regressVO.getInput());
		regress.setName(regressVO.getName());
		Project project=new Project();
		project.setName(regressVO.getProject());
		regress.setProject(project);
		return regressServer.update(regress, id);			
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Regress get(@PathVariable String id)
	{
		return regressServer.getById(id);			
	}
	
	@RequestMapping(value="/byName/{name}",method=RequestMethod.GET)
	@ResponseBody
	public Regress getByName(@PathVariable String name)
	{
		return regressServer.getByName(name);			
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Regress detete(@PathVariable String id)
	{
		return regressServer.delete(id);			
	}
	
	@RequestMapping(value="/byName/{name}",method=RequestMethod.DELETE)
	@ResponseBody
	public Regress deleteByName(@PathVariable String name)
	{
		return regressServer.deleteByName(name);			
	}
	
	@RequestMapping(value="/list/{name}",method=RequestMethod.GET)
	@ResponseBody
	public List<Regress> getListByProjectName(@PathVariable String name)
	{
		return regressServer.getListByProject(name);		
	}

}
