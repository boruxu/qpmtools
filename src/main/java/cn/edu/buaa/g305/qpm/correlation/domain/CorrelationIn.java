package cn.edu.buaa.g305.qpm.correlation.domain;

import java.util.Arrays;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import cn.edu.buaa.g305.qpm.system.domain.AbstractDocument;


@Document
public class CorrelationIn extends AbstractDocument {
	
	//所属项目的名字，存储时检查表中项目名，若无赋值为“无归属项目”
	protected String project;
	//名唯一
	@Indexed(unique=true)
	protected String name;
	
	protected CorrelationInXYArray[] correlationIn;
	

	@Override
	public String toString()
	{
		String correlationInString="";
		correlationInString="{"+"\"_id:\""+id+","+
		                        "\"project:\""+project+","+
				                "\"name\":"+name+","+
		                        "\"correlationIn\":"+Arrays.toString(correlationIn)+"}";
		return correlationInString;
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


	public CorrelationInXYArray[] getCorrelationIn() {
		return correlationIn;
	}

	public void setCorrelationIn(CorrelationInXYArray[] correlationIn) {
		this.correlationIn = correlationIn;
	}

}
