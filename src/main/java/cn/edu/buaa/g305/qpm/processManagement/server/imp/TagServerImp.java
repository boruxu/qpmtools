package cn.edu.buaa.g305.qpm.processManagement.server.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.mc.dao.MCRepository;
import cn.edu.buaa.g305.qpm.processManagement.dao.TagRepository;
import cn.edu.buaa.g305.qpm.processManagement.domian.Tag;
import cn.edu.buaa.g305.qpm.processManagement.server.TagServer;
@Component
public class TagServerImp implements TagServer{
	
	@Autowired
	private TagRepository tagRepository;

	@Override
	public Tag getByName(String name) {
		return tagRepository.findByName(name);
	}

	@Override
	public Tag getById(String id) {
		
		return tagRepository.findOne(id);
	}

	@Override
	public void delete(String id) {	
		 tagRepository.delete(id);
	}

	@Override
	public void deleteByName(String name) {
		Tag tag=tagRepository.findByName(name);
		if(tag!=null)
		{
			tagRepository.delete(tag);
		}
		
	}

	@Override
	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public Tag update(Tag tag, String id) {
        tag.setId(id);		
		return tagRepository.save(tag);
	}

	@Override
	public List<Tag> getAllList() {
		return (List<Tag>) tagRepository.findAll();
	}

}
