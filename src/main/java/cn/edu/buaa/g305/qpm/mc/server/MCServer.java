package cn.edu.buaa.g305.qpm.mc.server;

import java.util.List;

import cn.edu.buaa.g305.qpm.mc.domain.MC;

public interface MCServer {
	
	MC getByName(String name);
	MC getById(String id);
	MC delete(String id);
	MC deleteByName(String name);
	MC save(MC mc);
	MC update(MC mc,String id);
	
	//返回的MC不包含返回的随机数result，通过get请求在单独获得（考虑到传输性能）
	List<MC> getAllList();
	List<MC> getListByProject(String name);

	

}
