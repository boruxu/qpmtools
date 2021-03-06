package cn.edu.buaa.g305.qpm.spc.domain.spcc;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import cn.edu.buaa.g305.qpm.spc.domain.PlotType;
import cn.edu.buaa.g305.qpm.spc.domain.Spc;
import cn.edu.buaa.g305.qpm.system.domain.Project;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@Document
@JsonSerialize(include=Inclusion.NON_NULL)
public class C extends Spc{
	

	@DBRef
	private Project project;
	
	//get方法不要设置成static，方便其他包取出type数据
	private static String type=PlotType.C.name();

	private CIn input;
	private COut output;
	
	
	public String getType() {
		return type;
	}

	public static void setType(String type) {
		C.type = type;
	}

	public CIn getInput() {
		return input;
	}

	public void setInput(CIn input) {
		this.input = input;
	}

	public COut getOutput() {
		return output;
	}

	public void setOutput(COut output) {
		this.output = output;
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
				       "stauts:"+
				       "input:" +   input+","+
				       "output:" +   output+","+
				       "project"+project+"}";
		return string;
		
	}
	
	public static String format()
	{
		String format="{"+"createFormat:{"+
			       "name:organizationName.projectName."+type+".name,"+
			       "inputC:{" + C.format()+"}},"+
			       	"updateFormat:{"	+ 
			       	"name:organizationName.projectName."+type+".name,"+
				    "inputC:{" + C.format()+"}}}";
		return format;
	}


}
