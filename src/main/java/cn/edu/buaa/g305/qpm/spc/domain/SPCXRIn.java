package cn.edu.buaa.g305.qpm.spc.domain;

import java.util.Arrays;


public class SPCXRIn {
	
	private String[][] x;
	private String[] time;
/*	private Link[] links;
	
	public Link[] getLinks() {
		return links;
	}
	public void setLinks(Link[] links) {
		this.links = links;
	}*/
	public String[][] getX() {
		return x;
	}
	public void setX(String[][] x) {
		this.x = x;
	}
	public String[] getTime() {
		return time;
	}
	public void setTime(String[] time) {
		this.time = time;
	}
	
	@Override
	
	public String toString()
	{
		String xString="[";
		for (int i=0;i<x.length-1;i++) {
			xString+=Arrays.toString(x[i])+",";
		}
		xString+=Arrays.toString(x[x.length-1])+"]";
		String spcxrString="{"+"x:"+xString+","+
	                           "time:"+Arrays.toString(time)+","+"}";
				              // "links:"+Arrays.toString(links)+"}";
		return spcxrString;
	}
	
	
	
	
}
