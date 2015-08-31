package cn.edu.buaa.g305.qpm.processManagement.domian;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@Document
@JsonSerialize(include=Inclusion.NON_NULL)
public class FlowChart {
	
	private String html;
	private Connection[][] connection;
	
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public Connection[][] getConnection() {
		return connection;
	}
	public void setConnection(Connection[][] connection) {
		this.connection = connection;
	}
			
}


class Connection{
	//html 节点的id
	private String id;
	//链接的anchor类型
	private String type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
