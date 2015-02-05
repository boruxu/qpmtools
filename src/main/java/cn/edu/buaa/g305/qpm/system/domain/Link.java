package cn.edu.buaa.g305.qpm.system.domain;

public class Link {
	//超媒体链接含义
	private String rel;
	//超媒体链接
	private String href;
	
	public Link(){
		
	}
	
    public Link(String rel,String href){
    	
    	this.rel=rel;
    	this.href=href;
		
	}

	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
	@Override
	public String toString(){
		
		String string="{rel:"+rel+","+
						"href:"+href+"}";
		return string;
	}
	
	

}
