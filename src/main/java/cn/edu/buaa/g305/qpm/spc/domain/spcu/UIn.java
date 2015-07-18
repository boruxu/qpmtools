package cn.edu.buaa.g305.qpm.spc.domain.spcu;

import java.util.Arrays;

import cn.edu.buaa.g305.qpm.spc.domain.AbnormalPoint;

public class UIn {
	
	private String[] x;
	private String[] time;
	//机会域
	private String[] a;
	private String xName;
	private String timeName;
	private String aName;
	private AbnormalPoint abnormalPoint;	
	
    public AbnormalPoint getAbnormalPoint() {
		return abnormalPoint;
	}



	public void setAbnormalPoint(AbnormalPoint abnormalPoint) {
		this.abnormalPoint = abnormalPoint;
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
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
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
