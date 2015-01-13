package cn.edu.buaa.g305.qpm.spc.server;

import cn.edu.buaa.g305.qpm.spc.domain.*;

public interface SPC {
	
	SPCXROut computeXR(SPCXRIn spcxrIn);
	SPCXSOut computeXS(SPCXSIn spcxsIn);

}
