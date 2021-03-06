package cn.edu.buaa.g305.qpm.processManagement.server.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.NoHandlerFoundException;

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
	public Product save(Product product) {
		product.setId(null);
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product, String id) throws NoHandlerFoundException {
        product.setId(id);
        Product productR=productRepository.findOne(id);
        if(productR==null)
        {
        	throw new NoHandlerFoundException("post", id, null);
        }
        else {
        	productR.setFileName(product.getFileName());
        	productR.setName(product.getName());
        	productR.setDescription(product.getDescription());
		}
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllList() {
		return (List<Product>) productRepository.findAll();
	}

}
