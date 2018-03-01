package com.inventory.management.rest.IT;

import org.junit.Test;
import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.inventory.management.model.Brand;
import com.inventory.management.model.Product;
import com.inventory.management.model.reference.ProductType;

public class ProductControllerITTest extends BaseTestCaseIT{
	
	private String baseUrl = "/product";
	
	private Product product = new Product();
	
	@Before
	public void setup() {
		
		Brand brand = new Brand();
		ProductType productType = new ProductType();
		
		brand.setId(DEFAULT_ID);
		productType.setId(DEFAULT_ID);
		
		product.setName("New Product");
		product.setBrand(brand);
		product.setProductType(productType);
	}
	
    @Test
	public void listAllProducts() throws Exception {
    	verifyOK(createGetRequest(baseUrl, "/list").andDo(MockMvcResultHandlers.print()));
    }
	
	@Test
	public void getProductById() throws Exception {
		verifyOK(createGetRequest(baseUrl, "/view/" + DEFAULT_ID).andDo(MockMvcResultHandlers.print()));
	}	
	
	@Test(expected=Exception.class)
	public void getProductById_should_throw_exception()  throws Exception {
		createGetRequest(baseUrl, "/view/" + INVALID_ID).andDo(MockMvcResultHandlers.print())
	    	.andExpect(MockMvcResultMatchers.status().isInternalServerError())
	    	.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}	
	
	@Test
	public void deleteProduct()  throws Exception {
		verifyOK(createPutRequest(baseUrl, "/delete/" + DEFAULT_ID, null).andDo(MockMvcResultHandlers.print()));
	}	
	
	@Test(expected=Exception.class)
	public void deleteProduct_should_throw_exception()  throws Exception {
		createPutRequest(baseUrl, "/delete/" + INVALID_ID, null).andDo(MockMvcResultHandlers.print())
    		.andExpect(MockMvcResultMatchers.status().isInternalServerError())
	    	.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}	
	
	@Test
	public void editProduct()  throws Exception {
		product.setName("updated");
		verifyOK(createPutRequest(baseUrl, "/edit/" + DEFAULT_ID, product).andDo(MockMvcResultHandlers.print()));
	}
	
	@Test(expected=Exception.class)
	public void editProduct__should_throw_exception()  throws Exception {
		product.setName("updated");
		createPutRequest(baseUrl, "/edit/" + INVALID_ID, product).andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isInternalServerError())
    	.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void addNewProduct()  throws Exception {
		product.setId(null);
		verifyOK(createPostRequest(baseUrl, "/new", product).andDo(MockMvcResultHandlers.print()));
	}
	
}
