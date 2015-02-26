package cn.edu.buaa.g305.qpm.risk.server.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.risk.dao.RiskTypeRepository;
import cn.edu.buaa.g305.qpm.risk.domain.RiskType;
import cn.edu.buaa.g305.qpm.risk.domain.RiskTypeList;
import cn.edu.buaa.g305.qpm.risk.server.RiskServer;
import cn.edu.buaa.g305.qpm.system.dao.OrganizationRepository;
import cn.edu.buaa.g305.qpm.system.domain.Organization;
import cn.edu.buaa.g305.qpm.system.domain.Project;
import cn.edu.buaa.g305.qpm.system.domain.ProjectList;
import cn.edu.buaa.g305.qpm.system.server.SystemFind;

@Component
public class RiskServerImp implements RiskServer{
	
	@Autowired
	private RiskTypeRepository riskTypeRepository;
	@Autowired
	private SystemFind systemFind;
	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	public RiskType getRiskTypeById(String id) {
		
		RiskType riskType=riskTypeRepository.findOne(id);
		if(riskType==null)
		{
			riskType=new RiskType();
			//找不到资源，设置错误信息和状态码
			riskType.setErrorOutput("ID为"+id+"的风险类型不存在",HttpStatus.NOT_FOUND);
			return riskType;
		}
		else{
			riskType.setHttpStatus(HttpStatus.OK);
			return riskType;
		}
	}

	@Override
	public RiskType getRiskTypeByName(String name) {
		RiskType riskType=riskTypeRepository.findByName(name);
		if(riskType==null)
		{
			riskType=new RiskType();
			//找不到资源，设置错误信息和状态码
			riskType.setErrorOutput("名为"+name+"的风险类型不存在",HttpStatus.NOT_FOUND);
			return riskType;
		}
		else{
			riskType.setHttpStatus(HttpStatus.OK);
			return riskType;
		}
	}

	@Override
	public RiskType deleteRiskTypeById(String id) {
		RiskType riskType=getRiskTypeById(id);
		if(riskType.getError()==null)
		{
			riskTypeRepository.delete(id);
		}
		
		return riskType;
	}

	@Override
	public RiskType deleteRiskTypeByName(String name) {
		RiskType riskType=getRiskTypeByName(name);
		if(riskType.getError()==null)
		{
			riskTypeRepository.delete(riskType.getId());
		}
		
		return riskType;
	}

	@Override
	public RiskType saveRiskType(RiskType riskType,String organization) {
		Organization organizationDB=systemFind.findProjectAffiliation(organization);
		riskType.setOrganization(organizationDB);
		try {
			riskType=riskTypeRepository.save(riskType);
		} catch (DuplicateKeyException e) {
			riskType.setErrorOutput("名字重复，请重新命名", HttpStatus.BAD_REQUEST);
			return riskType;
		}
		
		riskType.setHttpStatus(HttpStatus.CREATED);
		return riskType;
	}

	@Override
	public RiskType updateRiskType(RiskType riskType, String id,
			String organization) {
		RiskType riskTypeDB=getRiskTypeById(id);
		if(riskTypeDB.getError()==null)
		{
			riskType.setId(id);
			riskType=saveRiskType(riskTypeDB, organization);
			if(riskType.getError()==null)
			{
				riskType.setHttpStatus(HttpStatus.OK);
				return riskType;
			}
			else {
				return riskType;
			}
			 
		}
		else {
			return riskTypeDB;
		}
	}

	@Override
	public RiskTypeList getRiskTypeList() {
		List<RiskType> projects= (List<RiskType>) riskTypeRepository.findAll();
		RiskTypeList riskTypeList=new RiskTypeList();
		riskTypeList.setList(projects);
		riskTypeList.setHttpStatus(HttpStatus.OK);
		return riskTypeList;
	}

	@Override
	public RiskTypeList getRiskTypeListByOrganization(String name) {
		RiskTypeList riskTypeList=new RiskTypeList();
		Organization organization=organizationRepository.findByName(name);
		if(organization==null)
		{
			riskTypeList.setError("名为"+name+"组织不存在");
			riskTypeList.setHttpStatus(HttpStatus.NOT_FOUND);
			riskTypeList.setList(new ArrayList<RiskType>());
			return riskTypeList;
		}
		else {
			riskTypeList.setOrganization(name);
			riskTypeList.setList(riskTypeRepository.findByOrganization(organization));
			riskTypeList.setHttpStatus(HttpStatus.OK);
			return riskTypeList;
		}
	}
	
	

}
