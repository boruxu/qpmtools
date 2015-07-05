package cn.edu.buaa.g305.qpm.spc.domain.spcc;

import java.util.Arrays;

public class CIn {
	
	private String[]x;
	private String xName;
	private String[] time;
	private String timeName;
	
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
	public String[] getX() {
		return x;
	}
	public void setX(String[] x) {
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
	
		String spcuString="{"+"x:"+Arrays.toString(x)+","+
	                           "time:"+Arrays.toString(time)+","+
	                           "}";
		return spcuString;
	}

	public static String format() {
		String format="{x:[],time:[]}";
		return format;
	}
	
	

}
