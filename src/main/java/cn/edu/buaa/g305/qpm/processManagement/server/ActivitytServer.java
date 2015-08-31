package cn.edu.buaa.g305.qpm.processManagement.server;

import java.util.List;

import org.springframework.web.servlet.NoHandlerFoundException;

import cn.edu.buaa.g305.qpm.processManagement.domian.Activity;

public interface ActivitytServer {
	
	Activity getByName(String name);
	Activity getById(String id);
	void delete(String id);
    void deleteByName(String name);
	Activity save(Activity Product);
	Activity update(Activity Product,String id) throws NoHandlerFoundException;

	List<Activity> getAllList();

}
