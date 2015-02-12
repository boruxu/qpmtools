package cn.edu.buaa.g305.qpm.spc.domain.spcxr;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import cn.edu.buaa.g305.qpm.spc.domain.Spc;
import cn.edu.buaa.g305.qpm.system.domain.Project;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;



@Document
@JsonSerialize(include=Inclusion.NON_NULL)
public class SpcXR extends Spc{
	
	@Id
	private String id;
	
	@Indexed(unique=true)
	private String name;
	@DBRef
	private Project project;
	
	private static String type="spcXR";
	private String stauts="computeFinished";
	private SpcXRIn input;
	private SpcXROut output;

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}


	public String getStauts() {
		return stauts;
	}

	public void setStauts(String stauts) {
		this.stauts = stauts;
	}

	public SpcXRIn getInput() {
		return input;
	}

	public void setInput(SpcXRIn input) {
		this.input = input;
	}

	public SpcXROut getOutput() {
		return output;
	}

	public void setOutput(SpcXROut output) {
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
		stauts=null;
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
				       "stauts:"+       stauts+","+
				       "input:" +   input+","+
				       "output:" +   output+","+
				       "project"+project+"}";
		return string;
		
	}
	
	public static String format()
	{
		String format="{"+"createFormat:{"+
			       "name:organizationName.projectName."+type+".name,"+
			       "inputXR:{" + SpcXRIn.format()+"}},"+
			       	"updateFormat:{"	+ 
			       	"name:organizationName.projectName."+type+".name,"+
				    "inputXR:{" + SpcXRIn.format()+"}}}";
		return format;
	}

}
