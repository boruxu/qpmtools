package cn.edu.buaa.g305.qpm.spc.server;

import cn.edu.buaa.g305.qpm.spc.domain.*;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.SpcXRIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.SpcXROut;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.SpcXR;
import cn.edu.buaa.g305.qpm.spc.domain.spcxs.SpcXS;
import cn.edu.buaa.g305.qpm.spc.domain.spcxs.SpcXSIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcxs.SpcXSOut;

public interface SPCService {
	
	SpcXROut computeXR(SpcXRIn spcxr);
	SpcXSOut computeXS(SpcXSIn spcxsIn);
	SPCXMROut computeXMR(SPCXMRIn spcxmrIn);
	SPCCOut computeC(SPCCIn spccIn);
	
	SpcXR getXRByName(String name);
	
	SpcXR getXRById(String id);
	
	SpcXR deleteXR(String id);
	
	SpcXR deleteXRByName(String name);
	
	SpcXR update(SpcXR spcXR,String id);
	SpcXR save(SpcXR spcXR);
	
    SpcXS getXSByName(String name);
	
	SpcXS getXSById(String id);
	
	SpcXS deleteXS(String id);
	
	SpcXS deleteXSByName(String name);
	
	SpcXS update(SpcXS spcXS,String id);
	SpcXS save(SpcXS spcXS);

}
