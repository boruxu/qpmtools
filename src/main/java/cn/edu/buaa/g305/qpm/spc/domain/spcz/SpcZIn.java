package cn.edu.buaa.g305.qpm.spc.domain.spcz;

import java.util.Arrays;

public class SpcZIn {
	
	private String[] x;
	private String[] time;
	//机会域
	private String[] a;
	
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
	public String[] getA() {
		return a;
	}
	public void setA(String[] a) {
		this.a = a;
	}
	@Override
	
	public String toString()
	{
	
		String spcuString="{"+"x:"+Arrays.toString(x)+","+
								"a:"+Arrays.toString(a)+","+
	                           "time:"+Arrays.toString(time)+","+
	                           "}";
		return spcuString;
	}

	public static String format() {
		String format="{x:[],a:[],time:[]}";
		return format;
	}
	
	

}
