package cn.edu.buaa.g305.qpm.system.dao;

import org.springframework.data.repository.CrudRepository;
import cn.edu.buaa.g305.qpm.system.domain.User;

public interface  UserRepository  extends CrudRepository<User, String>{
	
	User findByName(String name);
	
}