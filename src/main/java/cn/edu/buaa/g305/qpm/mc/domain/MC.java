package cn.edu.buaa.g305.qpm.mc.domain;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import cn.edu.buaa.g305.qpm.system.domain.AbstractDocument;
import cn.edu.buaa.g305.qpm.system.domain.Project;

@Document
@JsonSerialize(include=Inclusion.NON_NULL)
public class MC extends AbstractDocument {
	
	@Indexed(unique=true)
	private String name;
	@DBRef
	private Project project;
	
	private MCParam mcParam;
	
	@Transient
	private String error;
	
	public  MC() {
		super();
	}
    public  MC(String error) {    	
    	this.error=error;		
	}
	
	//产生的随机数不存入数据库
	@Transient
	private Double[] result;

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

	public MCParam getMcParam() {
		return mcParam;
	}

	public void setMcParam(MCParam mcParam) {
		this.mcParam = mcParam;
	}

	public Double[] getResult() {
		return result;
	}
	public void setResult(Double[] result) {
		this.result = result;
	}
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	

}
