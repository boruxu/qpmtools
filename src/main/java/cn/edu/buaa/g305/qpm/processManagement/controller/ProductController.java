package cn.edu.buaa.g305.qpm.processManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.edu.buaa.g305.qpm.processManagement.domian.Product;
import cn.edu.buaa.g305.qpm.processManagement.server.ProductServer;

@Controller
@RequestMapping("/api/pm/product")
public class ProductController {
	
	@Autowired
	private ProductServer productServer;
	//创建Product
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public Product create(@RequestBody Product Product)
	{
		return productServer.save(Product);			
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	@ResponseBody
	public Product update(@RequestBody Product Product,@PathVariable String id)
	{
		return productServer.update(Product, id);			
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Product get(@PathVariable String id)
	{
		return productServer.getById(id);			
	}
	
	@RequestMapping(value="/byName/{name}",method=RequestMethod.GET)
	@ResponseBody
	public Product getByName(@PathVariable String name)
	{
		return productServer.getByName(name);			
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Product detete(@PathVariable String id)
	{
		try {
			productServer.delete(id);
		} catch (Exception e) {
			return new Product(e.getMessage());
		}
					
		return null;
	}
	
	@RequestMapping(value="/byName/{name}",method=RequestMethod.DELETE)
	@ResponseBody
	public Product deleteByName(@PathVariable String name)
	{
		
		try {
			productServer.deleteByName(name);
		} catch (Exception e) {
			return new Product(e.getMessage());
		}
					
		return null;
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public List<Product> getListByProjectName()
	{
		return productServer.getAllList();		
	}
	
	

}
