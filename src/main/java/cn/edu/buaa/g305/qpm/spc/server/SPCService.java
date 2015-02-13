package cn.edu.buaa.g305.qpm.spc.server;

import java.util.List;

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
	
	//X-R图数据库操作
	SpcXR getXRByName(String name);
	
	SpcXR getXRById(String id);
	
	SpcXR deleteXR(String id);
	
	SpcXR deleteXRByName(String name);
	
	SpcXR update(SpcXR spcXR,String id,String project);
	SpcXR save(SpcXR spcXR,String project);
	
	SpcList getSpcXRList();
	SpcList getSpcXRListByProjectName(String name);
	
	
	//X-S图数据库操作
    SpcXS getXSByName(String name);
	
	SpcXS getXSById(String id);
	
	SpcXS deleteXS(String id);
	
	SpcXS deleteXSByName(String name);
	
	SpcXS update(SpcXS spcXS,String id,String project);
	SpcXS save(SpcXS spcXS,String project);
	
	SpcList getSpcXSList();
	SpcList getSpcXSListByProjectName(String name);

}
