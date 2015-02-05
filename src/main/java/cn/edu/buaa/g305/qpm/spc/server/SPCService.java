package cn.edu.buaa.g305.qpm.spc.server;

import cn.edu.buaa.g305.qpm.spc.domain.*;

public interface SPCService {
	
	SPCXROut computeXR(SPCXRIn spcxr);
	SPCXSOut computeXS(SPCXSIn spcxsIn);
	SPCXMROut computeXMR(SPCXMRIn spcxmrIn);
	SPCCOut computeC(SPCCIn spccIn);
	
    SpcXR getSpcxrByName(String name);
	
	SpcXR save(SpcXR spcXR);
	
	void delete(String id);
	
	SpcXR getById(String id);

}
