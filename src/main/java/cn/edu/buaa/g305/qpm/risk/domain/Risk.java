package cn.edu.buaa.g305.qpm.risk.domain;

import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import cn.edu.buaa.g305.qpm.system.domain.Project;
import cn.edu.buaa.g305.qpm.system.domain.ResourceSupportTransientLinks;
@Document
@JsonSerialize(include=Inclusion.NON_NULL)
@JsonIgnoreProperties("httpStatus")
public class Risk extends ResourceSupportTransientLinks{

	private String id;
	//风险项名
	@Indexed(unique=true)
	private String name;
	//风险项所属项目
	@DBRef
	private Project project;
	@DBRef
	//风险类型
	private RiskType riskType;
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
	//风险执行情况
	private String riskPlanImplementation;

	//计划措施
	private RiskPlanMeasure riskPlanMeasure; 

	
	private RiskApproval riskApproval;
	
	private String riskRemark;
	
	//跟踪信息，只可记录，不可修改
	private List<RiskTrack> riskTrack;
	
	
	//错误处理与状态码部分，不持久化
	@Transient
	private String error;
	
	@Transient
	private  HttpStatus httpStatus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	

	public RiskType getRiskType() {
		return riskType;
	}

	public void setRiskType(RiskType riskType) {
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

	

	public List<RiskTrack> getRiskTrack() {
		return riskTrack;
	}

	public void setRiskTrack(List<RiskTrack> riskTrack) {
		this.riskTrack = riskTrack;
	}

	public RiskPlanMeasure getRiskPlanMeasure() {
		return riskPlanMeasure;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getRiskPlanImplementation() {
		return riskPlanImplementation;
	}

	public void setRiskPlanImplementation(String riskPlanImplementation) {
		this.riskPlanImplementation = riskPlanImplementation;
	}

	public void setErrorOutput(String error, HttpStatus httpStatus) {
		id=null;
		name=null;
		project=null;
		riskType=null;
		riskInfo=null;
		riskState=null;
		riskSource=null;
		riskContex=null;
		riskWarning=null;
		riskPotentialInfluence=null;
		riskCondition=null;
		riskPosibility=null;
		riskDamage=null;
		riskUrgency=null;
		riskPriority=null;
		riskProposeMeasure=null;
		riskPlanMeasure=null; 
		riskPlanImplementation=null;


		riskApproval=null;
		riskRemark=null;
		riskTrack=null;
		
		this.error=error;
		this.httpStatus=httpStatus;
		
	}  
	
}
