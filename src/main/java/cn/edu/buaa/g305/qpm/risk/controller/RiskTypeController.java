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

import cn.edu.buaa.g305.qpm.risk.domain.RiskType;
import cn.edu.buaa.g305.qpm.risk.domain.RiskTypeList;
import cn.edu.buaa.g305.qpm.risk.server.RiskServer;


@Controller
@RequestMapping("/api/risk/riskType")
public class RiskTypeController {
	
	@Autowired
	private RiskServer riskServer;
	

	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<RiskType> create(@RequestBody RiskTypeVO riskVO)
	{
		RiskType riskType=new RiskType();
		riskType.setName(riskVO.getName());
		riskType.setDescription(riskVO.getDescription());
		riskType.setExsample(riskVO.getExsample());
		riskType.setEmergencyMeasure(riskVO.getEmergencyMeasure());
		riskType.setMitigationMeasure(riskVO.getMitigationMeasure());
		riskType=riskServer.saveRiskType(riskType, riskVO.getOrganization());
		if(riskType.getError()==null)
		{
			addAllLink(riskType, riskType.getId(), riskType.getName());
		}
		riskType.add(linkTo(methodOn(RiskTypeController.class).create(null)).withRel("create"));
		
		return new ResponseEntity<RiskType>(riskType,riskType.getHttpStatus());
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	@ResponseBody
	public  HttpEntity<RiskType> update(@RequestBody RiskTypeVO riskVO
			,@PathVariable String id)
	{
		RiskType riskType=new RiskType();
		riskType.setName(riskVO.getName());
		riskType.setDescription(riskVO.getDescription());
		riskType.setExsample(riskVO.getExsample());
		riskType.setEmergencyMeasure(riskVO.getEmergencyMeasure());
		riskType.setMitigationMeasure(riskVO.getMitigationMeasure());
		riskType=riskServer.updateRiskType(riskType, id, riskVO.getOrganization());

		if(riskType.getError()==null)
		{
			addAllLink(riskType, riskType.getId(), riskType.getName());
		}
		riskType.add(linkTo(methodOn(RiskTypeController.class).create(null)).withRel("create"));
		return new ResponseEntity<RiskType>(riskType,riskType.getHttpStatus());
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<RiskType> getById(@PathVariable String id)
	{
		RiskType riskType=new RiskType();
		riskType=riskServer.getRiskTypeById(id);

		if(riskType.getError()==null)
		{
			addAllLink(riskType, riskType.getId(), riskType.getName());
		}
		riskType.add(linkTo(methodOn(RiskTypeController.class).create(null)).withRel("create"));
		return new ResponseEntity<RiskType>(riskType,riskType.getHttpStatus());
		
	}

	
	@RequestMapping(value="/byName/{name}",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<RiskType> getByName(@PathVariable String name)
	{
		RiskType riskType=new RiskType();
		riskType=riskServer.getRiskTypeByName(name);

		if(riskType.getError()==null)
		{
			addAllLink(riskType, riskType.getId(), riskType.getName());
		}
		riskType.add(linkTo(methodOn(RiskTypeController.class).create(null)).withRel("create"));
		return new ResponseEntity<RiskType>(riskType,riskType.getHttpStatus());
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public  HttpEntity<RiskType> delete(@PathVariable String id)
	{
		RiskType riskType=new RiskType();
		riskType=riskServer.deleteRiskTypeById(id);

		if(riskType.getError()==null)
		{
			addAllLink(riskType, riskType.getId(), riskType.getName());
		}
		riskType.add(linkTo(methodOn(RiskTypeController.class).create(null)).withRel("create"));
		return new ResponseEntity<RiskType>(riskType,riskType.getHttpStatus());
		
	}

	@RequestMapping(value="/byName/{name}",method=RequestMethod.DELETE)
	@ResponseBody
	public  HttpEntity<RiskType> deleteByName(@PathVariable String name)
	{
		RiskType riskType=new RiskType();
		riskType=riskServer.deleteRiskTypeByName(name);

		if(riskType.getError()==null)
		{
			addAllLink(riskType, riskType.getId(), riskType.getName());
		}
		riskType.add(linkTo(methodOn(RiskTypeController.class).create(null)).withRel("create"));
		return new ResponseEntity<RiskType>(riskType,riskType.getHttpStatus());
		
	}
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<RiskTypeList> getProjectList()
	{
		RiskTypeList riskTypeList=new RiskTypeList();
		riskTypeList=riskServer.getRiskTypeList();
		
		setProjectListLink(riskTypeList);

		riskTypeList.add(linkTo(methodOn(RiskTypeController.class).getProjectList()).withSelfRel());
		return new ResponseEntity<RiskTypeList>(riskTypeList,riskTypeList.getHttpStatus());
		
	}
	
	
	@RequestMapping(value="/list/{name}",method=RequestMethod.GET)
	@ResponseBody
	public  HttpEntity<RiskTypeList> getProjectList(@PathVariable String name)
	{
		RiskTypeList riskTypeList=new RiskTypeList();
		riskTypeList=riskServer.getRiskTypeListByOrganization(name);

		if(riskTypeList.getError()==null)
		{
			riskTypeList.add(linkTo(methodOn(RiskTypeController.class).getProjectList(name)).withSelfRel());
			setProjectListLink(riskTypeList);
		}
		return new ResponseEntity<RiskTypeList>(riskTypeList,riskTypeList.getHttpStatus());
		
	}
	
	private void addAllLink(RiskType riskType,String id,String name)
	{
		riskType.add(linkTo(methodOn(RiskTypeController.class).getById(id)).withSelfRel());
		riskType.add(linkTo(methodOn(RiskTypeController.class).getByName(name)).withRel("getByName"));
		riskType.add(linkTo(methodOn(RiskTypeController.class).delete(id)).withRel("delete"));
		riskType.add(linkTo(methodOn(RiskTypeController.class).deleteByName(name)).withRel("deleteByName"));
		riskType.add(linkTo(methodOn(RiskTypeController.class).update(null, id)).withRel("update"));
			
	}

	private void setProjectListLink(RiskTypeList riskTypeList)
	{
		for (RiskType riskType :riskTypeList.getList()) {
			riskType.add(linkTo(methodOn(RiskTypeController.class).getById(riskType.getId())).withSelfRel());
			riskType.add(linkTo(methodOn(RiskTypeController.class).update(  null,riskType.getId())).withRel("update"));
			riskType.add(linkTo(methodOn(RiskTypeController.class).delete( riskType.getId())).withRel("delete"));			
		}

	}
	

}
