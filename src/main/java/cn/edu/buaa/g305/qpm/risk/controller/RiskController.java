package cn.edu.buaa.g305.qpm.risk.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.buaa.g305.qpm.risk.domain.Risk;
import cn.edu.buaa.g305.qpm.risk.domain.RiskList;
import cn.edu.buaa.g305.qpm.risk.domain.RiskTrack;
import cn.edu.buaa.g305.qpm.risk.server.RiskServer;


@Controller
@RequestMapping("/api/risk/riskItem")
public class RiskController {
	
	@Autowired
	private RiskServer riskServer;
	
    //风险识别时为创建risk的时候
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<Risk> create(@RequestBody RiskVO riskVO)
	{
		Risk risk=new Risk();
		//创建已识别风险接受的输入
		risk.setName(riskVO.getName());
		risk.setRiskContex(riskVO.getRiskContex());
		risk.setRiskPotentialInfluence(riskVO.getRiskPotentialInfluence());
		risk.setRiskCondition(riskVO.getRiskCondition());
		risk.setRiskState(riskVO.getRiskState());
		risk.setRiskProposeMeasure(riskVO.getRiskProposeMeasure());
		
		risk=riskServer.saveRisk(risk, riskVO.getProject(),riskVO.getRiskType(),riskVO.getRiskTrack());
		if(risk.getError()==null)
		{
			addAllLink(risk, risk.getId(), risk.getName(),"indentify");
		}
		risk.add(linkTo(methodOn(RiskController.class).create(null)).withRel("create"));
		
		return new ResponseEntity<Risk>(risk,risk.getHttpStatus());
		
	}
	
	@RequestMapping(value="/{step}/{id}",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<Risk> update(@RequestBody RiskVO riskVO
			,@PathVariable String step,@PathVariable String id)
	{
		Risk risk=riskServer.updateRisk(riskVO, step,id,riskVO.getProject(),
						riskVO.getRiskType(),riskVO.getRiskTrack());

		
		if(risk.getError()==null)
		{
			addAllLink(risk, risk.getId(), risk.getName(),"step");
		}
		risk.add(linkTo(methodOn(RiskController.class).create(null)).withRel("create"));
		return new ResponseEntity<Risk>(risk,risk.getHttpStatus());
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<Risk> getById(@PathVariable String id)
	{
		Risk risk=riskServer.getRiskById(id);

		if(risk.getError()==null)
		{
			addAllLink(risk, risk.getId(), risk.getName(),"step");
		}
		risk.add(linkTo(methodOn(RiskController.class).create(null)).withRel("create"));
		return new ResponseEntity<Risk>(risk,risk.getHttpStatus());
		
	}

	
	@RequestMapping(value="/byName/{name}",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<Risk> getByName(@PathVariable String name)
	{
		Risk risk=riskServer.getRiskByName(name);

		if(risk.getError()==null)
		{
			addAllLink(risk, risk.getId(), risk.getName(),"step");
		}
		risk.add(linkTo(methodOn(RiskController.class).create(null)).withRel("create"));
		return new ResponseEntity<Risk>(risk,risk.getHttpStatus());
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public  HttpEntity<Risk> delete(@PathVariable String id)
	{
		Risk risk=riskServer.deleteRiskById(id);

		if(risk.getError()==null)
		{
			addAllLink(risk, risk.getId(), risk.getName(),"step");
		}
		risk.add(linkTo(methodOn(RiskController.class).create(null)).withRel("create"));
		return new ResponseEntity<Risk>(risk,risk.getHttpStatus());
		
	}

	@RequestMapping(value="/byName/{name}",method=RequestMethod.DELETE)
	@ResponseBody
	public  HttpEntity<Risk> deleteByName(@PathVariable String name)
	{
		Risk risk=riskServer.deleteRiskByName(name);

		if(risk.getError()==null)
		{
			addAllLink(risk, risk.getId(), risk.getName(),"step");
		}
		risk.add(linkTo(methodOn(RiskController.class).create(null)).withRel("create"));
		return new ResponseEntity<Risk>(risk,risk.getHttpStatus());
		
	}
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<RiskList> getProjectList()
	{
		RiskList riskList=new RiskList();
		riskList=riskServer.getRiskList();
		
		setProjectListLink(riskList);

		riskList.add(linkTo(methodOn(RiskController.class).getProjectList()).withSelfRel());
		return new ResponseEntity<RiskList>(riskList,riskList.getHttpStatus());
		
	}
	
	
	@RequestMapping(value="/list/{name}",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<RiskList> getProjectList(@PathVariable String name)
	{
		RiskList riskList=new RiskList();
		riskList=riskServer.getRiskListByProject(name);

		if(riskList.getError()==null)
		{
			riskList.add(linkTo(methodOn(RiskController.class).getProjectList(name)).withSelfRel());
			setProjectListLink(riskList);
		}
		return new ResponseEntity<RiskList>(riskList,riskList.getHttpStatus());
		
	}
	
	private void addAllLink(Risk risk,String id,String name,String step)
	{
		risk.add(linkTo(methodOn(RiskController.class).getById(id)).withSelfRel());
		risk.add(linkTo(methodOn(RiskController.class).getByName(name)).withRel("getByName"));
		risk.add(linkTo(methodOn(RiskController.class).delete(id)).withRel("delete"));
		risk.add(linkTo(methodOn(RiskController.class).deleteByName(name)).withRel("deleteByName"));
		risk.add(linkTo(methodOn(RiskController.class).update(null,step, id)).withRel("update"));
			
	}

	private void setProjectListLink(RiskList riskList)
	{
		for (Risk risk :riskList.getList()) {
			risk.add(linkTo(methodOn(RiskController.class).getById(risk.getId())).withSelfRel());
			risk.add(linkTo(methodOn(RiskController.class).update( null,"step",risk.getId())).withRel("update"));
			risk.add(linkTo(methodOn(RiskController.class).delete( risk.getId())).withRel("delete"));			
		}

	}
	

}
