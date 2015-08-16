package cn.edu.buaa.g305.qpm.processManagement.dao;

import org.springframework.data.repository.CrudRepository;
import cn.edu.buaa.g305.qpm.processManagement.domian.Tag;



public interface TagRepository extends CrudRepository<Tag
, String> {
	
	Tag findByName(String name);

}