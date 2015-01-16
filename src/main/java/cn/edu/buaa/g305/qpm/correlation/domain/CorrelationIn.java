package cn.edu.buaa.g305.qpm.correlation.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import cn.edu.buaa.g305.qpm.system.domain.AbstractDocument;
import cn.edu.buaa.g305.qpm.system.domain.Project;

@Document
public class CorrelationIn extends AbstractDocument {
	
	@DBRef
	private Project project;
	
	private CorrelationInXYArray[] correlationIn;
	
	

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public CorrelationInXYArray[] getCorrelationIn() {
		return correlationIn;
	}

	public void setCorrelationIn(CorrelationInXYArray[] correlationIn) {
		this.correlationIn = correlationIn;
	}

}
