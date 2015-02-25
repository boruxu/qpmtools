package cn.edu.buaa.g305.qpm.system.domain;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL)
@JsonIgnoreProperties("httpStatus")
public class OrganizationList extends ResourceSupportTransientLinks{

	private List<Organization> list;
	
	private String error;

	private  HttpStatus httpStatus;

	public List<Organization> getList() {
		return list;
	}

	public void setList(List<Organization> list) {
		this.list = list;
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
	
}
