package cn.edu.buaa.g305.qpm.correlation.domain;

import cn.edu.buaa.g305.qpm.system.domain.Link;

public class CorrelationInLinks extends CorrelationIn{
	
    public CorrelationInLinks(CorrelationIn correlationIn) {
    	
    	this.id=correlationIn.getId();
    	
    	this.name=correlationIn.name;
    	
    	this.project=correlationIn.project;
    	
    	this.correlationIn=correlationIn.correlationIn;
		
	}
	
	private Link[] links;

	public Link[] getLinks() {
		return links;
	}

	public void setLinks(Link[] links) {
		this.links = links;
	}
	

}
