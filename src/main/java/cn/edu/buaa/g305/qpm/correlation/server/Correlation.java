package cn.edu.buaa.g305.qpm.correlation.server;

import java.util.Map;
import org.springframework.stereotype.Component;

public interface Correlation {
	
	//�����X��Y֮���Pֵ��Rֵ
	public Map<String, Double> computeCorrelationAndPValue(double[] xArray,double[] yArray);

}
