package cn.edu.buaa.g305.qpm.correlation.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import cn.edu.buaa.g305.qpm.system.domain.AbstractDocument;
import cn.edu.buaa.g305.qpm.system.domain.Project;

@Document
public class CorrelationIn extends AbstractDocument {
	
	//所属项目的名字，存储时检查表中项目名，若无赋值为“无归属项目”
	private String project;
	//名唯一
	@Indexed(unique=true)
	private String name;
	
	private CorrelationInXYArray[] correlationIn;
	

	
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public CorrelationInXYArray[] getCorrelationIn() {
		return correlationIn;
	}

	public void setCorrelationIn(CorrelationInXYArray[] correlationIn) {
		this.correlationIn = correlationIn;
	}

}
