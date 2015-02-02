package cn.edu.buaa.g305.qpm.spc.domain;

import java.util.Arrays;

public class SPCXMROut {
	
	private double[] x;
	private double[] mr;
	private String[] time;
	private double xUCL;
	private double xCL;
	private double xLCL;
	private double mrUCL;
	private double mrCL;
	private final double mrLCL=0;
	
	public double[] getX() {
		return x;
	}
	public void setX(double[] x) {
		this.x = x;
	}
	public double[] getMr() {
		return mr;
	}
	public void setMr(double[] mr) {
		this.mr = mr;
	}
	public String[] getTime() {
		return time;
	}
	public void setTime(String[] time) {
		this.time = time;
	}
	public double getxUCL() {
		return xUCL;
	}
	public void setxUCL(double xUCL) {
		this.xUCL = xUCL;
	}
	public double getxCL() {
		return xCL;
	}
	public void setxCL(double xCL) {
		this.xCL = xCL;
	}
	public double getxLCL() {
		return xLCL;
	}
	public void setxLCL(double xLCL) {
		this.xLCL = xLCL;
	}
	public double getMrUCL() {
		return mrUCL;
	}
	public void setMrUCL(double mrUCL) {
		this.mrUCL = mrUCL;
	}
	public double getMrCL() {
		return mrCL;
	}
	public void setMrCL(double mrCL) {
		this.mrCL = mrCL;
	}
	public double getMrLCL() {
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
