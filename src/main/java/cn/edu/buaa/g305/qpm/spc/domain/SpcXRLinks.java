package cn.edu.buaa.g305.qpm.spc.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import cn.edu.buaa.g305.qpm.system.domain.AbstractDocument;
import cn.edu.buaa.g305.qpm.system.domain.Link;

public class SpcXRLinks extends AbstractDocument{
	
	protected String organization;
	protected String project;
	@Indexed(unique=true)
	protected String name;
	protected final String type="spcXR";
	protected String stauts="computeFinished";
	protected SPCXRIn in;
	protected SPCXROut out;
	//存储的实体类不继承此field
	private Link[] links;

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
	

}
