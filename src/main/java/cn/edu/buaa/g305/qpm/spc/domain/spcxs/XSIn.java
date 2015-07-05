package cn.edu.buaa.g305.qpm.spc.domain.spcxs;

import java.util.Arrays;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
@JsonSerialize(include=Inclusion.NON_NULL)
public class XSIn {
	private String[][] x;
	private String[] time;
	private String sigma;
	
	
	public String[][] getX() {
		return x;
	}
	public void setX(String[][] x) {
		this.x = x;
	}
	public String getSigma() {
		return sigma;
	}
	public void setSigma(String sigma) {
		this.sigma = sigma;
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
		String xString="[";
		for (int i=0;i<x.length-1;i++) {
			xString+=Arrays.toString(x[i])+",";
		}
		xString+=Arrays.toString(x[x.length-1])+"]";
		String spcxsString="{"+"x:"+xString+","+
	                           "time:"+Arrays.toString(time)+","+"}";
		return spcxsString;
	}

	public static String format() {
		String format="{x:[[],[],[]...],time:[],sigma:''}";
		return format;
	}
}
