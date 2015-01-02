package cn.edu.buaa.g305.qpm.correlation.server;

import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationIn;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationOut;

public interface CorrelationServer {
	
	 CorrelationOut[] computeRAndP(CorrelationIn correlationIn); 
	
}
