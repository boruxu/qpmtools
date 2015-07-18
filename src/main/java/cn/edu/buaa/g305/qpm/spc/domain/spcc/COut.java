package cn.edu.buaa.g305.qpm.spc.domain.spcc;

import java.util.Arrays;

import cn.edu.buaa.g305.qpm.spc.domain.AbnormalPoint;


public class COut {
	
	private String[] x;
	private String[] time;
	private String cCL;
	private String cUCL;
	private String cLCL;
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
	
	


	public String getcCL() {
		return cCL;
	}



	public void setcCL(String cCL) {
		this.cCL = cCL;
	}



	public String getcUCL() {
		return cUCL;
	}



	public void setcUCL(String cUCL) {
		this.cUCL = cUCL;
	}



	public String getcLCL() {
		return cLCL;
	}



	public void setcLCL(String cLCL) {
		this.cLCL = cLCL;
	}



	@Override
	
	public String toString()
	{
		
		String spcuString="{"+"x:"+Arrays.toString(x)+","+
	                           "time:"+Arrays.toString(time)+","+
	                           "cUCL:"+cUCL+","+
	                           "cCL:"+cCL+","+
	                           "cLCL:"+cLCL+"}";
		return spcuString;
	}


}
