package cn.edu.buaa.g305.qpm.spc.domain.spcxs;

import java.util.Arrays;

import cn.edu.buaa.g305.qpm.spc.domain.AbnormalPoint;

public class XSOut {
	
	private String[] x;
	private String[] s;
	private String[] time;
	private String xUCL;
	private String xCL;
	private String xLCL;
	private String sUCL;
	private String sCL;
	private String sLCL;
	private AbnormalPoint abnormalPointX;
	private AbnormalPoint abnormalPointS;
		
	public AbnormalPoint getAbnormalPointX() {
		return abnormalPointX;
	}


	public void setAbnormalPointX(AbnormalPoint abnormalPointX) {
		this.abnormalPointX = abnormalPointX;
	}


	public AbnormalPoint getAbnormalPointS() {
		return abnormalPointS;
	}


	public void setAbnormalPointS(AbnormalPoint abnormalPointS) {
		this.abnormalPointS = abnormalPointS;
	}


	public String[] getX() {
		return x;
	}


	public void setX(String[] x) {
		this.x = x;
	}


	public String[] getS() {
		return s;
	}


	public void setS(String[] s) {
		this.s = s;
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


	public String getsUCL() {
		return sUCL;
	}


	public void setsUCL(String sUCL) {
		this.sUCL = sUCL;
	}


	public String getsCL() {
		return sCL;
	}


	public void setsCL(String sCL) {
		this.sCL = sCL;
	}


	public String getsLCL() {
		return sLCL;
	}


	public void setsLCL(String sLCL) {
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
