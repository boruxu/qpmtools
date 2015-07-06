package cn.edu.buaa.g305.qpm.risk.controller;

import cn.edu.buaa.g305.qpm.risk.domain.RiskApproval;
import cn.edu.buaa.g305.qpm.risk.domain.RiskPlanMeasure;
import cn.edu.buaa.g305.qpm.risk.domain.RiskTrack;

public class RiskVO {
	
	private String name;
	//风险项所属项目
	private String project;
	//风险类型
	private String riskType;
	//风险陈述
	private String riskInfo;
	//风险状态
	private String riskState;
	//风险来源
	private String riskSource;
	//风险情况
	private String riskContex;
	//风险征兆
	private String riskWarning;
	//潜在影响
	private String riskPotentialInfluence;
	//发生条件
	private String riskCondition;
	//可 能 性
	private String riskPosibility;
	//严 重 性
	private String riskDamage;
	//紧 迫 性
	private String riskUrgency;
	//优 先 级
	private String riskPriority;
	//建议措施
	private String riskProposeMeasure;

	//计划措施
	private RiskPlanMeasure riskPlanMeasure; 
	
	
	private RiskApproval riskApproval;
	
	private String riskRemark;
	
	//风险执行情况
    private String riskPlanImplementation;
	
	//跟踪信息，只可记录，不可修改
	private RiskTrack riskTrack;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getRiskInfo() {
		return riskInfo;
	}

	public void setRiskInfo(String riskInfo) {
		this.riskInfo = riskInfo;
	}

	public String getRiskState() {
		return riskState;
	}

	public void setRiskState(String riskState) {
		this.riskState = riskState;
	}

	public String getRiskSource() {
		return riskSource;
	}

	public void setRiskSource(String riskSource) {
		this.riskSource = riskSource;
	}

	public String getRiskContex() {
		return riskContex;
	}

	public void setRiskContex(String riskContex) {
		this.riskContex = riskContex;
	}

	public String getRiskWarning() {
		return riskWarning;
	}

	public void setRiskWarning(String riskWarning) {
		this.riskWarning = riskWarning;
	}

	public String getRiskPotentialInfluence() {
		return riskPotentialInfluence;
	}

	public void setRiskPotentialInfluence(String riskPotentialInfluence) {
		this.riskPotentialInfluence = riskPotentialInfluence;
	}

	public String getRiskCondition() {
		return riskCondition;
	}

	public void setRiskCondition(String riskCondition) {
		this.riskCondition = riskCondition;
	}

	public String getRiskPosibility() {
		return riskPosibility;
	}

	public void setRiskPosibility(String riskPosibility) {
		this.riskPosibility = riskPosibility;
	}

	public String getRiskDamage() {
		return riskDamage;
	}

	public void setRiskDamage(String riskDamage) {
		this.riskDamage = riskDamage;
	}

	public String getRiskUrgency() {
		return riskUrgency;
	}

	public void setRiskUrgency(String riskUrgency) {
		this.riskUrgency = riskUrgency;
	}

	public String getRiskPriority() {
		return riskPriority;
	}

	public void setRiskPriority(String riskPriority) {
		this.riskPriority = riskPriority;
	}

	public String getRiskProposeMeasure() {
		return riskProposeMeasure;
	}

	public void setRiskProposeMeasure(String riskProposeMeasure) {
		this.riskProposeMeasure = riskProposeMeasure;
	}

	public RiskPlanMeasure getRiskPlanMeasure() {
		return riskPlanMeasure;
	}

	public void setRiskPlanMeasure(RiskPlanMeasure riskPlanMeasure) {
		this.riskPlanMeasure = riskPlanMeasure;
	}


	public RiskApproval getRiskApproval() {
		return riskApproval;
	}

	public void setRiskApproval(RiskApproval riskApproval) {
		this.riskApproval = riskApproval;
	}

	public String getRiskRemark() {
		return riskRemark;
	}

	public void setRiskRemark(String riskRemark) {
		this.riskRemark = riskRemark;
	}

	public RiskTrack getRiskTrack() {
		return riskTrack;
	}

	public void setRiskTrack(RiskTrack riskTrack) {
		this.riskTrack = riskTrack;
	}

	public String getRiskPlanImplementation() {
		return riskPlanImplementation;
	}

	public void setRiskPlanImplementation(String riskPlanImplementation) {
		this.riskPlanImplementation = riskPlanImplementation;
	}
	
	

	
	
}
