package cn.edu.buaa.g305.qpm.system;

import java.math.BigDecimal;

public class DoublePrecision {
	/**
	 * 保留精度为n，被保留数进度需n+2
	 * @param doubleValue 被保留数
	 * @param precision 保留位数
	 * @return 相应精度的double数
	 */
	public static double precision(double doubleValue,int precision)
	{
		BigDecimal doubleBigDecimal=new BigDecimal(doubleValue);
		double doubleP=doubleBigDecimal.setScale(precision,BigDecimal.ROUND_HALF_UP).doubleValue();
	    return doubleP;
	}

}
