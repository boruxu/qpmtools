package cn.edu.buaa.g305.qpm.spc.domain.spcz;

import java.util.Arrays;

import cn.edu.buaa.g305.qpm.spc.domain.AbnormalPoint;


public class ZOut {
	
	private String[] x;
	private String[] time;
	
	private AbnormalPoint abnormalPoint;	
	
    public AbnormalPoint getAbnormalPoint() {
		return abnormalPoint;
	}



	public void setAbnormalPoint(AbnormalPoint abnormalPoint) {
		this.abnormalPoint = abnormalPoint;
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
	                           "time:"+Arrays.toString(time)+"}";
		return spcuString;
	}


}
