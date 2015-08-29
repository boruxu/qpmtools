package cn.edu.buaa.g305.qpm.processManagement.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.edu.buaa.g305.qpm.processManagement.domian.Product;
import cn.edu.buaa.g305.qpm.processManagement.server.ProductServer;
import cn.edu.buaa.g305.qpm.system.controller.BaseController;

@Controller
@RequestMapping("/api/pm/product")
public class ProductController extends BaseController{
	
	@Autowired
	private ProductServer productServer;
	@Autowired  
	private HttpServletRequest request; 

	private String realpath(){
		return request.getSession()
				.getServletContext().getRealPath("../qpmtoolsUpload/product");
	}
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public Product create(@RequestParam("product") String  productString,
			@RequestPart(value="file-data",required=false)  MultipartFile file) 
					throws ServletRequestBindingException, IOException
	{
		logger.log(Level.INFO, "- - -Product create API /api/pm/product");	
		Product product;
		try {
			product = new ObjectMapper().readValue(productString, Product.class);
		} catch (IOException e) {			
			throw new ServletRequestBindingException(productString+" binging error!");
		}
		
		if(product.getName()==null||product.getDescription()==null)
		{
			throw new ServletRequestBindingException("Param product "+productString+" has not enough values!");
		}
		if(file!=null)
		{
			logger.log(Level.INFO, "- - -Product create has file");
			product.setFileName(file.getOriginalFilename());
			product=productServer.save(product);
			logger.log(Level.INFO, "- - -Product create file name "+file.getOriginalFilename());
			

			//使用mongo生成的id作为文件的唯一id，原有名字存储在product.fileName
			File target = new File(realpath(), product.getId());
			FileUtils.copyInputStreamToFile(file.getInputStream(), target);
           
			return product;	
		}
		else {
			logger.log(Level.INFO, "- - -Product create file is null");
			return productServer.save(product);	
		}
			
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	@ResponseBody
	public Product update(@RequestParam("product") String  productString,
			@PathVariable String id,
			@RequestPart(value="file-data",required=false)  MultipartFile file) throws ServletRequestBindingException, IOException, NoHandlerFoundException 
	{
		logger.log(Level.INFO, "- - -Product update API /api/pm/product/{id}");	
		Product product;
		
		//得到上传目录,使用和项目同级的目录，程序和数据分离，也保证数据不会被重新部署刷掉
		
		try {
			product = new ObjectMapper().readValue(productString, Product.class);
		} catch (IOException e) {			
			throw new ServletRequestBindingException(productString+" binging error!");
		}
		
		if(product.getName()==null||product.getDescription()==null)
		{
			throw new ServletRequestBindingException("Param product "+productString+" has not enough values!");
		}
		if(file!=null)
		{
			logger.log(Level.INFO, "- - -Product update has file");
			product.setFileName(file.getOriginalFilename());
			product=productServer.update(product,id);
			logger.log(Level.INFO, "- - -Product update file name "+file.getOriginalFilename());
			
			//使用mongo生成的id作为文件的唯一id，原有名字存储在product.fileName
			File target = new File(realpath(), product.getId());
			if(target.exists()==false)
			{
				if(target.createNewFile())
				{
					FileUtils.copyInputStreamToFile(file.getInputStream(), target);
				}
				else{
					throw new IOException("For some system reasons "+product.getId()+" file can not be update!");
				}
			}
			else{
				if(target.delete()==true&&target.createNewFile()==true)
				{
					FileUtils.copyInputStreamToFile(file.getInputStream(), target);
				}
				else{
					throw new IOException("For some system reasons "+product.getId()+" file can not be update!");
				}
			}			
			return product;	
		}
		else {
			logger.log(Level.INFO, "- - -Product update file is null");
			//只删除文件的话，product更新是只要置fileName为null就行
			if(product.getFileName()==null)
			{
				deleteFile(product.getId());
				
			}			
			return productServer.update(product, id);	
		}
		
	}
	
	@RequestMapping(value="/file/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getFile(@PathVariable String id) throws IOException
	{
		
	    File file=new File(realpath(),id);  
	    HttpHeaders headers = new HttpHeaders();    
	    String fileName=productServer.getById(id).getFileName();
	    //解决中文不能显示问题
	    headers.setContentDispositionFormData("attachment", 
	    		new String(fileName.getBytes("utf-8"), "ISO8859-1"));
	    //headers.setContentType(MediaType.);   
	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
	                                          headers, HttpStatus.OK);   		
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
	public String detete(@PathVariable String id) throws IOException
	{
        if(productServer.getById(id)!=null&&
        		productServer.getById(id).getFileName()!=null)
        {
        	deleteFile(id);
        }
		productServer.delete(id);
					
		return "success";
	}
	
	@RequestMapping(value="/byName/{name}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteByName(@PathVariable String name) throws IOException
	{

		
		if(productServer.getByName(name)!=null&&
				productServer.getByName(name).getFileName()!=null)
        {
        	deleteFile(productServer.getByName(name).getId());
        }
		productServer.deleteByName(name);
			
		return "success";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public List<Product> getListByProjectName()
	{
		return productServer.getAllList();		
	}
	
	private void deleteFile(String id) throws IOException
	{
		File target = new File(realpath(), id);
		if(target.delete()!=true)
		{
			throw new IOException("For some system reasons "+id+" file can not be delete!");
		}
	}
	
	

}
