package cn.edu.buaa.g305.qpm.risk.server.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.risk.controller.RiskVO;
import cn.edu.buaa.g305.qpm.risk.dao.RiskRepository;
import cn.edu.buaa.g305.qpm.risk.dao.RiskTypeRepository;
import cn.edu.buaa.g305.qpm.risk.domain.Risk;
import cn.edu.buaa.g305.qpm.risk.domain.RiskList;
import cn.edu.buaa.g305.qpm.risk.domain.RiskTrack;
import cn.edu.buaa.g305.qpm.risk.domain.RiskType;
import cn.edu.buaa.g305.qpm.risk.domain.RiskTypeList;
import cn.edu.buaa.g305.qpm.risk.server.RiskServer;
import cn.edu.buaa.g305.qpm.system.dao.OrganizationRepository;
import cn.edu.buaa.g305.qpm.system.dao.ProjectRepository;
import cn.edu.buaa.g305.qpm.system.domain.Organization;
import cn.edu.buaa.g305.qpm.system.domain.Project;
import cn.edu.buaa.g305.qpm.system.server.SystemFind;

@Component
public class RiskServerImp implements RiskServer{
	
	@Autowired
	private RiskTypeRepository riskTypeRepository;
	@Autowired
	private RiskRepository riskRepository;
	@Autowired
	private SystemFind systemFind;
	@Autowired
	private OrganizationRepository organizationRepository;
	@Autowired
	private ProjectRepository projectRepository;

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
			riskType=saveRiskType(riskType, organization);
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
		riskTypeList.setOrganization("所有组织");
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

	@Override
	public Risk getRiskById(String id) {
		Risk risk=riskRepository.findOne(id);
		if(risk==null)
		{
			risk=new Risk();
			//找不到资源，设置错误信息和状态码
			risk.setErrorOutput("ID为"+id+"的风险不存在",HttpStatus.NOT_FOUND);
			return risk;
		}
		else{
			risk.setHttpStatus(HttpStatus.OK);
			return risk;
		}
	}

	@Override
	public Risk getRiskByName(String name) {
		Risk risk=riskRepository.findByName(name);
		if(risk==null)
		{
			risk=new Risk();
			//找不到资源，设置错误信息和状态码
			risk.setErrorOutput("名为"+name+"的风险不存在",HttpStatus.NOT_FOUND);
			return risk;
		}
		else{
			risk.setHttpStatus(HttpStatus.OK);
			return risk;
		}
	}

	@Override
	public Risk deleteRiskById(String id) {
		
		Risk risk=getRiskById(id);
		if(risk.getError()==null)
		{
			riskRepository.delete(id);
		}
		
		return risk;
	}

	@Override
	public Risk deleteRiskByName(String name) {
		Risk risk=getRiskByName(name);
		if(risk.getError()==null)
		{
			riskRepository.delete(risk.getId());
		}
		
		return risk;
	}

	@Override
	public Risk saveRisk(Risk risk, String project,String riskType,RiskTrack riskTrack) {

		Project projectDB=systemFind.findProductAffiliation(project);
		risk.setProject(projectDB);
		RiskType riskTypeDB=riskTypeRepository.findByName(riskType);
		if(riskTypeDB==null)
		{
			risk.setErrorOutput("所选风险类型"+riskType+"不存在", HttpStatus.BAD_REQUEST);
			return risk;
		}
		risk.setRiskType(riskTypeDB);
		List<RiskTrack> riskTracks=new ArrayList<RiskTrack>();
		riskTracks.add(riskTrack);
		risk.setRiskTrack(riskTracks);
		try {
			risk=riskRepository.save(risk);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			risk.setErrorOutput("名字重复，请重新命名", HttpStatus.BAD_REQUEST);
			return risk;
		}
		
		risk.setHttpStatus(HttpStatus.CREATED);
		return risk;
	}
	
	public Risk saveRiskNoTrack(Risk risk, String project,String riskType) {

		Project projectDB=systemFind.findProductAffiliation(project);
		risk.setProject(projectDB);
		RiskType riskTypeDB=riskTypeRepository.findByName(riskType);
		if(riskTypeDB==null)
		{
			risk.setErrorOutput("所选风险类型"+riskType+"不存在", HttpStatus.BAD_REQUEST);
			return risk;
		}
		risk.setRiskType(riskTypeDB);

		try {
			risk=riskRepository.save(risk);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			risk.setErrorOutput("名字重复，请重新命名", HttpStatus.BAD_REQUEST);
			return risk;
		}
		
		risk.setHttpStatus(HttpStatus.CREATED);
		return risk;
	}

	@Override
	public Risk updateRisk(RiskVO riskVO,String step,String id,String project,String riskType,RiskTrack riskTrack) {
		Risk riskDB=getRiskById(id);
		
		if(riskDB.getError()==null)
		{
			List<RiskTrack> riskTracks=riskDB.getRiskTrack();
			riskTracks.add(riskTrack);
			switch (step) {
			case "identify":
			{
				setIdentify(riskDB,riskVO);								
				break;
			}
			case "analysis":
			{
				setAnalysis(riskDB,riskVO);
				break;
			}
			case "evaluate":
			{
				setAnalysis(riskDB,riskVO);
				break;
			}
			case "plan":
			{
				setPlan(riskDB,riskVO);
				break;
			}
				

			default:
				break;
			}
			
			riskDB=saveRiskNoTrack(riskDB, project, riskType);
			if(riskDB.getError()==null)
			{
				riskDB.setHttpStatus(HttpStatus.OK);
			}

			return riskDB;

			 
		}
			return riskDB;

	}

	@Override
	public RiskList getRiskList() {
		List<Risk> risks= (List<Risk>) riskRepository.findAll();
		RiskList riskList=new RiskList();
		riskList.setList(risks);
		riskList.setProject("所有项目");
		riskList.setHttpStatus(HttpStatus.OK);
		return riskList;
	}

	@Override
	public RiskList getRiskListByProject(String name) {
		RiskList riskList=new RiskList();
		Project project=projectRepository.findByName(name);
		if(project==null)
		{
			riskList.setError("名为"+name+"项目不存在");
			riskList.setHttpStatus(HttpStatus.NOT_FOUND);
			riskList.setList(new ArrayList<Risk>());
			return riskList;
		}
		else {
			riskList.setProject(name);
			riskList.setList(riskRepository.findByProject(project));
			riskList.setHttpStatus(HttpStatus.OK);
			return riskList;
		}
	}
	
	private void setIdentify(Risk riskDB,RiskVO riskVO)
	{
		riskDB.setName(riskVO.getName());
		riskDB.setRiskContex(riskVO.getRiskContex());
		riskDB.setRiskPotentialInfluence(riskVO.getRiskPotentialInfluence());
		riskDB.setRiskCondition(riskVO.getRiskCondition());
		riskDB.setRiskState(riskVO.getRiskState());
		riskDB.setRiskProposeMeasure(riskVO.getRiskProposeMeasure());
		
	}
	
	
	private void setAnalysis(Risk riskDB,RiskVO riskVO)
	{
		setIdentify(riskDB,riskVO);
		riskDB.setRiskSource(riskVO.getRiskSource());
		riskDB.setRiskWarning(riskVO.getRiskWarning());
		riskDB.setRiskPriority(riskVO.getRiskPriority());
		riskDB.setRiskPosibility(riskVO.getRiskPosibility());
		riskDB.setRiskUrgency(riskVO.getRiskUrgency());
		riskDB.setRiskDamage(riskVO.getRiskDamage());
	}
	
	private void setPlan(Risk riskDB,RiskVO riskVO)
	{
		setAnalysis(riskDB,riskVO);
		riskDB.setRiskPlanMeasure(riskVO.getRiskPlanMeasure());
		
	}
	

}
