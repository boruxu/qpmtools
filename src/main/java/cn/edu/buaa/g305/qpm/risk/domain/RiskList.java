package cn.edu.buaa.g305.qpm.risk.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import cn.edu.buaa.g305.qpm.system.domain.ResourceSupportTransientLinks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@Document
@JsonSerialize(include=Inclusion.NON_NULL)
@JsonIgnoreProperties("httpStatus")
public class RiskList extends ResourceSupportTransientLinks {
	
	    //列表所属项目名，全部为“全部”
		private String project;
		
		private List<Risk> list;
		
		private String error;

		private  HttpStatus httpStatus;

		public String getProject() {
			return project;
		}

		public void setProject(String project) {
			this.project = project;
		}

		public List<Risk> getList() {
			return list;
		}

		public void setList(List<Risk> list) {
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
