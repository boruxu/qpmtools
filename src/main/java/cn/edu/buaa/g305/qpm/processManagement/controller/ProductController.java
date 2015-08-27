package cn.edu.buaa.g305.qpm.processManagement.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.edu.buaa.g305.qpm.processManagement.domian.Product;
import cn.edu.buaa.g305.qpm.processManagement.server.ProductServer;
import cn.edu.buaa.g305.qpm.system.controller.BaseController;

@Controller
@RequestMapping("/api/pm/product")
public class ProductController extends BaseController{
	
	@Autowired
	private ProductServer productServer;
	//创建Product

	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public Product create(@RequestParam("product") String  productString,
			@RequestPart(value="file-data",required=false)  MultipartFile file)
	{
		logger.log(Level.INFO, "Product API /api/pm/product");
		if(file!=null)
		{
			if(!file.isEmpty())
			{
				byte[] b;
				try {
					b = new byte[file.getInputStream().read()];
					file.getInputStream().read(b);
					System.out.write(b,0,b.length);
					Product product = new ObjectMapper().readValue(productString, Product.class);
					if(product==null)
					{
						System.out.println("product is null");
						
					}
					logger.log(Level.INFO, "Product 创建"+product.toString());
	
					return productServer.save(product);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			
		}
		else {
			Product product;
			try {
				product = new ObjectMapper().readValue(productString, Product.class);
				logger.log(Level.INFO, "Product创建"+product.toString());
				return productServer.save(product);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;		
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
