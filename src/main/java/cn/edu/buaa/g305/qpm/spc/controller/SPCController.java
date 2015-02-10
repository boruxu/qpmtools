package cn.edu.buaa.g305.qpm.spc.controller;

import java.awt.List;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import cn.edu.buaa.g305.qpm.spc.domain.PlotList;
import cn.edu.buaa.g305.qpm.spc.domain.Spc;
import cn.edu.buaa.g305.qpm.spc.domain.SpcXR;
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
	@RequestMapping(value="/{plotType}",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<? extends Spc> create(@PathVariable String plotType,@RequestBody Spc spc)
	{
		SpcXR spcXRt=null;//spcService.save(spc);
		if(spcXRt.getError()==null)
		{
			//spcXRt.add(linkTo(methodOn(SPCController.class).getXRByID(spcXRt.getName())).withSelfRel());
			return new ResponseEntity<SpcXR>(spcXRt,HttpStatus.CREATED);
		}	
		else {
			
			return new ResponseEntity<SpcXR>(spcXRt,HttpStatus.BAD_REQUEST);
		}	
		 
	}
	/*
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
	*/
	@RequestMapping(value="/{plotType}/byId/{id}",method=RequestMethod.GET)
	@ResponseBody
	public HttpEntity<Spc> getByID(@PathVariable(value="plotType") String plotType,
			@PathVariable(value="id") String id)
	{ 
		Spc spc=new Spc();
		switch(plotType)
		{
		    
			case("xrplot"):
				{
					spc=spcService.getById(id);
			        break;       				
				}
			default:
			{
				spc.setError("无"+plotType+"类型SPC控制图");
				spc.setHttpStatus(HttpStatus.NOT_FOUND);
				spc.add(linkTo(methodOn(SPCController.class).getPlotList())
					.withRel("plotList"));
				return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
			}			
		}
		spc.add(linkTo(methodOn(SPCController.class).create(plotType, spc))
		.withRel(plotType+"/create.post"));
		spc.add(linkTo(methodOn(SPCController.class).getDataFormat(plotType))
					.withRel(plotType+"/dataformat"));
		if(spc.getHttpStatus()==HttpStatus.NOT_FOUND)
		{
			return new ResponseEntity<Spc>(spc,HttpStatus.NOT_FOUND);
		}
		else {
			spc.add(linkTo(methodOn(SPCController.class).getByID(plotType, id))
					.withSelfRel());
			return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
		}

	}
	
	//spc所有类型byName查询
	@RequestMapping(value="/{plotType}/byName/{name}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Spc> getByName(@PathVariable(value="plotType") 
	String plotType,@PathVariable(value="name") String name) 
	{
		Spc spc=new Spc();
		switch(plotType)
		{
		    
			case("xrplot"):
				{
					spc=spcService.getSpcxrByName(name);
			        break;       				
				}
			default:
			{
				spc.setError("无"+plotType+"类型SPC控制图");
				spc.setHttpStatus(HttpStatus.NOT_FOUND);
				spc.add(linkTo(methodOn(SPCController.class).getPlotList())
					.withRel("plotList"));
				return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
			}			
		}
		spc.add(linkTo(methodOn(SPCController.class).create(plotType, spc))
		.withRel(plotType+"/create.post"));
		spc.add(linkTo(methodOn(SPCController.class).getDataFormat(plotType))
					.withRel(plotType+"/dataFormat"));
		if(spc.getHttpStatus()==HttpStatus.NOT_FOUND)
		{
			return new ResponseEntity<Spc>(spc,HttpStatus.NOT_FOUND);
		}
		else {
			spc.add(linkTo(methodOn(SPCController.class).getByName(plotType,name))
					.withSelfRel());
			return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
		}
	}
	
	//以下为帮助信息，不使用超媒体
	@RequestMapping(value="/{plotType}/dataFormat",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String,String>> getDataFormat(@PathVariable(value="plotType") 
	 					String plotType) 
	{
		Map<String, String> map=new HashMap<String, String>();
		switch(plotType)
		{
			case("xrplot"):
			{	
				map.put("format",SpcXR.format());
				return new ResponseEntity<Map<String,String>>
				(map,HttpStatus.OK); 
			}
			default:
			{
				map.put("format",plotType+"类型不存在");
				return new ResponseEntity<Map<String,String>>
				(map,HttpStatus.NOT_FOUND); 
			}
			
		}
		
	}
	
	//支持spc视图数组列表
	@RequestMapping(value="/plotList",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<PlotList[]> getPlotList()
	{
		PlotList[] lists=PlotList.values();
		return new ResponseEntity<PlotList[]>(lists,HttpStatus.NOT_FOUND);	
	}

}
