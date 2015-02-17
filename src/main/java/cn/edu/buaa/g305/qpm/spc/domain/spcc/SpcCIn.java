package cn.edu.buaa.g305.qpm.spc.domain.spcc;

import java.util.Arrays;

public class SpcCIn {
	
	private String[]x;
	private String[] time;
	
	
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
	
		String spcxmrString="{"+"x:"+Arrays.toString(x)+","+
	                           "time:"+Arrays.toString(time)+","+
	                           "}";
		return spcxmrString;
	}

	public static String format() {
		String format="{x:[],time:[]}";
		return format;
	}
	
	

}
