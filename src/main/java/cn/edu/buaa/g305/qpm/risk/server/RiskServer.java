package cn.edu.buaa.g305.qpm.risk.server;

import cn.edu.buaa.g305.qpm.risk.controller.RiskVO;
import cn.edu.buaa.g305.qpm.risk.domain.Risk;
import cn.edu.buaa.g305.qpm.risk.domain.RiskList;
import cn.edu.buaa.g305.qpm.risk.domain.RiskTrack;
import cn.edu.buaa.g305.qpm.risk.domain.RiskType;
import cn.edu.buaa.g305.qpm.risk.domain.RiskTypeList;

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
	Risk saveRisk(Risk risk,String project,String riskType,RiskTrack riskTrack);
	//更新risk不改变所属项目,所属类型
	Risk updateRisk(RiskVO riskVO,String step,String id,String project,String riskType,RiskTrack riskTrack);
	
	RiskList getRiskList();
	RiskList getRiskListByProject(String project);
	

}
