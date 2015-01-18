package cn.edu.buaa.g305.qpm.correlation.server;

import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationIn;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationOut;
import cn.edu.buaa.g305.qpm.system.domain.Project;

public interface CorrelationServer {
	
	//持久化输入产品
	void saveCorrelationIn(CorrelationIn correlationIn);
	
	
    CorrelationOut[] computeRAndP(CorrelationIn correlationIn); 
	
}
