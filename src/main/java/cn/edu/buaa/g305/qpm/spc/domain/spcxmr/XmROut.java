package cn.edu.buaa.g305.qpm.spc.domain.spcxmr;

import java.util.Arrays;

public class XmROut {
	
	private String[] x;
	private String[] mr;
	private String[] time;
	private String xUCL;
	private String xCL;
	private String xLCL;
	private String mrUCL;
	private String mrCL;
	private final String mrLCL="0";
	
	public String[] getX() {
		return x;
	}

	public void setX(String[] x) {
		this.x = x;
	}

	public String[] getMr() {
		return mr;
	}

	public void setMr(String[] mr) {
		this.mr = mr;
	}

	public String[] getTime() {
		return time;
	}

	public void setTime(String[] time) {
		this.time = time;
	}

	public String getxUCL() {
		return xUCL;
	}

	public void setxUCL(String xUCL) {
		this.xUCL = xUCL;
	}

	public String getxCL() {
		return xCL;
	}

	public void setxCL(String xCL) {
		this.xCL = xCL;
	}

	public String getxLCL() {
		return xLCL;
	}

	public void setxLCL(String xLCL) {
		this.xLCL = xLCL;
	}

	public String getMrUCL() {
		return mrUCL;
	}

	public void setMrUCL(String mrUCL) {
		this.mrUCL = mrUCL;
	}

	public String getMrCL() {
		return mrCL;
	}

	public void setMrCL(String mrCL) {
		this.mrCL = mrCL;
	}

	public String getMrLCL() {
		return mrLCL;
	}

	@Override
	
	public String toString()
	{
		
		String spcxrString="{"+"x:"+Arrays.toString(x)+","+
	                           "time:"+Arrays.toString(time)+","+
				               "mr:"+Arrays.toString(mr)+","+
	                           "xUCL:"+xUCL+","+
	                           "xCL:"+xCL+","+
	                           "xLCL:"+xLCL+","+
	                           "mrUCL:"+mrUCL+","+
	                           "mrCL:"+mrCL+","+
	                           "mrLCL:"+mrLCL+"}";
		return spcxrString;
	}


}
