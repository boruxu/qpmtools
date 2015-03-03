package cn.edu.buaa.g305.qpm.risk.domain;

public class RiskApproval {
	
	//审批状态
	private String riskApprovalState;
	//审批负责人
	private String riskApprovalMan;
	//审批时间
	private String riskApprovalTime;
	//审批结果
	private String riskApprovalResult;
	//未通过理由
	private String riskNotApprovalReason;
	
	
	public String getRiskApprovalState() {
		return riskApprovalState;
	}
	public void setRiskApprovalState(String riskApprovalState) {
		this.riskApprovalState = riskApprovalState;
	}
	public String getRiskApprovalMan() {
		return riskApprovalMan;
	}
	public void setRiskApprovalMan(String riskApprovalMan) {
		this.riskApprovalMan = riskApprovalMan;
	}
	public String getRiskApprovalTime() {
		return riskApprovalTime;
	}
	public void setRiskApprovalTime(String riskApprovalTime) {
		this.riskApprovalTime = riskApprovalTime;
	}
	public String getRiskApprovalResult() {
		return riskApprovalResult;
	}
	public void setRiskApprovalResult(String riskApprovalResult) {
		this.riskApprovalResult = riskApprovalResult;
	}
	public String getRiskNotApprovalReason() {
		return riskNotApprovalReason;
	}
	public void setRiskNotApprovalReason(String riskNotApprovalReason) {
		this.riskNotApprovalReason = riskNotApprovalReason;
	}
	
	
}
