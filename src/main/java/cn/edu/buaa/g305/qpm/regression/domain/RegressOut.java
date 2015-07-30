package cn.edu.buaa.g305.qpm.regression.domain;

import java.util.Arrays;

public class RegressOut {
	
	//回归方程
	private String regressFunction;
	private RegressAnalysis[] regressAnalysis;
	//回归模型标准差
	private String s;
	//回归R-sq
	private String r_sq;
	//R-sq调整
	private String r_sq_ad;
	//方差分析
	//回归、残差、总自由度
	private int dfr;
	private int dfe;
	private int dft;
	//回归、残差、总SS
	private String ssr;
	private String sse;
	private String sst;
	//回归、残差MS
	private String msr;
	private String mse;
	//方差分析F（检验）
	private String f;
	//方差检验P
	private String p;
	
	//不显示给用户，用来显示图形的数据，使用精确的double，不截断
	private double[] residual;
	private double[] yEstimate;
	
	public double[] getResidual() {
		return residual;
	}
	public void setResidual(double[] residual) {
		this.residual = residual;
	}
	public double[] getyEstimate() {
		return yEstimate;
	}
	public void setyEstimate(double[] yEstimate) {
		this.yEstimate = yEstimate;
	}
	public String getRegressFunction() {
		return regressFunction;
	}
	public void setRegressFunction(String regressFunction) {
		this.regressFunction = regressFunction;
	}
	public RegressAnalysis[] getRegressAnalysis() {
		return regressAnalysis;
	}
	public void setRegressAnalysis(RegressAnalysis[] regressAnalysis) {
		this.regressAnalysis = regressAnalysis;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getR_sq() {
		return r_sq;
	}
	public void setR_sq(String r_sq) {
		this.r_sq = r_sq;
	}
	public String getR_sq_ad() {
		return r_sq_ad;
	}
	public void setR_sq_ad(String r_sq_ad) {
		this.r_sq_ad = r_sq_ad;
	}
	public int getDfr() {
		return dfr;
	}
	public void setDfr(int dfr) {
		this.dfr = dfr;
	}
	public int getDfe() {
		return dfe;
	}
	public void setDfe(int dfe) {
		this.dfe = dfe;
	}
	public int getDft() {
		return dft;
	}
	public void setDft(int dft) {
		this.dft = dft;
	}
	public String getSsr() {
		return ssr;
	}
	public void setSsr(String ssr) {
		this.ssr = ssr;
	}
	public String getSse() {
		return sse;
	}
	public void setSse(String sse) {
		this.sse = sse;
	}
	public String getSst() {
		return sst;
	}
	public void setSst(String sst) {
		this.sst = sst;
	}
	public String getMsr() {
		return msr;
	}
	public void setMsr(String msr) {
		this.msr = msr;
	}
	public String getMse() {
		return mse;
	}
	public void setMse(String mse) {
		this.mse = mse;
	}
	public String getF() {
		return f;
	}
	public void setF(String f) {
		this.f = f;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	@Override
	public String toString(){
		return 
				"回归方程：\n"+
				regressFunction+"\n"+
				"自变量：\n"+
				Arrays.toString(regressAnalysis)+"\n"+
				"回归模型标准差：\n"+
				s+"\n"+
				"回归R-sq：\n"+
				r_sq+"\n"+
				"R-sq调整：\n"+
				r_sq_ad+"\n"+
				"回归、残差、总自由度：\n"+
				dfr+"\\"+dfe+"\\"+dft+"\n"+
				"回归、残差、总SS：\n"+
				ssr+"\\"+sse+"\\"+sst+"\n"+
				"回归、残差MS：\n"+
				msr+"\\"+mse+"\n"+
				"F检验：\n"+
				f+"\n"+
				"P检验：\n"+
				p;
	}

}
