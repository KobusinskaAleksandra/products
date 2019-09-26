package assigment.products.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import assigment.products.entity.ProductVO;
import assigment.products.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public String getOne(Long id) throws NotFoundException {
		ProductVO product = productRepository.findOne(id);
		if(product==null) {
			log.error("Product with id: " + id + " doesn't exist");
			throw new NotFoundException("Product with id: " + id + " doesn't exist");
		}
		String productJson = new Gson().toJson(product);
		return productJson;
	}

	public String getAll() throws Exception {
		List<ProductVO> products = productRepository.findAll();
		
		if(products.isEmpty()) {
			log.error("There's no products available");
			throw new NotFoundException("There's no products available");
		}
		
		String productsJson = new Gson().toJson(products);
		return productsJson;
	}
	
	public void updateProduct(Long id, String productString) throws Exception {
		ProductVO newProduct =  new Gson().fromJson(productString, ProductVO.class);
		ProductVO productToUpdate = productRepository.findOne(id);
		if(productToUpdate==null) {
			log.error("Product with id: " + id + " doesn't exist");
			throw new NotFoundException("Product with id: " + id + " doesn't exist");
		}
		productToUpdate.setCurrentPrice(newProduct.getCurrentPrice());
		productToUpdate.setLastUpdated(new Date());
		productToUpdate.setName(newProduct.getName());
		productRepository.save(productToUpdate);
	}
	
	public void addProduct(String productString) {
		ProductVO product =  new Gson().fromJson(productString, ProductVO.class);
		productRepository.save(product);
	}
	
}
