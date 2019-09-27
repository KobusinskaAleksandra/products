package assigment.products;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import assigment.products.repository.ProductRepository;
import assigment.products.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class AssigmentProductsApplicationTests {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;

	
	@Test
	public void newProduct() throws Exception {	
		String product = "{'name':'Test','currentPrice':'1.0'}";
		productService.addProduct(product);
		assertTrue(productRepository.findByName("Test")!=null);
		assertTrue((productRepository.findByName("Test").getLastUpdated().getTime()-(new Date()).getTime())/1000 >-2);
	}
	
	@Test
	public void newProductWithDate() throws Exception {	
		String date = "2019-09-27 10:00:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String product = "{'name':'TestDate','currentPrice':'1.0','lastUpdated':'" + date + "'}";
		productService.addProduct(product);
		assertTrue(productRepository.findByName("TestDate")!=null);
		assertTrue(format.format(productRepository.findByName("TestDate").getLastUpdated()).equals(date));
	}
	
	@Test
	public void updateProduct() throws Exception {	
		String product = "{'name':'TestUpdate','currentPrice':'1.0'}";
		productService.updateProduct(1L, product);
		assertTrue(productRepository.findOne(1L).getName().equals("TestUpdate"));
	}
	
}