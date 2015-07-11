package cn.edu.buaa.g305.qpm.mc.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.buaa.g305.qpm.mc.domain.MC;
import cn.edu.buaa.g305.qpm.mc.server.MCServer;

@Controller
@RequestMapping("/api/mc")
public class MCController {
	
	@Autowired
	private MCServer mcServer;
	//创建mc
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public MC create(@RequestBody MC mc)
	{
		return mcServer.save(mc);			
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	@ResponseBody
	public MC update(@RequestBody MC mc,@PathVariable String id)
	{
		return mcServer.update(mc, id);			
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public MC get(@PathVariable String id)
	{
		return mcServer.getById(id);			
	}
	
	@RequestMapping(value="/byName/{name}",method=RequestMethod.GET)
	@ResponseBody
	public MC getByName(@PathVariable String name)
	{
		return mcServer.getByName(name);			
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public MC detete(@PathVariable String id)
	{
		return mcServer.delete(id);			
	}
	
	@RequestMapping(value="/byName/{name}",method=RequestMethod.DELETE)
	@ResponseBody
	public MC deleteByName(@PathVariable String name)
	{
		return mcServer.deleteByName(name);			
	}
	
	@RequestMapping(value="/list/{name}",method=RequestMethod.GET)
	@ResponseBody
	public List<MC> getListByProjectName(@PathVariable String name)
	{
		return mcServer.getListByProject(name);		
	}

	

}
