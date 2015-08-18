package cn.edu.buaa.g305.qpm.processManagement.server;

import java.util.List;
import cn.edu.buaa.g305.qpm.processManagement.domian.Product;

public interface ProductServer {
	
	Product getByName(String name);
	Product getById(String id);
	void delete(String id);
    void deleteByName(String name);
	Product save(Product Product);
	Product update(Product Product,String id);

	List<Product> getAllList();

}
