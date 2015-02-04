package cn.edu.buaa.g305.qpm.spc.server;

import java.math.BigInteger;
import cn.edu.buaa.g305.qpm.spc.domain.SpcXR;


public interface SPCIO {
	
	SpcXR getSpcxrByName(String name);
	
	BigInteger save(SpcXR spcXR);
	
	void delete(BigInteger id);
	
	SpcXR getById(BigInteger id);
	
	

}
