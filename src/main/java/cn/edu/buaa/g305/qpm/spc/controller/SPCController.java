package cn.edu.buaa.g305.qpm.spc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import cn.edu.buaa.g305.qpm.spc.domain.SpcXR;
import cn.edu.buaa.g305.qpm.spc.domain.spcExtend;
import cn.edu.buaa.g305.qpm.spc.server.SPCService;

@Controller
@RequestMapping("/spc")
public class SPCController {
	
	@Autowired
	private SPCService spcService;
	
	//spc主页
	@RequestMapping()
	public String spcHome()
	{
		return "spc.jsp";
	}
	@RequestMapping(value="/xrplot",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<SpcXR> createXR(@RequestBody SpcXR spcXR)
	{
		SpcXR spcXRt=spcService.save(spcXR);
		if(spcXRt.getError()==null)
		{
			spcXRt.add(linkTo(methodOn(SPCController.class).getXRByID(spcXRt.getName())).withSelfRel());
			return new ResponseEntity<SpcXR>(spcXRt,HttpStatus.CREATED);
		}	
		else {
			spcExtend spcExtend=new spcExtend();
			return new ResponseEntity<SpcXR>(spcExtend,HttpStatus.BAD_REQUEST);
		}	
		 
	}
	
	@RequestMapping(value="/xrplot/{id}",method=RequestMethod.PATCH)
	@ResponseBody
	public  HttpEntity<SpcXR> updateXR(@RequestBody SpcXR spcXR,@PathVariable String id)
	{
		SpcXR spcXRt=spcService.update(spcXR, id);
		if(spcXRt.getError()==null)
		{
			spcXRt.add(linkTo(methodOn(SPCController.class).updateXR(spcXRt, id)).withRel("update"));
			spcXRt.add(linkTo(methodOn(SPCController.class).getXRByID(id)).withSelfRel());
			return new ResponseEntity<SpcXR>(spcXRt,HttpStatus.OK);			
		}
		else {
			spcXRt.add(linkTo(methodOn(SPCController.class).createXR(null))
					.withRel("xrplot/create.post"));
			//找不到资源，设置错误信息和状态码
			return new ResponseEntity<SpcXR>(spcXRt,HttpStatus.NOT_FOUND);	
		}
	}
	
	@RequestMapping(value="/xrplot/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public  HttpEntity<SpcXR> deleteXR(@PathVariable String id)
	{ 
		SpcXR spcXR=spcService.delete(id);
		if(spcXR.getError()==null)
		{
			spcXR.add(linkTo(methodOn(SPCController.class).getXRByID(id)).withSelfRel());
			spcXR=null;
			return new ResponseEntity<SpcXR>(spcXR,HttpStatus.OK);			
		}
		else {
			spcXR.add(linkTo(methodOn(SPCController.class).createXR(null))
					.withRel("xrplot/create.post"));
			//找不到资源，设置错误信息和状态码
			return new ResponseEntity<SpcXR>(spcXR,HttpStatus.NOT_FOUND);	
		}
	}
	
	@RequestMapping(value="/xrplot/{id}",method=RequestMethod.GET)
	@ResponseBody
	public HttpEntity<SpcXR> getXRByID(@PathVariable String id)
	{ 
		SpcXR spcXR=spcService.getById(id);
		if(spcXR.getError()==null)
		{
			spcXR.add(linkTo(methodOn(SPCController.class).getXRByID(id)).withSelfRel());
			return new ResponseEntity<SpcXR>(spcXR,HttpStatus.OK);			
		}
		else {
			spcXR.add(linkTo(methodOn(SPCController.class).createXR(null))
					.withRel("xrplot/create.post"));
			//找不到资源，设置错误信息和状态码
			return new ResponseEntity<SpcXR>(spcXR,HttpStatus.NOT_FOUND);	
		}

	}
	
	@RequestMapping(value="/xrplot/byName/{name}",method=RequestMethod.GET)
	@ResponseBody
	public HttpEntity<SpcXR> getXRByName(@PathVariable String name)
	{
		SpcXR spcXR=spcService.getSpcxrByName(name);
		if(spcXR.getError()==null)
		{
			spcXR.add(linkTo(methodOn(SPCController.class).getXRByName(name)).withSelfRel());
			return new ResponseEntity<SpcXR>(spcXR,HttpStatus.OK);
		}
		else {
			spcXR.add(linkTo(methodOn(SPCController.class).createXR(null))
					.withRel("create"));
			//找不到资源，设置错误信息和状态码
			return new ResponseEntity<SpcXR>(spcXR,HttpStatus.NOT_FOUND);
		}
	}

}
