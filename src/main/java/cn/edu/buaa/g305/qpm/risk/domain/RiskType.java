package cn.edu.buaa.g305.qpm.risk.domain;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import cn.edu.buaa.g305.qpm.system.domain.Organization;
import cn.edu.buaa.g305.qpm.system.domain.ResourceSupportTransientLinks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
@Document
@JsonSerialize(include=Inclusion.NON_NULL)
@JsonIgnoreProperties("httpStatus")
public class RiskType extends ResourceSupportTransientLinks{

	private String id;
	@Indexed(unique=true)
	private String name;
	
	private String description;
	private String exsample;
	//风险缓解措施
	private String mitigationMeasure;
	//风险应急措施
	private String emergencyMeasure;
	@DBRef
	private Organization organization;
	
	@Transient
	private String error;
	
	@Transient
	private  HttpStatus httpStatus;
	
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
	public String getExsample() {
		return exsample;
	}
	public void setExsample(String exsample) {
		this.exsample = exsample;
	}
	public String getMitigationMeasure() {
		return mitigationMeasure;
	}
	public void setMitigationMeasure(String mitigationMeasure) {
		this.mitigationMeasure = mitigationMeasure;
	}
	public String getEmergencyMeasure() {
		return emergencyMeasure;
	}
	public void setEmergencyMeasure(String emergencyMeasure) {
		this.emergencyMeasure = emergencyMeasure;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
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
	public void setErrorOutput(String error, HttpStatus httpStatus) {
		id=null;
		name=null;
		description=null;
		mitigationMeasure=null;
		emergencyMeasure=null;
		this.error=error;
		this.httpStatus=httpStatus;
	}
	
}
