package cn.edu.buaa.g305.qpm.spc.domain;

public class SPCXSIn {
	private double[][] x;
	private String[] time;
	private double sigma=-1;
	
	public double getSigma() {
		return sigma;
	}
	public void setSigma(double sigma) {
		this.sigma = sigma;
	}
	public double[][] getX() {
		return x;
	}
	public void setX(double[][] x) {
		this.x = x;
	}
	public String[] getTime() {
		return time;
	}
	public void setTime(String[] time) {
		this.time = time;
	}
}
