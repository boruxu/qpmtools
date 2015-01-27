package cn.edu.buaa.g305.qpm.correlation.domain;

import java.util.Arrays;

public class CorrelationInXYArray {
	
	private double[] x;
	private double[] y;
	
	@Override
	public String toString()
	{
		String correlationInXYArrayString="";
		correlationInXYArrayString="{"+"x:"+Arrays.toString(x)+","+
				                       "y:"+Arrays.toString(y)+"}";
		return correlationInXYArrayString;
	}
	public double[] getX() {
		return x;
	}
	public void setX(double[] x) {
		this.x = x;
	}
	public double[] getY() {
		return y;
	}
	public void setY(double[] y) {
		this.y = y;
	}

}
