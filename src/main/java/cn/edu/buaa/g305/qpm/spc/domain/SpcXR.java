package cn.edu.buaa.g305.qpm.spc.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import cn.edu.buaa.g305.qpm.system.domain.AbstractDocument;
import cn.edu.buaa.g305.qpm.system.domain.Link;


@Document
@JsonSerialize(include=Inclusion.NON_NULL)
public class SpcXR extends AbstractDocument{
	
	private String organization;
	private String project;



	@Indexed(unique=true)
	private String name;
	private String type="spcXR";
	private String stauts="computeFinished";
	private SPCXRIn input;
	private SPCXROut output;
	//存储的实体类不继承此field,存储时置空
	private Link[] links;
	private String error;

	public String getOrganization() {
		return organization;
	}


	public void setOrganization(String organization) {
		this.organization = organization;
	}


	public String getProject() {
		return project;
	}


	public void setProject(String project) {
		this.project = project;
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


	public String getStauts() {
		return stauts;
	}


	public void setStauts(String stauts) {
		this.stauts = stauts;
	}


	

	public SPCXRIn getInput() {
		return input;
	}


	public void setInput(SPCXRIn input) {
		this.input = input;
	}


	public SPCXROut getOutput() {
		return output;
	}


	public void setOutput(SPCXROut output) {
		this.output = output;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Link[] getLinks() {
		return links;
	}


	public void setLinks(Link[] links) {
		this.links = links;
	}


	public String getType() {
		return type;
	}


	@Override
	
	public String toString()
	{
		String string="{id:"+id+","+
				       "name:"+ name+","+
				       "type:"+ type+"spcXR"+","+
				       "stauts:"+       stauts+","+
				       "in:" +   input+","+
				       "out:" +   output+","+
				       "links:"+links+"}";
		return string;
		
	}

}
