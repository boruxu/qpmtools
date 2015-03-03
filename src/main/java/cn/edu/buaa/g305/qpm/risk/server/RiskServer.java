package cn.edu.buaa.g305.qpm.risk.server;

import cn.edu.buaa.g305.qpm.risk.domain.Risk;
import cn.edu.buaa.g305.qpm.risk.domain.RiskList;
import cn.edu.buaa.g305.qpm.risk.domain.RiskType;
import cn.edu.buaa.g305.qpm.risk.domain.RiskTypeList;
import cn.edu.buaa.g305.qpm.system.domain.Organization;
import cn.edu.buaa.g305.qpm.system.domain.ProjectList;

public interface RiskServer {
	
	RiskType getRiskTypeById(String id);
	RiskType getRiskTypeByName(String name);
	RiskType deleteRiskTypeById(String id);
	RiskType deleteRiskTypeByName(String name);
	RiskType saveRiskType(RiskType riskType,String organization);
	RiskType updateRiskType(RiskType risk,String id,String organization);
	
	RiskTypeList getRiskTypeList();
	RiskTypeList getRiskTypeListByOrganization(String organizarion);
	
	Risk getRiskById(String id);
	Risk getRiskByName(String name);
	Risk deleteRiskById(String id);
	Risk deleteRiskByName(String id);
	Risk saveRisk(Risk risk,String project,String riskType);
	//更新risk不改变所属项目,所属类型
	Risk updateRisk(Risk risk,String id);
	
	RiskList getRiskList();
	RiskList getRiskListByProject(String project);
	

}
