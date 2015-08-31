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

import cn.edu.buaa.g305.qpm.processManagement.domian.Activity;
import cn.edu.buaa.g305.qpm.processManagement.server.ActivitytServer;
import cn.edu.buaa.g305.qpm.system.controller.BaseController;

@Controller
@RequestMapping("/api/pm/activity")
public class ActivityController extends BaseController{
	
	@Autowired
	private ActivitytServer activityServer;
	@Autowired  
	private HttpServletRequest request; 
	
	private String realpath(){
		return request.getSession()
				.getServletContext().getRealPath("../qpmtoolsUpload/activity");
	}
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public Activity create(@RequestParam("activity") String  activityString,
			@RequestPart(value="file-data",required=false)  MultipartFile file) 
					throws ServletRequestBindingException, IOException
	{
		logger.log(Level.INFO, "- - -Activity create API /api/pm/Activity");	
		Activity activity;
		try {
			activity = new ObjectMapper().readValue(activityString, Activity.class);
		} catch (IOException e) {			
			throw new ServletRequestBindingException(activityString+" binging error!");
		}
		
		if(activity.getName()==null||activity.getDescription()==null)
		{
			throw new ServletRequestBindingException("Param Activity "+
		activityString+" has not enough values!");
		}
		
		//处理activity本身的文件
		if(file!=null)
		{
			logger.log(Level.INFO, "- - -Activity create has file");
			activity.setFileName(file.getOriginalFilename());
			activity=activityServer.save(activity);
			logger.log(Level.INFO, "- - -Activity create file name "+file.getOriginalFilename());		
			//使用mongo生成的id作为文件的唯一id，原有名字存储在Activity.fileName
			File target = new File(realpath(), activity.getId());
			FileUtils.copyInputStreamToFile(file.getInputStream(), target);			
		}
		else {
			logger.log(Level.INFO, "- - -Activity create file is null");
			activity=activityServer.save(activity);	
		}		
		return activity;
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	@ResponseBody
	public Activity update(@RequestParam("activity") String  activityString,
			@PathVariable String id,
			@RequestPart(value="file-data",required=false)  MultipartFile file) 
					throws ServletRequestBindingException, IOException
					, NoHandlerFoundException 
	{
		logger.log(Level.INFO, "- - -Activity update API /api/pm/activity/{id}");	
		Activity activity;
		
		//得到上传目录,使用和项目同级的目录，程序和数据分离，也保证数据不会被重新部署刷掉
		
		try {
			activity = new ObjectMapper().readValue(activityString, Activity.class);
		} catch (IOException e) {			
			throw new ServletRequestBindingException(activityString+" binging error!");
		}
		
		if(activity.getName()==null||activity.getDescription()==null)
		{
			throw new ServletRequestBindingException("Param Activity "+
		activityString+" has not enough values!");
		}
		if(file!=null)
		{
			logger.log(Level.INFO, "- - -Activity update has file");
			activity.setFileName(file.getOriginalFilename());
			activity=activityServer.update(activity,id);
			logger.log(Level.INFO, "- - -Activity update file name "
			+file.getOriginalFilename());
			
			//使用mongo生成的id作为文件的唯一id，原有名字存储在Activity.fileName
			File target = new File(realpath(), activity.getId());
			if(target.exists()==false)
			{
				if(target.createNewFile())
				{
					FileUtils.copyInputStreamToFile(file.getInputStream(), target);
				}
				else{
					throw new IOException("For some system reasons "+activity.getId()+" file can not be update!");
				}
			}
			else{
				if(target.delete()==true&&target.createNewFile()==true)
				{
					FileUtils.copyInputStreamToFile(file.getInputStream(), target);
				}
				else{
					throw new IOException("For some system reasons "+activity.getId()+" file can not be update!");
				}
			}			
			return activity;	
		}
		else {
			logger.log(Level.INFO, "- - -Activity update file is null");
			//只删除文件的话，Activity更新是只要置fileName为null就行
			if(activity.getFileName()==null)
			{
				deleteFile(activity.getId());
				
			}			
			return activityServer.update(activity, id);	
		}
		
	}
	
	@RequestMapping(value="/file/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getFile(@PathVariable String id) throws IOException
	{
		
	    File file=new File(realpath(),id);  
	    HttpHeaders headers = new HttpHeaders();    
	    String fileName=activityServer.getById(id).getFileName();
	    //解决中文不能显示问题
	    headers.setContentDispositionFormData("attachment", 
	    		new String(fileName.getBytes("utf-8"), "ISO8859-1"));
	    //headers.setContentType(MediaType.);   
	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
	                                          headers, HttpStatus.OK);   		
	}

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Activity get(@PathVariable String id)
	{
		return activityServer.getById(id);			
	}
	
	@RequestMapping(value="/byName/{name}",method=RequestMethod.GET)
	@ResponseBody
	public Activity getByName(@PathVariable String name)
	{
		return activityServer.getByName(name);			
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> detete(@PathVariable String id) throws IOException
	{
        if(activityServer.getById(id)!=null&&
        		activityServer.getById(id).getFileName()!=null)
        {
        	deleteFile(id);
        }
		activityServer.delete(id);
					
		return new ResponseEntity<String>("sucess", HttpStatus.OK);
	}
	
	@RequestMapping(value="/byName/{name}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> deleteByName(@PathVariable String name) throws IOException
	{

		
		if(activityServer.getByName(name)!=null&&
				activityServer.getByName(name).getFileName()!=null)
        {
        	deleteFile(activityServer.getByName(name).getId());
        }
		activityServer.deleteByName(name);
			
		return new ResponseEntity<String>("sucess", HttpStatus.OK);
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public List<Activity> getListByProjectName()
	{
		return activityServer.getAllList();		
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
