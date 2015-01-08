package cn.edu.buaa.g305.qpm.correlation.server;

import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationOut;

public interface Correlation {
	
	//输入两个数组，计算相应的r和P
	public CorrelationOut computeCorrelationAndPValue(double[] xArray,double[] yArray);

}
