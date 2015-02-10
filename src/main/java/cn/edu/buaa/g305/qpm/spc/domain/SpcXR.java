package cn.edu.buaa.g305.qpm.spc.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;



@Document
@JsonSerialize(include=Inclusion.NON_NULL)
public class SpcXR extends Spc{
	
	@Id
	private String id;
	
	@Indexed(unique=true)
	private String name;
	private static String type="spcXR";
	private String stauts="computeFinished";
	private SPCXRIn input;
	private SPCXROut output;

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

	public SPCXRIn getInput() {
		return input;
	}

	public void setInput(SPCXRIn input) {
		this.input = input;
	}

	public SPCXROut getOutput() {
		return output;
	}

	public void setOutput(SPCXROut output) {
		this.output = output;
	}
	//设置出错输出格式，不输出的设为空，jackson不输出
	public void setErrorOutput(String error,HttpStatus httpStatus) {
		
		id=null;
		name=null;
		stauts=null;
		input=null;
		output=null;
		this.error=error;
		this.httpStatus=httpStatus;
	}

	@Override
	
	public String toString()
	{
		String string="{id:"+id+","+
				       "name:"+ name+","+
				       "type:"+ type+"spcXR"+","+
				       "stauts:"+       stauts+","+
				       "input:" +   input+","+
				       "output:" +   output+"}";
		return string;
		
	}
	
	public static String format()
	{
		String format="{"+
			       "name:organizationName.projectName."+type+".name,"+
			       "input:{" + SPCXRIn.format()+"}}";
		return format;
	}

}
