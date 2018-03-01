package com.inventory.management.rest;

import org.junit.Test;
import org.junit.Before;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventory.management.BaseTestCase;
import com.inventory.management.model.Product;
import com.inventory.management.model.ProductSizeMapping;
import com.inventory.management.model.reference.ProductSize;

public class SizeControllerTest extends BaseTestCase{
	
	@Autowired
	private SizeController controller;
	
	private ProductSize productSize = new ProductSize();
	private ProductSizeMapping productSizeMapping = new ProductSizeMapping();
	
	@Before
	public void setup() {
		
		Product product = new Product();
		ProductSize productSize = new ProductSize();
		
		product.setId(DEFAULT_ID);
		productSize.setId(DEFAULT_ID);
		
		productSize.setName("Test Size");
		
		productSizeMapping.setProduct(product);
		productSizeMapping.setProductSize(productSize);
	}

	@Test
	public void listAllProducts_should_return_not_empty_list() {
		List<ProductSize> list = controller.listAllProducts();
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getProductSizes_should_return_not_empty_list() {
		List<ProductSizeMapping> list = controller.getProductSizes(DEFAULT_ID);
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}	
	
	@Test(expected=EntityNotFoundException.class)
	public void getProductSizes_should_throw_excpetion() {
		List<ProductSizeMapping> list = controller.getProductSizes(INVALID_ID);
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}	
	
	
	@Test
	public void addProductSize_should_return_notnull_size() {
		productSize.setId(DEFAULT_ID);
		ProductSizeMapping obj = controller.addProductSize(productSize, DEFAULT_ID);
		Assertions.assertThat(obj).isNotNull();
	}	
	
	@Test
	public void editProduct_should_return_updated_size() {
		productSize.setName("Updated");
		ProductSize obj = controller.editProduct(productSize, DEFAULT_ID);
		Assertions.assertThat(obj).isNotNull();
		Assertions.assertThat(obj.getName()).isEqualTo("Updated");
	}
	
	@Test
	public void addNewProductSize_should_return_notnull_size() {
		ProductSize productSize = new ProductSize();
		productSize.setName("New Size");
		ProductSize obj = controller.addNewProductSize(productSize);
		Assertions.assertThat(obj).isNotNull();
		Assertions.assertThat(obj.getName()).isEqualTo("New Size");

	}
	
}
