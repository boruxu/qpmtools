package cn.edu.buaa.g305.qpm.regression.domain;

import java.util.Arrays;

public class RegressIn {
	
	//回归所需的x，y数组和其名字（自变量与因变量名字）
	private String[] xName;
	private String[][] x;
	private String yName;
	private String[] y;
	public String[] getxName() {
		return xName;
	}
	public void setxName(String[] xName) {
		this.xName = xName;
	}
	public String[][] getX() {
		return x;
	}
	public void setX(String[][] x) {
		this.x = x;
	}
	public String getyName() {
		return yName;
	}
	public void setyName(String yName) {
		this.yName = yName;
	}
	public String[] getY() {
		return y;
	}
	public void setY(String[] y) {
		this.y = y;
	}
	@Override
	public String toString(){
		return 
				"yName：\n"+
				yName+"\n"+
				"y：\n"+
				Arrays.toString(y)+"\n"+
				"xName：\n"+
				Arrays.toString(xName)+"\n"+
				"x：\n"+
				Arrays.deepToString(x);
	}
}
