package cn.edu.buaa.g305.qpm.spc.domain.spcxr;

import java.util.Arrays;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL)
public class SpcXROut {
	
	private String[] x;
	private String[] r;
	private String[] time;
	private String xUCL;
	private String xCL;
	private String xLCL;
	private String rUCL;
	private String rCL;
	private String rLCL;
	
	private String error; 
	
	
	public String getError() {
		return error;
	}



	public void setError(String error) {
		this.error = error;
	}



	public String[] getX() {
		return x;
	}



	public void setX(String[] x) {
		this.x = x;
	}



	public String[] getR() {
		return r;
	}



	public void setR(String[] r) {
		this.r = r;
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



	public String getrUCL() {
		return rUCL;
	}



	public void setrUCL(String rUCL) {
		this.rUCL = rUCL;
	}



	public String getrCL() {
		return rCL;
	}



	public void setrCL(String rCL) {
		this.rCL = rCL;
	}



	public String getrLCL() {
		return rLCL;
	}



	public void setrLCL(String rLCL) {
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
