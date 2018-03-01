package com.inventory.management.rest.IT;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.inventory.management.model.Product;
import com.inventory.management.model.reference.ProductSize;

public class SizeControllerITTest extends BaseTestCaseIT{
	
	private String baseUrl = "/size";
	
	private ProductSize productSize = new ProductSize();
	
	@Before
	public void setup() {
		
		Product product = new Product();
		product.setId(DEFAULT_ID);
		
		productSize.setId(DEFAULT_ID);
		productSize.setName("Test Size");
	}
	
    @Test
	public void listAllProducts() throws Exception {
    	verifyOK(createGetRequest(baseUrl, "/list").andDo(MockMvcResultHandlers.print()));
    }
	
	@Test
	public void getProductSizes()  throws Exception {
		verifyOK(createGetRequest(baseUrl, "/product/" + DEFAULT_ID).andDo(MockMvcResultHandlers.print()));
	}	
	
	@Test(expected=Exception.class)
	public void getProductSizes_should_throw_excpetion()  throws Exception {
		createGetRequest(baseUrl, "/product/" + INVALID_ID).andDo(MockMvcResultHandlers.print())
	    	.andExpect(MockMvcResultMatchers.status().isInternalServerError())
	    	.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}	
	
	
	@Test
	public void addProductSize()  throws Exception {
		verifyOK(createPutRequest(baseUrl, "/add/product/" + DEFAULT_ID, productSize).andDo(MockMvcResultHandlers.print()));
	}	
	
	@Test
	public void editProduct()  throws Exception {
		productSize.setName("updated");
		verifyOK(createPutRequest(baseUrl, "/edit/" + DEFAULT_ID, productSize).andDo(MockMvcResultHandlers.print()));
	}
	
	@Test
	public void addNewProductSize()  throws Exception {
		productSize.setId(null);
		createPostRequest(baseUrl, "/new", productSize).andDo(MockMvcResultHandlers.print())
	    	.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
	    	.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

	}
	
}
