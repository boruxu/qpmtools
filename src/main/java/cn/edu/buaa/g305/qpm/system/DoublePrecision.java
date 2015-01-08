package cn.edu.buaa.g305.qpm.system;

import java.math.BigDecimal;

public class DoublePrecision {
	/**
	 * 要保留的位数n，原数据数据精度要为n+2
	 * @param doubleValue 输入要保留小数的精度
	 * @param precision 保留的位数
	 * @return 保留后的小数
	 */
	public static double precision(double doubleValue,int precision)
	{
		BigDecimal doubleBigDecimal=new BigDecimal(doubleValue);
		double doubleP=doubleBigDecimal.setScale(precision,BigDecimal.ROUND_HALF_UP).doubleValue();
	    return doubleP;
	}

}
