package cn.edu.buaa.g305.qpm.spc.domain;

import java.util.Arrays;

public class SPCXSOut {
	
	private double[] x;
	private double[] s;
	private String[] time;
	private double xUCL;
	private double xCL;
	private double xLCL;
	private double sUCL;
	private double sCL;
	private double sLCL;
	public double[] getX() {
		return x;
	}
	public void setX(double[] x) {
		this.x = x;
	}
	public double[] getS() {
		return s;
	}
	public void setS(double[] s) {
		this.s = s;
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
	public double getsUCL() {
		return sUCL;
	}
	public void setsUCL(double sUCL) {
		this.sUCL = sUCL;
	}
	public double getsCL() {
		return sCL;
	}
	public void setsCL(double sCL) {
		this.sCL = sCL;
	}
	public double getsLCL() {
		return sLCL;
	}
	public void setsLCL(double sLCL) {
		this.sLCL = sLCL;
	}
	
	@Override
	
	public String toString()
	{
		
		String spcxrString="{"+"x:"+Arrays.toString(x)+","+
	                           "time:"+Arrays.toString(time)+","+
				               "s:"+Arrays.toString(s)+","+
	                           "xUCL:"+xUCL+","+
	                           "xCL:"+xCL+","+
	                           "xLCL:"+xLCL+","+
	                           "sUCL:"+sUCL+","+
	                           "sCL:"+sCL+","+
	                           "sLCL:"+sLCL+"}";
		return spcxrString;
	}
	
	

}
