package cn.edu.buaa.g305.qpm.processManagement.server;

import java.util.List;
import cn.edu.buaa.g305.qpm.processManagement.domian.Tag;

public interface TagServer {
	
	Tag getByName(String name);
	Tag getById(String id);
	void delete(String id);
    void deleteByName(String name);
	Tag save(Tag Tag);
	Tag update(Tag Tag,String id);

	List<Tag> getAllList();

}
