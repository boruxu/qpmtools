package cn.edu.buaa.g305.qpm.correlation.server;

import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationOut;

public interface Correlation {
	
	//计算出X、Y之间的P值和R值
	public CorrelationOut computeCorrelationAndPValue(double[] xArray,double[] yArray);

}
