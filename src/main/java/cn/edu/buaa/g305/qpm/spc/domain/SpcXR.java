package cn.edu.buaa.g305.qpm.spc.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import cn.edu.buaa.g305.qpm.system.domain.AbstractDocument;
import cn.edu.buaa.g305.qpm.system.domain.Link;


@Document
public class SpcXR extends AbstractDocument{
	
	private String organization;
	private String project;



	@Indexed(unique=true)
	private String name;
	private final String type="spcXR";
	private String stauts="computeFinished";
	private SPCXRIn in;
	private SPCXROut out;
	//存储的实体类不继承此field,存储时置空
	private Link[] links;
	private String error;

	public String getOrganization() {
		return organization;
	}


	public void setOrganization(String organization) {
		this.organization = organization;
	}


	public String getProject() {
		return project;
	}


	public void setProject(String project) {
		this.project = project;
	}
	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStauts() {
		return stauts;
	}


	public void setStauts(String stauts) {
		this.stauts = stauts;
	}


	public SPCXRIn getIn() {
		return in;
	}


	public void setIn(SPCXRIn in) {
		this.in = in;
	}


	public SPCXROut getOut() {
		return out;
	}


	public void setOut(SPCXROut out) {
		this.out = out;
	}


	public Link[] getLinks() {
		return links;
	}


	public void setLinks(Link[] links) {
		this.links = links;
	}


	public String getType() {
		return type;
	}


	@Override
	
	public String toString()
	{
		String string="{id:"+id+","+
				       "name:"+ name+","+
				       "type:"+ type+"spcXR"+","+
				       "stauts:"+       stauts+","+
				       "in:" +   in+","+
				       "out:" +   out+","+
				       "links:"+links+"}";
		return string;
		
	}

}
