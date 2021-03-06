package cn.edu.buaa.g305.qpm.spc.controller;

import cn.edu.buaa.g305.qpm.spc.domain.spcc.CIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcu.UIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcxmr.XmRIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.XRIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcxs.XSIn;

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
	private XRIn inputXR;
	//X-S图
	private XSIn inputXS;
	//XMR图
	private XmRIn inputXmR;
	//C图
	private CIn inputC;
	//U、Z图
	private UIn inputU;
    private UIn inputZ;
  
	public UIn getInputZ() {
		return inputZ;
	}

	public void setInputZ(UIn inputZ) {
		this.inputZ = inputZ;
	}

	public XmRIn getInputXmR() {
		return inputXmR;
	}

	public void setInputXmR(XmRIn inputXmR) {
		this.inputXmR = inputXmR;
	}

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

	public XRIn getInputXR() {
		return inputXR;
	}

	public void setInputXR(XRIn inputXR) {
		this.inputXR = inputXR;
	}

	public XSIn getInputXS() {
		return inputXS;
	}

	public void setInputXS(XSIn inputXS) {
		this.inputXS = inputXS;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public CIn getInputC() {
		return inputC;
	}

	public void setInputC(CIn inputC) {
		this.inputC = inputC;
	}

	public UIn getInputU() {
		return inputU;
	}

	public void setInputU(UIn inputU) {
		this.inputU = inputU;
	}
	
	
}
