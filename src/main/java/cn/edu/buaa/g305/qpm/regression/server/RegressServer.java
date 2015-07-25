package cn.edu.buaa.g305.qpm.regression.server;

import java.util.List;

import cn.edu.buaa.g305.qpm.regression.domain.Regress;

public interface RegressServer{
		
	Regress computer(Regress regress);
	
	Regress getByName(String name);
	Regress getById(String id);
	Regress delete(String id);
	Regress deleteByName(String name);
	Regress save(Regress mc);
	Regress update(Regress mc,String id);
	
	//返回的MC不包含返回的随机数result，通过get请求在单独获得（考虑到传输性能）
	List<Regress> getAllList();
	List<Regress> getListByProject(String name);


}
