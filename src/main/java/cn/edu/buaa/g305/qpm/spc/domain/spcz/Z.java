package cn.edu.buaa.g305.qpm.spc.domain.spcz;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import cn.edu.buaa.g305.qpm.spc.domain.PlotType;
import cn.edu.buaa.g305.qpm.spc.domain.Spc;
import cn.edu.buaa.g305.qpm.spc.domain.spcu.UIn;
import cn.edu.buaa.g305.qpm.system.domain.Project;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@Document
@JsonSerialize(include=Inclusion.NON_NULL)
public class Z extends Spc{

	@DBRef
	private Project project;
	
	private static String type=PlotType.Z.name();
	//Z的输入与U完全一致，应该使用UIn，以保证U改动后，z也改动
	private UIn input;
	private ZOut output;

	public String getType() {
		return type;
	}	
	public UIn getInput() {
		return input;
	}
	public void setInput(UIn input) {
		this.input = input;
	}



	public ZOut getOutput() {
		return output;
	}

	public void setOutput(ZOut output) {
		this.output = output;
	}

	public static void setType(String type) {
		Z.type = type;
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
			       "inputZ:{" + UIn.format()+"}},"+
			       	"updateFormat:{"	+ 
			       	"name:organizationName.projectName."+type+".name,"+
				    "inputZ:{" + UIn.format()+"}}}";
		return format;
	}


}
