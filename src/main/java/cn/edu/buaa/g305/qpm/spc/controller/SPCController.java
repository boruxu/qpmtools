package cn.edu.buaa.g305.qpm.spc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping(value="/xrplot",method=RequestMethod.POST)
	@ResponseBody
	public SpcXR createXR(@RequestBody SpcXR spcXR)
	{
		return spcService.save(spcXR);
	}
	
	@RequestMapping(value="/xrplot/input/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public SpcXR updateXR(@RequestBody SpcXR spcXR,@PathVariable String id)
	{
		return spcService.save(spcXR);
	}
	
	@RequestMapping(value="/xrplot/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteXR(@PathVariable String id)
	{ 
		spcService.delete(id);
		return "{deleted:"+id+"}";
	}
	
	@RequestMapping(value="/xrplot/{id}",method=RequestMethod.GET)
	@ResponseBody
	public SpcXR getXRByID(@PathVariable String id)
	{ 
		return spcService.getById(id);
	}
	
	@RequestMapping(value="/xrplot/byName/{name}",method=RequestMethod.GET)
	@ResponseBody
	public SpcXR getXRByName(@PathVariable String name)
	{
		SpcXR spcXR=spcService.getSpcxrByName(name);
		if(spcXR==null)
		{
			spcXR=new SpcXR();
			spcXR.setError("无此名");
			return spcXR;
		}
		return spcXR;
	}

}
