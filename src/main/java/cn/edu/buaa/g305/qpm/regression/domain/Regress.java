package cn.edu.buaa.g305.qpm.regression.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import cn.edu.buaa.g305.qpm.system.domain.Project;

public class Regress {
	
	@Id
	private String id;
	
	@Transient
	private String error;
	
	@Indexed(unique=true)
	private String name;
	
	@DBRef
	private Project project;
	
	private RegressIn input;
	
	private RegressOut output;
	
	public Regress() {
		super();
	}
	public Regress(String error) {
		this.error=error;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
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
	public RegressIn getInput() {
		return input;
	}
	public void setInput(RegressIn input) {
		this.input = input;
	}
	public RegressOut getOutput() {
		return output;
	}
	public void setOutput(RegressOut output) {
		this.output = output;
	}
	
	@Override
	public String toString(){
		return "输入：\n"+
				input+"\n"+
				"输出：\n"+
				output;
	}
}
