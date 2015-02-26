package cn.edu.buaa.g305.qpm.spc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import cn.edu.buaa.g305.qpm.spc.domain.PlotList;
import cn.edu.buaa.g305.qpm.spc.domain.Spc;
import cn.edu.buaa.g305.qpm.spc.domain.SpcList;
import cn.edu.buaa.g305.qpm.spc.domain.spcc.SpcC;
import cn.edu.buaa.g305.qpm.spc.domain.spcu.SpcU;
import cn.edu.buaa.g305.qpm.spc.domain.spcxmr.SpcXMR;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.SpcXR;
import cn.edu.buaa.g305.qpm.spc.domain.spcxs.SpcXS;
import cn.edu.buaa.g305.qpm.spc.domain.spcz.SpcZ;
import cn.edu.buaa.g305.qpm.spc.server.SPCService;

@Controller
@RequestMapping("/api/spc")
public class SPCController {
	
	@Autowired
	private SPCService spcService;
	

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
			case("xmrplot"):
			{
				SpcXMR spcXMR=new SpcXMR();
				spcXMR.setName(spcVO.getName());
				spcXMR.setInput(spcVO.getInputXMR());
				spcXMR=spcService.save(spcXMR,spcVO.getProject());
				id=spcXMR.getId();
				name=spcXMR.getName();
				spc=spcXMR;
		        break;       				
			}
			case("cplot"):
			{
				SpcC spcC=new SpcC();
				spcC.setName(spcVO.getName());
				spcC.setInput(spcVO.getInputC());
				spcC=spcService.save(spcC,spcVO.getProject());
				id=spcC.getId();
				name=spcC.getName();
				spc=spcC;
		        break;       				
			}
			case("uplot"):
			{
				SpcU spcU=new SpcU();
				spcU.setName(spcVO.getName());
				spcU.setInput(spcVO.getInputU());
				spcU=spcService.save(spcU,spcVO.getProject());
				id=spcU.getId();
				name=spcU.getName();
				spc=spcU;
		        break;       				
			}
			case("zplot"):
			{
				SpcZ spcZ=new SpcZ();
				spcZ.setName(spcVO.getName());
				spcZ.setInput(spcVO.getInputZ());
				spcZ=spcService.save(spcZ,spcVO.getProject());
				id=spcZ.getId();
				name=spcZ.getName();
				spc=spcZ;
		        break;       				
			}
			
			default:
			{
				addNoPlotType(spc, plotType);
				return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
			}
		}
		
		if(spc.getError()==null)
		{
			addAllLinkExcludeHelp(spc, id, name, plotType);
			return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
		}	
		else {
			addCreateLandDataFormatL(spc,plotType);
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
			case("xmrplot"):
			{
				SpcXMR spcXMR=new SpcXMR();
				spcXMR.setName(spcVO.getName());
				spcXMR.setInput(spcVO.getInputXMR());
				spcXMR=spcService.update(spcXMR, id,spcVO.getProject());
				name=spcXMR.getName();
				spc=spcXMR;
		        break;       				
			}
			case("cplot"):
			{
				SpcC spcC=new SpcC();
				spcC.setName(spcVO.getName());
				spcC.setInput(spcVO.getInputC());
				spcC=spcService.update(spcC, id,spcVO.getProject());
				name=spcC.getName();
				spc=spcC;
		        break;       				
			}
			case("uplot"):
			{
				SpcU spcU=new SpcU();
				spcU.setName(spcVO.getName());
				spcU.setInput(spcVO.getInputU());
				spcU=spcService.update(spcU, id,spcVO.getProject());
				name=spcU.getName();
				spc=spcU;
		        break;       				
			}
			case("zplot"):
			{
				SpcZ spcZ=new SpcZ();
				spcZ.setName(spcVO.getName());
				spcZ.setInput(spcVO.getInputZ());
				spcZ=spcService.update(spcZ, id,spcVO.getProject());
				name=spcZ.getName();
				spc=spcZ;
		        break;       				
			}
			default:
			{
				addNoPlotType(spc, plotType);
				return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
			}
		}
		
		if(spc.getError()==null)
		{
			addAllLinkExcludeHelp(spc, id, name, plotType);
			return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
		}	
		else {
			addCreateLandDataFormatL(spc,plotType);
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
			case("xmrplot"):
			{
				spc=spcService.deleteXMR(id);
		        break;       				
			}
			case("cplot"):
			{
				spc=spcService.deleteC(id);
		        break;       				
			}
			case("uplot"):
			{
				spc=spcService.deleteU(id);
		        break;       				
			}
			case("zplot"):
			{
				spc=spcService.deleteZ(id);
		        break;       				
			}
			default:
			{
				addNoPlotType(spc, plotType);
				return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
			}
		}
		addCreateLandDataFormatL(spc,plotType);
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
			case("xmrplot"):
			{
				spc=spcService.deleteXMRByName(name);
		        break;       				
			}
			case("cplot"):
			{
				spc=spcService.deleteCByName(name);
		        break;       				
			}
			case("uplot"):
			{
				spc=spcService.deleteUByName(name);
		        break;       				
			}
			case("zplot"):
			{
				spc=spcService.deleteZByName(name);
		        break;       				
			}
			default:
			{
				addNoPlotType(spc, plotType);
				return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
			}
		}
		addCreateLandDataFormatL(spc,plotType);
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
			case("xmrplot"):
			{
				spc=spcService.getXMRById(id);
		        break;       				
			}
			case("cplot"):
			{
				spc=spcService.getCById(id);
		        break;       				
			}
			case("uplot"):
			{
				spc=spcService.getUById(id);
		        break;       				
			}
			case("zplot"):
			{
				spc=spcService.getZById(id);
		        break;       				
			}
			default:
			{
				addNoPlotType(spc, plotType);
				return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
			}			
		}
		addCreateLandDataFormatL(spc,plotType);
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
			case("xmrplot"):
			{
				spc=spcService.getXMRByName(name);
		        break;       				
			}
			case("cplot"):
			{
				spc=spcService.getCByName(name);
		        break;       				
			}
			case("uplot"):
			{
				spc=spcService.getUByName(name);
		        break;       				
			}
			case("zplot"):
			{
				spc=spcService.getZByName(name);
		        break;       				
			}
			
			default:
			{
				addNoPlotType(spc, plotType);
				return new ResponseEntity<Spc>(spc,spc.getHttpStatus());
			}			
		}
		addCreateLandDataFormatL(spc,plotType);
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
	//spc所有类型findAll
	@RequestMapping(value="/{plotType}/list",method=RequestMethod.GET)
	@ResponseBody
	public HttpEntity<SpcList> getList(@PathVariable(value="plotType") 
	 String plotType) 
	{
		SpcList spcList=new SpcList();
			
		switch(plotType)
		{
			    
			case("xrplot"):
				{
				   spcList=spcService.getSpcXRList();
				   setSpcListLink(spcList, plotType );
				   break;       				
				}
			case("xsplot"):
			{
				spcList=spcService.getSpcXSList();
				   setSpcListLink(spcList, plotType);
			       break;       				
			}
			case("xmrplot"):
			{
				spcList=spcService.getSpcXMRList();
				   setSpcListLink(spcList, plotType);
			       break;       				
			}
			case("cplot"):
			{
				spcList=spcService.getSpcCList();
				   setSpcListLink(spcList, plotType);
			       break;       				
			}
			case("uplot"):
			{
				spcList=spcService.getSpcUList();
				   setSpcListLink(spcList, plotType);
			       break;       				
			}
			case("zplot"):
			{
				spcList=spcService.getSpcZList();
				   setSpcListLink(spcList, plotType);
			       break;       				
			}
			default:
			{
				spcList.setError("无"+plotType+"类型SPC控制图");
				spcList.setHttpStatus(HttpStatus.NOT_FOUND);
				spcList.add(linkTo(methodOn(SPCController.class).getPlotList())
						.withRel("plotList"));
					//spcList.a
				return new ResponseEntity<SpcList>( spcList,spcList.getHttpStatus());
			}			
		}

		spcList.add(linkTo(methodOn(SPCController.class).getList(plotType))
						.withSelfRel());
		return new ResponseEntity<SpcList>( spcList,spcList.getHttpStatus());

	}

	@RequestMapping(value="/{plotType}/list/{name}",method=RequestMethod.GET)
	@ResponseBody
	public HttpEntity<SpcList> getListByProjectName(@PathVariable(value="plotType") 
	 String plotType,@PathVariable(value="name") String name) 
	{
		SpcList spcList=new SpcList();
			
		switch(plotType)
		{
			    
			case("xrplot"):
				{
				 	spcList=spcService.getSpcXRListByProjectName(name);
				   setSpcListLink(spcList, plotType );
				   break;       				
				}
			case("xsplot"):
			{
				spcList=spcService.getSpcXSListByProjectName(name);
				   setSpcListLink(spcList, plotType);
			       break;       				
			}
			case("xmrplot"):
			{
				spcList=spcService.getSpcXMRListByProjectName(name);
				   setSpcListLink(spcList, plotType);
			       break;       				
			}
			case("cplot"):
			{
				spcList=spcService.getSpcCListByProjectName(name);
				   setSpcListLink(spcList, plotType);
			       break;       				
			}
			case("uplot"):
			{
				spcList=spcService.getSpcUListByProjectName(name);
				   setSpcListLink(spcList, plotType);
			       break;       				
			}
			case("zplot"):
			{
				spcList=spcService.getSpcZListByProjectName(name);
				   setSpcListLink(spcList, plotType);
			       break;       				
			}
			default:
			{
				spcList.setError("无"+plotType+"类型SPC控制图");
				spcList.setHttpStatus(HttpStatus.NOT_FOUND);
				spcList.add(linkTo(methodOn(SPCController.class).getPlotList())
						.withRel("plotList"));
					//spcList.a
				return new ResponseEntity<SpcList>( spcList,spcList.getHttpStatus());
			}			
		}

		if(spcList.getError()!=null)
		{
			spcList.add(linkTo(methodOn(SPCController.class).getList(plotType))
					.withRel(plotType+"/list"));
		}
		else{
			spcList.add(linkTo(methodOn(SPCController.class).getListByProjectName(plotType, name))
					.withSelfRel());
		}		
		return new ResponseEntity<SpcList>( spcList,spcList.getHttpStatus());

	}
	//spc增加create和datdaformat链接
	private void addCreateLandDataFormatL(Spc spc,String plotType)
	{
		spc.add(linkTo(methodOn(SPCController.class).create(plotType, null))
					.withRel("create"));
		spc.add(linkTo(methodOn(SPCController.class).getDataFormat(plotType))
					.withRel("dataFormat"));
	}
		
	private void addAllLinkExcludeHelp(Spc spc,String id,String name,String plotType)
	{
		spc.add(linkTo(methodOn(SPCController.class).getByID(plotType,id)).withSelfRel());
		spc.add(linkTo(methodOn(SPCController.class).getByName(plotType,name)).withRel("getByName"));
		spc.add(linkTo(methodOn(SPCController.class).delete(plotType, id)).withRel("delete"));
		spc.add(linkTo(methodOn(SPCController.class).deleteByName(plotType, name)).withRel("deleteByName"));
		spc.add(linkTo(methodOn(SPCController.class).updateXR(plotType, id, null)).withRel("update"));
			
	}
		
	private void addNoPlotType(Spc spc,String plotType)
	{
		spc.setError("无"+plotType+"类型SPC控制图");
		spc.setHttpStatus(HttpStatus.NOT_FOUND);
		spc.add(linkTo(methodOn(SPCController.class).getPlotList())
			.withRel("plotList"));
	}

	
	private void setSpcListLink(SpcList spcList,String plotType)
	{
		for (Spc spc :spcList.getList()) {
			spc.add(linkTo(methodOn(SPCController.class).getByID(plotType,spc.getId())).withSelfRel());
			spc.add(linkTo(methodOn(SPCController.class).updateXR(plotType, spc.getId(), null)).withRel("update"));
			spc.add(linkTo(methodOn(SPCController.class).delete(plotType, spc.getId())).withRel("delete"));			
		}
		spcList.setPlotType(plotType);	
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
			case("xmrplot"):
			{	
				map.put("format",SpcXMR.format());
				return new ResponseEntity<Map<String,String>>
				(map,HttpStatus.OK); 
			}
			case("cplot"):
			{	
				map.put("format",SpcC.format());
				return new ResponseEntity<Map<String,String>>
				(map,HttpStatus.OK); 
			}
			case("uplot"):
			{	
				map.put("format",SpcU.format());
				return new ResponseEntity<Map<String,String>>
				(map,HttpStatus.OK); 
			}
			case("zplot"):
			{	
				map.put("format",SpcZ.format());
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
