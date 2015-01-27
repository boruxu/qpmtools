package cn.edu.buaa.g305.qpm.spc.server;

import cn.edu.buaa.g305.qpm.spc.domain.SPCXRIn;
import cn.edu.buaa.g305.qpm.spc.domain.SPCXROut;


public interface SPCIO {
	
	SPCXRIn getSpcxrInByName(String name);

}
