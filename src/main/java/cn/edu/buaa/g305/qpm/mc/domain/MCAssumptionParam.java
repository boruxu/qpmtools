package cn.edu.buaa.g305.qpm.mc.domain;

import java.util.List;

public class MCAssumptionParam {

	private String type;
	private String name;
	private List<Double> distributionParam;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public List<Double> getDistributionParam() {
		return distributionParam;
	}


	public void setDistributionParam(List<Double> distributionParam) {
		this.distributionParam = distributionParam;
	}

}
