package cn.edu.buaa.g305.qpm.spc.server;

import cn.edu.buaa.g305.qpm.spc.domain.*;
import cn.edu.buaa.g305.qpm.spc.domain.spcc.C;
import cn.edu.buaa.g305.qpm.spc.domain.spcc.CIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcc.COut;
import cn.edu.buaa.g305.qpm.spc.domain.spcu.U;
import cn.edu.buaa.g305.qpm.spc.domain.spcu.UIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcu.UOut;
import cn.edu.buaa.g305.qpm.spc.domain.spcxmr.XmR;
import cn.edu.buaa.g305.qpm.spc.domain.spcxmr.XmRIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcxmr.XmROut;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.XRIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.XROut;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.XR;
import cn.edu.buaa.g305.qpm.spc.domain.spcxs.XS;
import cn.edu.buaa.g305.qpm.spc.domain.spcxs.XSIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcxs.XSOut;
import cn.edu.buaa.g305.qpm.spc.domain.spcz.Z;
import cn.edu.buaa.g305.qpm.spc.domain.spcz.ZOut;

public interface SPCService {
	
	XROut computeXR(XRIn spcxr);
	XSOut computeXS(XSIn spcxsIn);
	XmROut computeXMR(XmRIn spcxmrIn);
	COut computeC(CIn spccIn);
	UOut computeU(UIn spcuIn);
	ZOut computeZ(UIn spczIn);
	
	//X-R图数据库操作
	XR getXRByName(String name);
	
	XR getXRById(String id);
	
	XR deleteXR(String id);
	
	XR deleteXRByName(String name);
	
	XR update(XR spcXR,String id,String project);
	XR save(XR spcXR,String project);
	
	SpcList getSpcXRList();
	SpcList getSpcXRListByProjectName(String name);
	
	
	//X-S图数据库操作
    XS getXSByName(String name);
	
	XS getXSById(String id);
	
	XS deleteXS(String id);
	
	XS deleteXSByName(String name);
	
	XS update(XS spcXS,String id,String project);
	XS save(XS spcXS,String project);
	
	SpcList getSpcXSList();
	SpcList getSpcXSListByProjectName(String name);
	
	//XMR图数据库操作
	XmR getXMRByName(String name);
	
	XmR getXMRById(String id);
	
	XmR deleteXMR(String id);
	
	XmR deleteXMRByName(String name);
	
	XmR update(XmR spcXMR,String id,String project);
	XmR save(XmR spcXMR,String project);
	
	SpcList getSpcXMRList();
	SpcList getSpcXMRListByProjectName(String name);
	
	//C图数据库操作
    C getCByName(String name);
	
	C getCById(String id);
	
	C deleteC(String id);
	
	C deleteCByName(String name);
	
	C update(C spcC,String id,String project);
	C save(C spcC,String project);
	
	SpcList getSpcCList();
	SpcList getSpcCListByProjectName(String name);
	
	//U图数据库操作
	U getUByName(String name);
	
	U getUById(String id);
	
	U deleteU(String id);
	
	U deleteUByName(String name);
	
	U update(U spcU,String id,String project);
	U save(U spcU,String project);
	
	SpcList getSpcUList();
	SpcList getSpcUListByProjectName(String name);
	
	//Z图数据库操作
	Z getZByName(String name);
	
	Z getZById(String id);
	
	Z deleteZ(String id);
	
	Z deleteZByName(String name);
	
	Z update(Z spcZ,String id,String project);
	Z save(Z spcZ,String project);
	
	SpcList getSpcZList();
	SpcList getSpcZListByProjectName(String name);
	
	SpcList getAllSpcListByProject(String name);
}
