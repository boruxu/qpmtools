package cn.edu.buaa.g305.qpm.mc.domain;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL)
public class MCParam {
	
		//mc参数的预测变量名和公式
		private String  predictionName;
		private String  predictionValue;
		//置信区间
		private double  confidenceInterval; 
		//模拟次数 
		private int simulationNumber;
		//mc参数的假设变量
		private List<MCAssumptionParam> mcAssumptionParamList;
		//mc参数的常量
		private List<MCNormalParam> mcNormalParamList;
		
		private String remark;

		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getPredictionName() {
			return predictionName;
		}
		public void setPredictionName(String predictionName) {
			this.predictionName = predictionName;
		}
		public String getPredictionValue() {
			return predictionValue;
		}
		public void setPredictionValue(String predictionValue) {
			this.predictionValue = predictionValue;
		}
		public double getConfidenceInterval() {
			return confidenceInterval;
		}
		public void setConfidenceInterval(double confidenceInterval) {
			this.confidenceInterval = confidenceInterval;
		}
		public int getSimulationNumber() {
			return simulationNumber;
		}
		public void setSimulationNumber(int simulationNumber) {
			this.simulationNumber = simulationNumber;
		}
		public List<MCAssumptionParam> getMcAssumptionParamList() {
			return mcAssumptionParamList;
		}
		public void setMcAssumptionParamList(
				List<MCAssumptionParam> mcAssumptionParamList) {
			this.mcAssumptionParamList = mcAssumptionParamList;
		}
		public List<MCNormalParam> getMcNormalParamList() {
			return mcNormalParamList;
		}
		public void setMcNormalParamList(List<MCNormalParam> mcNormalParamList) {
			this.mcNormalParamList = mcNormalParamList;
		}

}
