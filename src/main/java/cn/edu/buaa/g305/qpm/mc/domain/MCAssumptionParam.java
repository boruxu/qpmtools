package cn.edu.buaa.g305.qpm.mc.domain;

import java.util.List;

public class MCAssumptionParam {

	//假设变量类型
	private String type;
	//假设变量名 符号，例如 x或y
	private String name;
	//变量符号解释 例如，x是什么
    private String nameRemark;
	public String getNameRemark() {
		return nameRemark;
	}
	public void setNameRemark(String nameRemark) {
		this.nameRemark = nameRemark;
	}


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
