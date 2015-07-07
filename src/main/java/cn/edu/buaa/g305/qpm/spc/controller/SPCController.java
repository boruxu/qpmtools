package cn.edu.buaa.g305.qpm.spc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.buaa.g305.qpm.spc.domain.PlotType;
import cn.edu.buaa.g305.qpm.spc.domain.Spc;
import cn.edu.buaa.g305.qpm.spc.domain.SpcList;
import cn.edu.buaa.g305.qpm.spc.domain.spcc.C;
import cn.edu.buaa.g305.qpm.spc.domain.spcu.U;
import cn.edu.buaa.g305.qpm.spc.domain.spcxmr.XmR;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.XR;
import cn.edu.buaa.g305.qpm.spc.domain.spcxs.XS;
import cn.edu.buaa.g305.qpm.spc.domain.spcz.Z;
import cn.edu.buaa.g305.qpm.spc.server.SPCService;

@Controller
@RequestMapping("/api/spc")
public class SPCController {
	
	@Autowired
	private SPCService spcService;
	

	@RequestMapping(value="/{plotType}",method=RequestMethod.POST)
	@ResponseBody

	public  Spc create(@PathVariable PlotType plotType,@RequestBody SpcVO spcVO)
	{
		switch(plotType)
		{
		    
			case XR:
				{
					XR spcXR=new XR();
					spcXR.setInput(spcVO.getInputXR());
					spcXR.setName(spcVO.getName());
					return spcService.save(spcXR, spcVO.getProject());     				
				}
			case XS:
			{
				XS spcXS=new XS();
				spcXS.setInput(spcVO.getInputXS());
				spcXS.setName(spcVO.getName());
				return spcService.save(spcXS,spcVO.getProject());      				
			}
			case XmR:
			{
				XmR spcXMR=new XmR();
				spcXMR.setInput(spcVO.getInputXmR());
				spcXMR.setName(spcVO.getName());
				return spcService.save(spcXMR, spcVO.getProject());      				
			}
			case C:
			{
				C spcC=new C();
				spcC.setInput(spcVO.getInputC());
				spcC.setName(spcVO.getName());
				return spcService.save(spcC, spcVO.getProject());     				
			}
			case U:
			{
				U spcU=new U();
				spcU.setInput(spcVO.getInputU());
				spcU.setName(spcVO.getName());
				return spcService.save(spcU, spcVO.getProject());
       				
			}
			case Z:
			{
				Z spcZ=new Z();
				spcZ.setInput(spcVO.getInputZ());
				spcZ.setName(spcVO.getName());
				return spcService.save(spcZ, spcVO.getProject());
      				
			}

		}
		return null;	
		 
	}
	
	@RequestMapping(value="/{plotType}/{id}",method=RequestMethod.POST)
	@ResponseBody
	public  Spc updateXR(@PathVariable PlotType plotType,
			@PathVariable String id,@RequestBody SpcVO spcVO)
	{
		switch(plotType)
		{
		    
			case XR:
				{
					XR spcXR=new XR();
					spcXR.setInput(spcVO.getInputXR());
					spcXR.setName(spcVO.getName());
					return spcService.update(spcXR, id,spcVO.getProject());     				
				}
			case XS:
			{
				XS spcXS=new XS();
				spcXS.setInput(spcVO.getInputXS());
				spcXS.setName(spcVO.getName());
				return spcService.update(spcXS, id,spcVO.getProject());      				
			}
			case XmR:
			{
				XmR spcXMR=new XmR();
				spcXMR.setInput(spcVO.getInputXmR());
				spcXMR.setName(spcVO.getName());
				return spcService.update(spcXMR, id,spcVO.getProject());      				
			}
			case C:
			{
				C spcC=new C();
				spcC.setInput(spcVO.getInputC());
				spcC.setName(spcVO.getName());
				return spcService.update(spcC, id,spcVO.getProject());     				
			}
			case U:
			{
				U spcU=new U();
				spcU.setInput(spcVO.getInputU());
				spcU.setName(spcVO.getName());
				return spcService.update(spcU, id,spcVO.getProject());
       				
			}
			case Z:
			{
				Z spcZ=new Z();
				spcZ.setInput(spcVO.getInputZ());
				spcZ.setName(spcVO.getName());
				return spcService.update(spcZ, id,spcVO.getProject());
      				
			}

		}
		return null;

	}
	
	@RequestMapping(value="/{plotType}/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public  Spc delete(@PathVariable(value="plotType") PlotType plotType,
			@PathVariable(value="id") String id)
	{
		Spc spc=new Spc();
		switch(plotType)
		{
		    
			case XR:
				{
					spc=spcService.deleteXR(id);
			        break;       				
				}
			case XS:
			{
				spc=spcService.deleteXS(id);
		        break;       				
			}
			case XmR:
			{
				spc=spcService.deleteXMR(id);
		        break;       				
			}
			case C:
			{
				spc=spcService.deleteC(id);
		        break;       				
			}
			case U:
			{
				spc=spcService.deleteU(id);
		        break;       				
			}
			case Z:
			{
				spc=spcService.deleteZ(id);
		        break;       				
			}
		}
		return spc;			

	}
	
	@RequestMapping(value="/{plotType}/byName/{name}",method=RequestMethod.DELETE)
	@ResponseBody
	public  Spc deleteByName(@PathVariable(value="plotType") PlotType plotType,
			@PathVariable(value="name") String name)
	{ 
		Spc spc=new Spc();
		switch(plotType)
		{
		    
			case XR:
				{
					spc=spcService.deleteXRByName(name);
			        break;       				
				}
			case XS:
			{
				spc=spcService.deleteXSByName(name);
		        break;       				
			}
			case XmR:
			{
				spc=spcService.deleteXMRByName(name);
		        break;       				
			}
			case C:
			{
				spc=spcService.deleteCByName(name);
		        break;       				
			}
			case U:
			{
				spc=spcService.deleteUByName(name);
		        break;       				
			}
			case Z:
			{
				spc=spcService.deleteZByName(name);
		        break;       				
			}
		}
		return spc;			

	}
	
	@RequestMapping(value="/{plotType}/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Spc getByID(@PathVariable(value="plotType") PlotType plotType,
			@PathVariable(value="id") String id)
	{ 
		Spc spc=new Spc();
		switch(plotType)
		{
		    
			case XR:
				{
					spc=spcService.getXRById(id);
			        break;       				
				}
			case XS:
			{
				spc=spcService.getXSById(id);
		        break;       				
			}
			case XmR:
			{
				spc=spcService.getXMRById(id);
		        break;       				
			}
			case C:
			{
				spc=spcService.getCById(id);
		        break;       				
			}
			case U:
			{
				spc=spcService.getUById(id);
		        break;       				
			}
			case Z:
			{
				spc=spcService.getZById(id);
		        break;       				
			}		
		}

		return spc;
	

	}
	
	//spc所有类型byName查询
	@RequestMapping(value="/{plotType}/byName/{name}",method=RequestMethod.GET)
	@ResponseBody
	public Spc getByName(@PathVariable(value="plotType") 
	PlotType plotType,@PathVariable(value="name") String name) 
	{
		Spc spc=new Spc();
		switch(plotType)
		{
		    
			case XR:
				{
					spc=spcService.getXRByName(name);
			        break;       				
				}
			case XS:
			{
				spc=spcService.getXSByName(name);
		        break;       				
			}
			case XmR:
			{
				spc=spcService.getXMRByName(name);
		        break;       				
			}
			case C:
			{
				spc=spcService.getCByName(name);
		        break;       				
			}
			case U:
			{
				spc=spcService.getUByName(name);
		        break;       				
			}
			case Z:
			{
				spc=spcService.getZByName(name);
		        break;       				
			}			
		}
		return spc;
	}
	//spc所有类型findAll
	@RequestMapping(value="/{plotType}/list",method=RequestMethod.GET)
	@ResponseBody
	public SpcList getList(@PathVariable(value="plotType") 
	 PlotType plotType) 
	{
		SpcList spcList=new SpcList();
			
		switch(plotType)
		{
			    
			case XR:
				{
				   spcList=spcService.getSpcXRList();
				   break;       				
				}
			case XS:
			{
				spcList=spcService.getSpcXSList();
			       break;       				
			}
			case XmR:
			{
				spcList=spcService.getSpcXMRList();
			       break;       				
			}
			case C:
			{
				spcList=spcService.getSpcCList();
			       break;       				
			}
			case U:
			{
				spcList=spcService.getSpcUList();
			       break;       				
			}
			case Z:
			{
				spcList=spcService.getSpcZList();
			       break;       				
			}		
		}

		return spcList;

	}

	@RequestMapping(value="/{plotType}/list/{name}",method=RequestMethod.GET)
	@ResponseBody
	public SpcList getListByProjectName(@PathVariable(value="plotType") 
	 PlotType plotType,@PathVariable(value="name") String name) 
	{
		SpcList spcList=new SpcList();
			
		switch(plotType)
		{
			    
			case XR:
				{
				 	spcList=spcService.getSpcXRListByProjectName(name);

				   break;       				
				}
			case XS:
			{
				spcList=spcService.getSpcXSListByProjectName(name);
			       break;       				
			}
			case XmR:
			{
				spcList=spcService.getSpcXMRListByProjectName(name);
			       break;       				
			}
			case C:
			{
				spcList=spcService.getSpcCListByProjectName(name);
			       break;       				
			}
			case U:
			{
				spcList=spcService.getSpcUListByProjectName(name);
			       break;       				
			}
			case Z:
			{
				spcList=spcService.getSpcZListByProjectName(name);
			       break;       				
			}
		
		}
	
		return spcList;

	}
		
	@RequestMapping(value="/help/{plotType}/dataFormat",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,String> getDataFormat(@PathVariable(value="plotType") 
	 					PlotType plotType) 
	{
		Map<String, String> map=new HashMap<String, String>();
		switch(plotType)
		{
			case XR:
			{	
				map.put("format",XR.format());
				break;
			}
			case XS:
			{	
				map.put("format",XS.format());
				break;
			}
			case XmR:
			{	
				map.put("format",XmR.format());
				break;
			}
			case C:
			{	
				map.put("format",C.format());
				break;
			}
			case U:
			{	
				map.put("format",U.format());
				break;
			}
			case Z:
			{	
				map.put("format",Z.format());
				break;
 
			}

			
		}
		return map;
		
	}
	
	//支持spc视图数组列表
	@RequestMapping(value="/help/plotTypeList",method=RequestMethod.GET)
	@ResponseBody
	public PlotType[] getPlotList()
	{
		PlotType[] lists=PlotType.values();
		return lists;	
	}
	
	//简化开发，不使用超媒体,获得一个项目下的所有spc控制图
	@RequestMapping(value="/plotList/{name}",method=RequestMethod.GET)
	@ResponseBody
	public List<? extends Spc> getSpcListByProject(@PathVariable String name)
	{
		return spcService.getAllSpcListByProject(name).getList();
		
	}
	
	
	
}
