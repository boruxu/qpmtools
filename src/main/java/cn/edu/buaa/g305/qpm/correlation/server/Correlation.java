package cn.edu.buaa.g305.qpm.correlation.server;

import java.util.Map;
import org.springframework.stereotype.Component;

public interface Correlation {
	
	//计算出X、Y之间的P值和R值
	public Map<String, Double> computeCorrelationAndPValue(double[] xArray,double[] yArray);

}
