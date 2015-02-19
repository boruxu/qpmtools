package cn.edu.buaa.g305.qpm.spc.domain.spcu;

import java.util.Arrays;


public class SpcUOut {
	
	private String[] x;
	private String[] time;
	private String uCL;
	private String[] uUCL;
	private String[] uLCL;
	
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
	

	public String getuCL() {
		return uCL;
	}



	public void setuCL(String uCL) {
		this.uCL = uCL;
	}



	public String[] getuUCL() {
		return uUCL;
	}



	public void setuUCL(String[] uUCL) {
		this.uUCL = uUCL;
	}



	public String[] getuLCL() {
		return uLCL;
	}



	public void setuLCL(String[] uLCL) {
		this.uLCL = uLCL;
	}



	@Override
	
	public String toString()
	{
		
		String spcuString="{"+"x:"+Arrays.toString(x)+","+
	                           "time:"+Arrays.toString(time)+","+
	                           "uUCL:"+Arrays.toString(uUCL)+","+
	                           "uCL:"+uCL+","+
	                           "uLCL:"+Arrays.toString(uLCL)+"}";
		return spcuString;
	}


}
