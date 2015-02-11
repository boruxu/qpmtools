package cn.edu.buaa.g305.qpm.spc.server;

import cn.edu.buaa.g305.qpm.spc.domain.*;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.SpcXRIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.SpcXROut;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.SpcXR;

public interface SPCService {
	
	SpcXROut computeXR(SpcXRIn spcxr);
	SPCXSOut computeXS(SPCXSIn spcxsIn);
	SPCXMROut computeXMR(SPCXMRIn spcxmrIn);
	SPCCOut computeC(SPCCIn spccIn);
	
	SpcXR getXRByName(String name);
	
	SpcXR getXRById(String id);
	
	SpcXR deleteXR(String id);
	
	SpcXR deleteXRByName(String name);
	
	SpcXR update(SpcXR spcXR,String id);
	SpcXR save(SpcXR spcXR);

}
