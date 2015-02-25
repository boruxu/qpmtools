package cn.edu.buaa.g305.qpm.system.domain;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@Document
@JsonSerialize(include=Inclusion.NON_NULL)
@JsonIgnoreProperties("httpStatus")
public class Project extends ResourceSupportTransientLinks{
	
	private String id;
	@Indexed(unique=true)
	private String name;

	@DBRef
	private Organization organization;
	
	private String description;
	
	@Transient
	protected String error;
	
	@Transient
	protected  HttpStatus httpStatus;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString()
	{
		return "{id:"+id+","+
	           "name:"+name+","+
			   "organization:"+organization+","+
	           "description:"+description+"}";
	}

	public void setErrorOutput(String error, HttpStatus httpStatus) {
		id=null;
		name=null;
		description=null;
		organization=null;
		this.error=error;
		this.httpStatus=httpStatus;
	}
}
