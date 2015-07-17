package cn.edu.buaa.g305.qpm.spc.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL)
public class AbnormalPoint {
	//8条规则对应的异常点数
	private String[][][] abnormalPoint;
	
	private static String[] description={"1个点，距离中心线大于K个标准差",
										 "连续K点在中心线同一侧",
										 "连续K个点，全部递增或全部递减",
										 "连续K个点，上下交错",
										 "K+1个点中有K个点距离中心线（同侧）大于2个标准差",
										 "K+1个点中有K个点距离中心线（同侧）大于1个标准差",
										 "连续K个点，距离中心线（任一侧）1个标准差以内",
										 "连续K个点，距离中心线（任一侧）大于1个标准差"};
	//系统默认的标准,第一个为double，其余必须转换为int
	private double[] k={3,9,6,14,2,4,15,8};
	
	//具体使用哪个判别方法，默认使用第一点规则
	private boolean[] check={true,false,false,false,false,false,false,false};

	

	public String[][][] getAbnormalPoint() {
		return abnormalPoint;
	}


	public void setAbnormalPoint(String[][][] abnormalPoint) {
		this.abnormalPoint = abnormalPoint;
	}


	public static void setDescription(String[] description) {
		AbnormalPoint.description = description;
	}


	public static String[] getDescription() {
		return description;
	}


	public double[] getK() {
		return k;
	}


	public void setK(double[] k) {
		this.k = k;
	}


	public boolean[] getCheck() {
		return check;
	}

	public void setCheck(boolean[] check) {
		this.check = check;
	}
		

}
