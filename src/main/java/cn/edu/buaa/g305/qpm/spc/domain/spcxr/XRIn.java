package cn.edu.buaa.g305.qpm.spc.domain.spcxr;

import java.util.Arrays;

import cn.edu.buaa.g305.qpm.spc.domain.AbnormalPoint;


public class XRIn {
	
	private String[][] x;
	private String[] time;
	private String xName;
	private String timeName;
	private final String rName="极差";
	
	private AbnormalPoint abnormalPoint;
		
	public AbnormalPoint getAbnormalPoint() {
		return abnormalPoint;
	}
	public void setAbnormalPoint(AbnormalPoint abnormalPoint) {
		this.abnormalPoint = abnormalPoint;
	}
	public String getrName() {
		return rName;
	}
	public String getxName() {
		return xName;
	}
	public void setxName(String xName) {
		this.xName = xName;
	}
	public String getTimeName() {
		return timeName;
	}
	public void setTimeName(String timeName) {
		this.timeName = timeName;
	}
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
		return spcxrString;
	}

	public static String format() {
		String format="{x:[[],[],[]...],time:[]}";
		return format;
	}
	
	
	
	
}
