package cn.edu.buaa.g305.qpm.system.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@Document
@JsonSerialize(include=Inclusion.NON_NULL)
public class Project extends AbstractDocument{
	
	@Indexed(unique=true)
	private String name;

	@DBRef
	private Organization organization;
	
	private String description;
	


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
	
	@Override
	public String toString()
	{
		return "{id:"+id+","+
	           "name:"+name+","+
			   "organization:"+organization+","+
	           "description:"+description+"}";
	}
}
