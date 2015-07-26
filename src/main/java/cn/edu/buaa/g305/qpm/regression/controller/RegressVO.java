package cn.edu.buaa.g305.qpm.regression.controller;

import cn.edu.buaa.g305.qpm.regression.domain.RegressIn;

public class RegressVO {
	
    private String id;
	
	private String name;
	
	private String project;
	
	private RegressIn input;

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

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public RegressIn getInput() {
		return input;
	}

	public void setInput(RegressIn input) {
		this.input = input;
	}
	
}
