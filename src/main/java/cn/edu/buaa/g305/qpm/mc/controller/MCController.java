package cn.edu.buaa.g305.qpm.mc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationIn;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationInXYArray;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationOut;
import cn.edu.buaa.g305.qpm.correlation.server.CorrelationServer;

@Controller
@RequestMapping("/api/mc")
public class MCController {

	//创建mc
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public void dsa()
	{
			
	}

	

}
