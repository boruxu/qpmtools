package cn.edu.buaa.g305.qpm.risk.domain;

public class RiskPlanMeasure {
	//计划措施类型
	private String planMeasureType;
	//理由
	private String reason;
	
	//类型为风险缓解时，不使用reason，使用下面描述参数
	
	//风险缓解措施
	private String riskMitigation;
	//缓解措施启动时间
	private String riskMitigationStartTime;
	//风险应急措施
	private String riskEmergencyMeasure;
	//应急措施触发事件
	private String riskEmergencyTrigger;
	public String getPlanMeasureType() {
		return planMeasureType;
	}
	public void setPlanMeasureType(String planMeasureType) {
		this.planMeasureType = planMeasureType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRiskMitigation() {
		return riskMitigation;
	}
	public void setRiskMitigation(String riskMitigation) {
		this.riskMitigation = riskMitigation;
	}
	public String getRiskMitigationStartTime() {
		return riskMitigationStartTime;
	}
	public void setRiskMitigationStartTime(String riskMitigationStartTime) {
		this.riskMitigationStartTime = riskMitigationStartTime;
	}
	public String getRiskEmergencyMeasure() {
		return riskEmergencyMeasure;
	}
	public void setRiskEmergencyMeasure(String riskEmergencyMeasure) {
		this.riskEmergencyMeasure = riskEmergencyMeasure;
	}
	public String getRiskEmergencyTrigger() {
		return riskEmergencyTrigger;
	}
	public void setRiskEmergencyTrigger(String riskEmergencyTrigger) {
		this.riskEmergencyTrigger = riskEmergencyTrigger;
	}

}
