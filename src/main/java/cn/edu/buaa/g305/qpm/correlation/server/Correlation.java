package cn.edu.buaa.g305.qpm.correlation.server;

import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationOut;

public interface Correlation {
	
	//�����X��Y֮���Pֵ��Rֵ
	public CorrelationOut computeCorrelationAndPValue(double[] xArray,double[] yArray);

}
