package cn.edu.buaa.g305.qpm.spc.domain.spcxs;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import cn.edu.buaa.g305.qpm.spc.domain.PlotType;
import cn.edu.buaa.g305.qpm.spc.domain.Spc;
import cn.edu.buaa.g305.qpm.system.domain.Project;

@Document
@JsonSerialize(include=Inclusion.NON_NULL)
public class XS extends Spc{

	@DBRef
	private Project project;
	private static String type=PlotType.XS.name();
	private XSIn input;
	private XSOut output;

	public String getType() {
		return type;
	}
	
	public XSIn getInput() {
		return input;
	}

	public void setInput(XSIn input) {
		this.input = input;
	}

	public XSOut getOutput() {
		return output;
	}

	public void setOutput(XSOut output) {
		this.output = output;
	}

	public static void setType(String type) {
		XS.type = type;
	}
	

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	//设置出错输出格式，不输出的设为空，jackson不输出
	public void setErrorOutput(String error,HttpStatus httpStatus) {
		
		id=null;
		name=null;
		input=null;
		output=null;
		project=null;
		this.error=error;
		this.httpStatus=httpStatus;
	}

	@Override
	
	public String toString()
	{
		String string="{id:"+id+","+
				       "name:"+ name+","+
				       "type:"+ type+","+
				       "input:" +   input+","+
				       "output:" +   output+","+
				       "project"+project+"}";
		return string;
		
	}
	
	public static String format()
	{
		String format="{"+"createFormat:{"+
			       "name:organizationName.projectName."+type+".name,"+
			       "inputXS:{" + XSIn.format()+"}},"+
			       	"updateFormat:{"	+ 
			       	"name:organizationName.projectName."+type+".name,"+
				    "inputXS:{" + XSIn.format()+"}}}";
		return format;
	}

}
