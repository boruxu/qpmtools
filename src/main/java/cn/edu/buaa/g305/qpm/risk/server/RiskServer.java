package cn.edu.buaa.g305.qpm.risk.server;

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

}
