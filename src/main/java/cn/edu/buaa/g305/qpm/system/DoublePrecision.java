package cn.edu.buaa.g305.qpm.system;

import java.math.BigDecimal;

public class DoublePrecision {
	/**
	 * Ҫ������λ��n��ԭ�������ݾ���ҪΪn+2
	 * @param doubleValue ����Ҫ����С���ľ���
	 * @param precision ������λ��
	 * @return �������С��
	 */
	public static double precision(double doubleValue,int precision)
	{
		BigDecimal doubleBigDecimal=new BigDecimal(doubleValue);
		double doubleP=doubleBigDecimal.setScale(precision,BigDecimal.ROUND_HALF_UP).doubleValue();
	    return doubleP;
	}

}
