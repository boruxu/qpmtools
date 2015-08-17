package cn.edu.buaa.g305.qpm.processManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.buaa.g305.qpm.processManagement.domian.Tag;
import cn.edu.buaa.g305.qpm.processManagement.server.TagServer;


@Controller
@RequestMapping("/api/pm/tag")
public class PMTagController {
	
	@Autowired
	private TagServer tagServer;
	//创建tag
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public Tag create(@RequestBody Tag tag)
	{
		return tagServer.save(tag);			
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	@ResponseBody
	public Tag update(@RequestBody Tag tag,@PathVariable String id)
	{
		return tagServer.update(tag, id);			
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Tag get(@PathVariable String id)
	{
		return tagServer.getById(id);			
	}
	
	@RequestMapping(value="/byName/{name}",method=RequestMethod.GET)
	@ResponseBody
	public Tag getByName(@PathVariable String name)
	{
		return tagServer.getByName(name);			
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Tag detete(@PathVariable String id)
	{
		try {
			tagServer.delete(id);
		} catch (Exception e) {
			return new Tag(e.getMessage());
		}
					
		return null;
	}
	
	@RequestMapping(value="/byName/{name}",method=RequestMethod.DELETE)
	@ResponseBody
	public Tag deleteByName(@PathVariable String name)
	{
		
		try {
			tagServer.deleteByName(name);
		} catch (Exception e) {
			return new Tag(e.getMessage());
		}
					
		return null;
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public List<Tag> getListByProjectName()
	{
		return tagServer.getAllList();		
	}
	
	

}
