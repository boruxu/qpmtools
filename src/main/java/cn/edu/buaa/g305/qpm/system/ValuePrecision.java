package cn.edu.buaa.g305.qpm.system;

import java.math.BigDecimal;

public class ValuePrecision {
	/**
	 * 保留精度为n，被保留数进度需n+2
	 * @param doubleValue 被保留数
	 * @param precision 保留位数
	 * @return 相应精度的double数
	 */
	public static double precision(double doubleValue,int precision)
	{
		BigDecimal doubleBigDecimal=new BigDecimal(doubleValue);
		double doubleS=doubleBigDecimal.setScale(precision,BigDecimal.ROUND_HALF_UP).doubleValue();
	    return doubleS;
	}
	/**
	 * @param stringValue 待求精度
	 * @return 返回数值的精度
	 */
	//判断可转换为数值的字符串的精度，判断double型不精确，改用判断string
	public static int precision(String stringValue)
	{
		int dot=stringValue.indexOf(".");
		int length=stringValue.length();
		//整数返回0
		if(dot==-1)
		{
			return 0;
		}
		else {
			return length-dot-1;
		}
	    
	}
	

}
