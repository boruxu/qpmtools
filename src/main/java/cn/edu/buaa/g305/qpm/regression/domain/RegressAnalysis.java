package cn.edu.buaa.g305.qpm.regression.domain;

/**
 * @author boru
 * 存储回归分析中自变量的名称、系数、系数的标准误差、系数的T检验以及P值
 * 均转为String存储
 */
public class RegressAnalysis {

	//自变量名
	private String name;
	//系数
	private String beta;
	//系数标准误差
	private String sigma;
	//T
	private String t;
	//P
	private String p;
	public RegressAnalysis() {
		super();
	}
	public RegressAnalysis(String name,String beta,String sigma,String t,String p) {
		this.name=name;
		this.beta=beta;
		this.sigma=sigma;
		this.t=t;
		this.p=p;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBeta() {
		return beta;
	}
	public void setBeta(String beta) {
		this.beta = beta;
	}
	public String getSigma() {
		return sigma;
	}
	public void setSigma(String sigma) {
		this.sigma = sigma;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	@Override
	public String toString(){
		return "自变量名 "+"系数"+"系数标准误差"+"T"+"P"+"\n"+
	            name+" "+beta+" "+sigma+" "+t+" "+p+"\n";
	}
}
