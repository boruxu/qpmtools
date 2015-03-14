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
	
	List<MC> getAllList();
	List<MC> getListByProject(String name);
	//返回mc名字列表,弥补返回List MC的性能损失，返回MC会返回大量随机数，数据量较大
	List<String> getAllName();
	List<String> getNameListByProject(String name);
	

}
