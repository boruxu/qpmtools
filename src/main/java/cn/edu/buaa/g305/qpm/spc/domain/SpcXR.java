package cn.edu.buaa.g305.qpm.spc.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import cn.edu.buaa.g305.qpm.system.domain.AbstractDocument;
import cn.edu.buaa.g305.qpm.system.domain.Link;


@Document
@JsonSerialize(include=Inclusion.NON_NULL)
public class SpcXR extends ResourceSupport{
	
	@Id
	private String id;
	private String organization;
	private String project;

 
	@Indexed(unique=true)
	private String name;
	private static String type="spcXR";
	private String stauts="computeFinished";
	private SPCXRIn input;
	private SPCXROut output;

	private String error;
	private HttpStatus httpStatus;


	public void setId(String id) {
		this.id = id;
	}

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




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
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

	public String getError() {
		return error;
	}

	



	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setError(String error) {
		this.error = error;
	}




	@Override
	
	public String toString()
	{
		String string="{id:"+id+","+
				       "name:"+ name+","+
				       "type:"+ type+"spcXR"+","+
				       "stauts:"+       stauts+","+
				       "input:" +   input+","+
				       "output:" +   output+"}";
		return string;
		
	}
	
	public static String format()
	{
		String format="{"+
			       "name:'{组织名}.{项目名}.{"+type+"}.{资源名}',"+
			       "input:{" + SPCXRIn.format()+"}}";
		return format;
	}

}
