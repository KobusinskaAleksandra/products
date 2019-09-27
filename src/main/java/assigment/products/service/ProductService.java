package assigment.products.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		if (product == null) {
			log.error("Product with id: " + id + " doesn't exist");
			throw new NotFoundException("Product with id: " + id + " doesn't exist");
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		String productJson = gson.toJson(product);
		return productJson;
	}

	public String getAll() throws Exception {
		List<ProductVO> products = productRepository.findAll();

		if (products.isEmpty()) {
			log.error("There's no products available");
			throw new NotFoundException("There's no products available");
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		String productsJson = gson.toJson(products);
		return productsJson;
	}

	public void updateProduct(Long id, String productString) throws Exception {
		ProductVO newProduct = new Gson().fromJson(productString, ProductVO.class);
		ProductVO productToUpdate = productRepository.findOne(id);
		if (productToUpdate == null) {
			log.error("Product with id: " + id + " doesn't exist");
			throw new NotFoundException("Product with id: " + id + " doesn't exist");
		}
		if (newProduct.getCurrentPrice() != null) {
			productToUpdate.setCurrentPrice(newProduct.getCurrentPrice());
		}
		if (newProduct.getName() != null) {
			productToUpdate.setName(newProduct.getName());
		}
		productToUpdate.setLastUpdated(new Date());
		productRepository.save(productToUpdate);
	}

	public ProductVO addProduct(String productString) {
		ProductVO product = new Gson().fromJson(productString, ProductVO.class);
		if (product.getLastUpdated() == null)
			product.setLastUpdated(new Date());
		productRepository.save(product);
		return product;
	}

}
