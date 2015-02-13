package cn.edu.buaa.g305.qpm.spc.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cn.edu.buaa.g305.qpm.system.domain.ResourceSupportTransientLinks;

@JsonIgnoreProperties("httpStatus")
public class Spc extends ResourceSupportTransientLinks{
	
	//spc数据基类，用于controller层统一简化方法
	@Id
	protected String id;
	
	@Transient
	protected String error;
	
	@Transient
	protected  HttpStatus httpStatus;
	
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
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}



}
