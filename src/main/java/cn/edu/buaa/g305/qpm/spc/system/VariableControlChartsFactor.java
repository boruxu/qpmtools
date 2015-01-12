package cn.edu.buaa.g305.qpm.spc.system;


public class VariableControlChartsFactor {
	
	/**
	 * 计量值控制图系数A2,估计标准差
	 * 下标+2=n（n为样本大小）
	 * 由于n>10,R估计极差效果比较差
	 * 根据minitab中大于6就不采用R来估计标准差
	 * 所以此表只给出2-6的a2
	 */
	private static final double[] A2=new double[]{1.880,1.023,0.729,0.577,0.483};
	/**
	 * 计量值控制图系数d3，估计R图UCL
	 * 样本最大为6
	 * @see VariableControlChartsFactor#A2
	 * 
	 */
	private static final double[] D3=new double[]{0,0,0,0,0};
	/**
	 * 计量值控制图系数d4，估计R图LCL
	 * 样本最大为6
	 * @see VariableControlChartsFactor#A2
	 * 
	 */
	private static final double[] D4=new double[]{3.267,2.574,2.282,2.114,2.004};
	
	private static final double[] B3=new double[]{0,0,0,0,0.030,0.118,0.185,0.239,0.284,
                                                  0.321,0.354,0.382,0.406,0.428,0.448,
                                                  0.466,0.482,0.497,0.510,0.523,0.534,
                                                  0.545,0.555,0.565};
	private static final double[] B4=new double[]{3.267,2.568,2.266,2.089};
	
	/**
	 * 输入样本数，获取控制线系数A
	 * @param n
	 * @return a
	 */
	public static double computeA(int n) {
		double a=3/Math.sqrt(n);
		return a;		
	}
	/**
	 * 输入样本数，获取控制线系数A2
	 * @param n
	 * @return a2 值为2-6之外的样本值，返回-1
	 */
	public static double computeA2(int n){
		if(n<2||n>6)
		{
			return -1;
		}
		return A2[n-2];
	}
	/**
	 * 输入样本数，获取控制线系数D3
	 * @param n
	 * @return D3 值为2-6之外的样本值，返回-1
	 */
	public static double computeD3(int n){
		if(n<2||n>6)
		{
			return -1;
		}
		return D3[n-2];
	}
	/**
	 * 输入样本数，获取控制线系数D4
	 * @param n
	 * @return D4 值为2-6之外的样本值，返回-1
	 */
	public static double computeD4(int n){
		if(n<2||n>6)
		{
			return -1;
		}
		return D4[n-2];
	}
	
	//以下为X-S图相关的计算系数
	/**
	 * 输入样本数，获取控制线系数c2
	 * @param n
	 * @return c2
	 */
	private static double computeC2(int n){
		double result=0.0;
		if(n%2==0)
		{
			result=Math.sqrt(2.0/n)*Math.pow(computeFactorial((n-2)/2), 2)*Math.pow(2, n-2)
					/(computeFactorial(n-2)*Math.sqrt(Math.PI));
		}
		else {
			result=Math.sqrt(2.0/n)*computeFactorial(n-1)*Math.sqrt(Math.PI)
					/(computeFactorial((n-1)/2)*computeFactorial((n-3)/2)*Math.pow(2, n-1));
		}
		return result;
	}
	/**
	 * 输入样本数，获取控制线系数c4
	 * @param n
	 * @return c4
	 */
	private static double computeC4(int n){
		double result=0.0;
		result=computeC2(n)*Math.sqrt((double)n/(n-1));
		return result;
	}
	//采用double int数据长度不够用
	private static double computeFactorial(int n)
	{
		double result=1;
		if(n==0||n==1)
		{
			return 1;
		}
		for(int i=2;i<=n;i++)
		{
			result=result*i;
		}
		return result;	
	}
	/**
	 * 输入样本数，获取控制线系数A3（西格玛未知时，估计X图上下线的3倍西格玛A3*S平均值 ）
	 * @param n
	 * @return a3
	 */
	public static double computeA3(int n){
		double result=0.0;
		result=3/(computeC4(n)*Math.sqrt(n));
		return result;
	}
	/**
	 * 输入样本数，获取控制线系数B3
	 * @param n
	 * @return b3
	 */
	public static double computeB3(int n){
		
		double result=0.0;
		//n<6时计算公式不适用，用图表中的数据
		if(n<6)
		{
			return result;
		}
		result=1-(3/(computeC4(n)*Math.sqrt(2*(n-1))));
		return result;
	}
	/**
	 * 输入样本数，获取控制线系数B4
	 * @param n
	 * @return b4
	 */
	public static double computeB4(int n){
		
		double result=0.0;
		//n<26时计算公式不适用，用图表中的数据
		if(n<26)
		{
			result=B4[n-2];
			return result;
		}
		result=1+(3/(computeC4(n)*Math.sqrt(2*(n-1))));
		return result;
	}
	

}
