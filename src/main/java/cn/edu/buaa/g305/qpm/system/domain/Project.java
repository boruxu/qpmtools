package cn.edu.buaa.g305.qpm.system.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Project extends AbstractDocument{
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
