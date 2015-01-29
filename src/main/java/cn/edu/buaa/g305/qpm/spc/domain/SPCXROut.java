package cn.edu.buaa.g305.qpm.spc.domain;

import java.util.Arrays;

public class SPCXROut {
	
	private double[] x;
	private double[] r;
	private String[] time;
	private double xUCL;
	private double xCL;
	private double xLCL;
	private double rUCL;
	private double rCL;
	private double rLCL;
	
	public String[] getTime() {
		return time;
	}
	public void setTime(String[] time) {
		this.time = time;
	}
	public double getxCL() {
		return xCL;
	}
	public void setxCL(double xCL) {
		this.xCL = xCL;
	}
	public double getrCL() {
		return rCL;
	}
	public void setrCL(double rCL) {
		this.rCL = rCL;
	}
	public double[] getX() {
		return x;
	}
	public void setX(double[] x) {
		this.x = x;
	}

	public double[] getR() {
		return r;
	}
	public void setR(double[] r) {
		this.r = r;
	}
	public double getxUCL() {
		return xUCL;
	}
	public void setxUCL(double xUCL) {
		this.xUCL = xUCL;
	}

	public double getxLCL() {
		return xLCL;
	}
	public void setxLCL(double xLCL) {
		this.xLCL = xLCL;
	}
	public double getrUCL() {
		return rUCL;
	}
	public void setrUCL(double rUCL) {
		this.rUCL = rUCL;
	}

	public double getrLCL() {
		return rLCL;
	}
	public void setrLCL(double rLCL) {
		this.rLCL = rLCL;
	}
	
	@Override
	
	public String toString()
	{
		String spcxrString="{"+"x:"+Arrays.toString(x)+","+
	                           "time:"+Arrays.toString(time)+","+
				               "r:"+Arrays.toString(r)+","+
	                           "xUCL:"+xUCL+","+
	                           "xCL:"+xCL+","+
	                           "xLCL:"+xLCL+","+
	                           "rUCL:"+rUCL+","+
	                           "rCL:"+rCL+","+
	                           "rLCL:"+rLCL+"}";
		return spcxrString;
	}
	
	

}
