package cn.edu.buaa.g305.qpm.spc.controller;

import cn.edu.buaa.g305.qpm.spc.domain.spcxr.SpcXRIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcxs.SpcXSIn;

/**
 * 用于接受前端传来的数据，使用spring MVC-jackson进行数据绑定
 * 为可以绑定所有spc类的数据，创建此vo
 * @author Boru XU
 *
 */
public class SpcVO {
	//公用输入，id在创建spc资源时不需要
	private String id;
	
	private String name;
	
	private String project;
	
	//不同输入有不同的input输入，具体参见具体spc类的format
	//X-R图
	private SpcXRIn inputXR;
	//X-S图
	private SpcXSIn inputXS;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SpcXRIn getInputXR() {
		return inputXR;
	}

	public void setInputXR(SpcXRIn inputXR) {
		this.inputXR = inputXR;
	}

	public SpcXSIn getInputXS() {
		return inputXS;
	}

	public void setInputXS(SpcXSIn inputXS) {
		this.inputXS = inputXS;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
	
	
	
	
}
