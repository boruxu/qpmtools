package cn.edu.buaa.g305.qpm.processManagement.domian;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import cn.edu.buaa.g305.qpm.system.domain.AbstractDocument;

@Document
public class Activity extends AbstractDocument{
	
	//product名
	private String[] input;
	private String[] outputList;
	//副本，内联文档
	private String tag;
	@Indexed(unique=true)
	private String name;
	//活动的简要描述
	private String description;
	
	//保存流程图的
	private FlowChart flowChart;
	
	private String fileName;

	public String[] getInput() {
		return input;
	}

	public void setInput(String[] input) {
		this.input = input;
	}

	public String[] getOutputList() {
		return outputList;
	}

	public void setOutputList(String[] outputList) {
		this.outputList = outputList;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public FlowChart getFlowChart() {
		return flowChart;
	}

	public void setFlowChart(FlowChart flowChart) {
		this.flowChart = flowChart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
