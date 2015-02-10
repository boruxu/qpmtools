package cn.edu.buaa.g305.qpm.correlation.server;

import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationIn;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationOut;

public interface CorrelationServer {
	
	//持久化输入产品
	void saveCorrelationIn(CorrelationIn correlationIn);
	
	CorrelationIn getCorrelationInByName(String name);
	
	
    CorrelationOut[] computeRAndP(CorrelationIn correlationIn); 
	
}
