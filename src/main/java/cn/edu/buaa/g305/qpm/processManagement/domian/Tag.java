package cn.edu.buaa.g305.qpm.processManagement.domian;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import cn.edu.buaa.g305.qpm.system.domain.AbstractDocument;

@Document
@JsonSerialize(include=Inclusion.NON_NULL)
public class Tag extends AbstractDocument {
	
	@Indexed(unique=true)
	private String name;

	private String description;

	@Transient
	private String error;
	
	public  Tag() {
		super();
	}
    public  Tag(String error) {    	
    	this.error=error;		
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
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	

}
