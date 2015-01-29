package cn.edu.buaa.g305.qpm.spc.domain;


public class SPCCOut {
	
	private double[] x;
	private String[] time;
	private double c;
	private double cUCL;
	private double cLCL;
	public double[] getX() {
		return x;
	}
	public void setX(double[] x) {
		this.x = x;
	}
	public String[] getTime() {
		return time;
	}
	public void setTime(String[] time) {
		this.time = time;
	}
	public double getC() {
		return c;
	}
	public void setC(double c) {
		this.c = c;
	}
	public double getcUCL() {
		return cUCL;
	}
	public void setcUCL(double cUCL) {
		this.cUCL = cUCL;
	}
	public double getcLCL() {
		return cLCL;
	}
	public void setcLCL(double cLCL) {
		this.cLCL = cLCL;
	}
	


}
