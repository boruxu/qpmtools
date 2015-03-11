package cn.edu.buaa.g305.qpm.risk.domain;

public class RiskPlanMeasure {
	
	//计划负责人
	private String riskPlanMan;
	//计划时间
	private String riskPlanTime;
	//计划措施类型
	private String planMeasureType;
	//理由
	private String remark;
	
	//类型为风险缓解时，不使用reason，使用下面描述参数
	
	//风险缓解措施
	private String riskMitigationMeasure;
	//缓解措施启动时间
	private String riskMitigationMeasureStartTime;
	//风险应急措施
	private String riskEmergencyMeasure;
	//应急措施触发事件
	private String riskEmergencyTrigger;
	
	//风险指示器
	private String riskIndicator;
	//指示器阀值
	private String riskIndicatorThresholdValue;
	
	
	
	public String getRiskIndicator() {
		return riskIndicator;
	}
	public void setRiskIndicator(String riskIndicator) {
		this.riskIndicator = riskIndicator;
	}
	public String getRiskIndicatorThresholdValue() {
		return riskIndicatorThresholdValue;
	}
	public void setRiskIndicatorThresholdValue(String riskIndicatorThresholdValue) {
		this.riskIndicatorThresholdValue = riskIndicatorThresholdValue;
	}
	public String getRiskPlanMan() {
		return riskPlanMan;
	}
	public void setRiskPlanMan(String riskPlanMan) {
		this.riskPlanMan = riskPlanMan;
	}
	public String getRiskPlanTime() {
		return riskPlanTime;
	}
	public void setRiskPlanTime(String riskPlanTime) {
		this.riskPlanTime = riskPlanTime;
	}
	public String getPlanMeasureType() {
		return planMeasureType;
	}
	public void setPlanMeasureType(String planMeasureType) {
		this.planMeasureType = planMeasureType;
	}

	
	public String getRiskMitigationMeasure() {
		return riskMitigationMeasure;
	}
	public void setRiskMitigationMeasure(String riskMitigationMeasure) {
		this.riskMitigationMeasure = riskMitigationMeasure;
	}
	public String getRiskMitigationMeasureStartTime() {
		return riskMitigationMeasureStartTime;
	}
	public void setRiskMitigationMeasureStartTime(
			String riskMitigationMeasureStartTime) {
		this.riskMitigationMeasureStartTime = riskMitigationMeasureStartTime;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
