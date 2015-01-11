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
	

}
