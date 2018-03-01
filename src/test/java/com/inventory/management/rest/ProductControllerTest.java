package com.inventory.management.rest;

import org.junit.Test;
import org.junit.Before;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventory.management.BaseTestCase;
import com.inventory.management.model.Brand;
import com.inventory.management.model.Product;
import com.inventory.management.model.reference.ProductType;

public class ProductControllerTest extends BaseTestCase {
	
	@Autowired
	private ProductController controller;
	
	private final Product product = new Product();
	
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
	public void listAllProducts_should_return_not_empty_list() {
		List<Product> list= controller.listAllProducts();	
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getProductById_should_return_notnull_product() {
		Product product = controller.getProductById(DEFAULT_ID);
		Assertions.assertThat(product).isNotNull();	
		Assertions.assertThat(product.getId()).isEqualTo(DEFAULT_ID);	

	}
	
	@Test(expected=EntityNotFoundException.class)
	public void getProductById_should_throw_exception() {
		controller.getProductById(INVALID_ID);
	}
	
	@Test
	public void deleteProduct_should_mark_product_as_deleted() {
		Product product = controller.deleteProduct(DEFAULT_ID);
		Assertions.assertThat(product).isNotNull();	
		Assertions.assertThat(product.getIsDeleted()).isTrue();	
	}	
	
	@Test(expected=EntityNotFoundException.class)
	public void deleteProduct_should_throw_exception() {
		controller.deleteProduct(INVALID_ID);
	}	
	
	@Test
	public void editProduct_should_return_update_product() {
		product.setId(DEFAULT_ID);
		product.setName("Updated Product");

		Product obj = controller.editProduct(product, DEFAULT_ID);
		Assertions.assertThat(obj).isNotNull();	
		Assertions.assertThat(obj.getName()).isEqualTo("Updated Product");	

	}
	
	@Test(expected=EntityNotFoundException.class)
	public void editProduct__should_throw_exception() {
		controller.editProduct(product, INVALID_ID);

	}
	
	@Test
	public void addNewProduct_should_return_notnull_product() {
		Product obj = controller.addNewProduct(product);
		
		Assertions.assertThat(obj).isNotNull();	
		Assertions.assertThat(obj).isNotNull();	

	}
}
