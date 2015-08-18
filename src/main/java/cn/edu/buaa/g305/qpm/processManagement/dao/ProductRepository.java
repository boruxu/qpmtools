package cn.edu.buaa.g305.qpm.processManagement.dao;

import org.springframework.data.repository.CrudRepository;
import cn.edu.buaa.g305.qpm.processManagement.domian.Product;

public interface ProductRepository extends CrudRepository<Product
, String> {
	
	Product findByName(String name);

}