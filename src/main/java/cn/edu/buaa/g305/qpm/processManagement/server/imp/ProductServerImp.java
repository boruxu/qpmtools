package cn.edu.buaa.g305.qpm.processManagement.server.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.edu.buaa.g305.qpm.processManagement.dao.ProductRepository;
import cn.edu.buaa.g305.qpm.processManagement.domian.Product;
import cn.edu.buaa.g305.qpm.processManagement.server.ProductServer;
@Component
public class ProductServerImp implements ProductServer{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product getByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public Product getById(String id) {
		
		return productRepository.findOne(id);
	}

	@Override
	public void delete(String id) {	
		 productRepository.delete(id);
	}

	@Override
	public void deleteByName(String name) {
		Product Product=productRepository.findByName(name);
		if(Product!=null)
		{
			productRepository.delete(Product);
		}
		
	}

	@Override
	public Product save(Product Product) {
		return productRepository.save(Product);
	}

	@Override
	public Product update(Product Product, String id) {
        Product.setId(id);		
		return productRepository.save(Product);
	}

	@Override
	public List<Product> getAllList() {
		return (List<Product>) productRepository.findAll();
	}

}
