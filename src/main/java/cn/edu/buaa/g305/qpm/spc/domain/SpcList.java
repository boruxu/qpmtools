package cn.edu.buaa.g305.qpm.spc.domain;

import java.util.List;
import org.springframework.http.HttpStatus;
import cn.edu.buaa.g305.qpm.system.domain.ResourceSupportTransientLinks;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL)
@JsonIgnoreProperties("httpStatus")
public class SpcList extends ResourceSupportTransientLinks {
	
	private String plotType;
	
	private List<? extends Spc> Lists;
	
	private String error;

	private  HttpStatus httpStatus;
	
	public List<? extends Spc> getLists() {
		return Lists;
	}

	public void setLists(List<? extends Spc> lists) {
		Lists = lists;
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

	public String getPlotType() {
		return plotType;
	}

	public void setPlotType(String plotType) {
		this.plotType = plotType;
	}

}
