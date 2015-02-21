package cn.edu.buaa.g305.qpm.spc.domain.spcz;

import java.util.Arrays;


public class SpcZOut {
	
	private String[] x;
	private String[] time;
	
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
	


	@Override
	
	public String toString()
	{
		
		String spcuString="{"+"x:"+Arrays.toString(x)+","+
	                           "time:"+Arrays.toString(time)+"}";
		return spcuString;
	}


}
