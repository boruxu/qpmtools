package cn.edu.buaa.g305.qpm.spc.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.SpcXR;
import cn.edu.buaa.g305.qpm.spc.domain.spcxs.SpcXS;
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
	public  HttpEntity<Spc> create(@PathVariable String plotType,@RequestBody SpcVO spcVO)
	{
		Spc spc=new Spc();
		String id=null;
		String name=null;
		switch(plotType)
		{
		    
			case("xrplot"):
				{
					SpcXR spcXR=new SpcXR();
					spcXR.setName(spcVO.getName());
					spcXR.setInput(spcVO.getInputXR());
					spcXR=spcService.save(spcXR,spcVO.getProject());
					id=spcXR.getId();
					name=spcXR.getName();
					spc=spcXR;
			        break;       				
				}
			case("xsplot"):
			{
				SpcXS spcXS=new SpcXS();
				spcXS.setName(spcVO.getName());
				spcXS.setInput(spcVO.getInputXS());
				spcXS=spcService.save(spcXS,spcVO.getProject());
				id=spcXS.getId();
				name=spcXS.getName();
				spc=spcXS;
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
		
		if(spc.getError()==null)
		{
			spc.add(linkTo(methodOn(SPCController.class).getByID(plotType,id)).withSelfRel());
			spc.add(linkTo(methodOn(SPCController.class).getByName(plotType,name)).withRel("getByName"));
			spc.add(linkTo(methodOn(SPCController.class).delete(plotType, id)).withRel("delete"));
			spc.add(linkTo(methodOn(SPCController.class).deleteByName(plotType, name)).withRel("deleteByName"));
			spc.add(linkTo(methodOn(SPCController.class).updateXR(plotType, id, null)).withRel("update.patch"));
			return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
		}	
		else {
			spc.add(linkTo(methodOn(SPCController.class).create(plotType, spcVO)
					).withRel(plotType+"/create.post"));
			spc.add(linkTo(methodOn(SPCController.class).getPlotList()
					).withRel("plotList"));
			spc.add(linkTo(methodOn(SPCController.class).getDataFormat(plotType)
					).withRel(plotType+"/dataformat"));
			return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
		}	
		 
	}
	
	@RequestMapping(value="/{plotType}/{id}",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<Spc> updateXR(@PathVariable String plotType,
			@PathVariable String id,@RequestBody SpcVO spcVO)
	{
		Spc spc=new Spc();
		String name=null;
		switch(plotType)
		{
		    
			case("xrplot"):
				{
					SpcXR spcXR=new SpcXR();
					spcXR.setName(spcVO.getName());
					spcXR.setInput(spcVO.getInputXR());
					spcXR=spcService.update(spcXR, id,spcVO.getProject());
					name=spcXR.getName();
					spc=spcXR;
			        break;       				
				}
			case("xsplot"):
			{
				SpcXS spcXS=new SpcXS();
				spcXS.setName(spcVO.getName());
				spcXS.setInput(spcVO.getInputXS());
				spcXS=spcService.update(spcXS, id,spcVO.getProject());
				name=spcXS.getName();
				spc=spcXS;
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
		
		if(spc.getError()==null)
		{
			spc.add(linkTo(methodOn(SPCController.class).getByID(plotType,id)).withSelfRel());
			spc.add(linkTo(methodOn(SPCController.class).getByName(plotType,name)).withRel("getByName"));
			spc.add(linkTo(methodOn(SPCController.class).delete(plotType, id)).withRel("delete"));
			spc.add(linkTo(methodOn(SPCController.class).deleteByName(plotType, name)).withRel("deleteByName"));
			spc.add(linkTo(methodOn(SPCController.class).updateXR(plotType, id, null)).withRel("update.patch"));
			return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
		}	
		else {
			spc.add(linkTo(methodOn(SPCController.class).create(plotType, spcVO)
					).withRel(plotType+"/create.post"));
			spc.add(linkTo(methodOn(SPCController.class).getPlotList()
					).withRel("plotList"));
			spc.add(linkTo(methodOn(SPCController.class).getDataFormat(plotType)
					).withRel(plotType+"/dataformat"));
			return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
		}	
	}
	
	@RequestMapping(value="/{plotType}/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public  HttpEntity<Spc> delete(@PathVariable(value="plotType") String plotType,
			@PathVariable(value="id") String id)
	{
		Spc spc=new Spc();
		switch(plotType)
		{
		    
			case("xrplot"):
				{
					spc=spcService.deleteXR(id);
			        break;       				
				}
			case("xsplot"):
			{
				spc=spcService.deleteXS(id);
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
		spc.add(linkTo(methodOn(SPCController.class).create(plotType, null))
					.withRel(plotType+"/create.post"));
		spc.add(linkTo(methodOn(SPCController.class).getDataFormat(plotType))
				.withRel(plotType+"/dataformat"));
		return new ResponseEntity<Spc>(spc,spc.getHttpStatus());			

	}
	
	@RequestMapping(value="/{plotType}/byName/{name}",method=RequestMethod.DELETE)
	@ResponseBody
	public  HttpEntity<Spc> deleteByName(@PathVariable(value="plotType") String plotType,
			@PathVariable(value="name") String name)
	{ 
		Spc spc=new Spc();
		switch(plotType)
		{
		    
			case("xrplot"):
				{
					spc=spcService.deleteXRByName(name);
			        break;       				
				}
			case("xsplot"):
			{
				spc=spcService.deleteXSByName(name);
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
		spc.add(linkTo(methodOn(SPCController.class).create(plotType, null))
					.withRel(plotType+"/create.post"));
		spc.add(linkTo(methodOn(SPCController.class).getDataFormat(plotType))
				.withRel(plotType+"/dataformat"));
		return new ResponseEntity<Spc>(spc,spc.getHttpStatus());			

	}
	
	@RequestMapping(value="/{plotType}/{id}",method=RequestMethod.GET)
	@ResponseBody
	public HttpEntity<Spc> getByID(@PathVariable(value="plotType") String plotType,
			@PathVariable(value="id") String id)
	{ 
		Spc spc=new Spc();
		switch(plotType)
		{
		    
			case("xrplot"):
				{
					spc=spcService.getXRById(id);
			        break;       				
				}
			case("xsplot"):
			{
				spc=spcService.getXSById(id);
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
		spc.add(linkTo(methodOn(SPCController.class).create(plotType, null))
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
	public HttpEntity<Spc> getByName(@PathVariable(value="plotType") 
	String plotType,@PathVariable(value="name") String name) 
	{
		Spc spc=new Spc();
		switch(plotType)
		{
		    
			case("xrplot"):
				{
					spc=spcService.getXRByName(name);
			        break;       				
				}
			case("xsplot"):
			{
				spc=spcService.getXSByName(name);
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
		spc.add(linkTo(methodOn(SPCController.class).create(plotType, null))
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
	@RequestMapping(value="/help/{plotType}/dataFormat",method=RequestMethod.GET)
	@ResponseBody
	public HttpEntity<Map<String,String>> getDataFormat(@PathVariable(value="plotType") 
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
			case("xsplot"):
			{	
				map.put("format",SpcXS.format());
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
	@RequestMapping(value="/help/plotList",method=RequestMethod.GET)
	@ResponseBody
	public HttpEntity<PlotList[]> getPlotList()
	{
		PlotList[] lists=PlotList.values();
		return new ResponseEntity<PlotList[]>(lists,HttpStatus.NOT_FOUND);	
	}

}
