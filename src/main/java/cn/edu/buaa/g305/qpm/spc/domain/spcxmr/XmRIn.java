package cn.edu.buaa.g305.qpm.spc.domain.spcxmr;

import java.util.Arrays;

import cn.edu.buaa.g305.qpm.spc.domain.AbnormalPoint;

public class XmRIn {
	
	private String[]x;
	private String[] time;
	private String sigma=null;	
	private String xName;
	private String timeName;
	private final String mrName="移动极差";
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
	public String getMrName() {
		return mrName;
	}
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
