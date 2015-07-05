package cn.edu.buaa.g305.qpm.spc.domain.spcxmr;

import java.util.Arrays;

public class XmRIn {
	
	private String[]x;
	private String[] time;
	private String sigma=null;
	
	public String[] getX() {
		return x;
	}
	public void setX(String[] x) {
		this.x = x;
	}
	public String getSigma() {
		return sigma;
	}
	public void setSigma(String sigma) {
		this.sigma = sigma;
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
	                           "sigma:"+sigma+"}";
		return spcxmrString;
	}

	public static String format() {
		String format="{x:[],time:[]}";
		return format;
	}

}
