package com.inventory.management.services;

import org.junit.Test;
import org.junit.Before;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventory.management.BaseTestCase;
import com.inventory.management.model.Brand;
import com.inventory.management.model.Product;
import com.inventory.management.model.ProductSizeMapping;
import com.inventory.management.model.reference.ProductSize;
import com.inventory.management.model.reference.ProductType;

public class ProductServiceTest extends BaseTestCase {

	@Autowired
	private ProductService productService;
	
	private final Product defaultProduct = new Product();
	private final ProductSize defaultSize = new ProductSize();
	
	@Before
	public void setup() {

		defaultProduct.setId(DEFAULT_ID);
		defaultSize.setId(DEFAULT_ID);
			
		Brand brand = new Brand();
		brand.setId(DEFAULT_ID);
		
		ProductType type = new ProductType();
		type.setId(DEFAULT_ID);
		defaultProduct.setBrand(brand);
		defaultProduct.setProductType(type);
	}
	
	@Test()
	public void getAllProducts_should_return_not_empty_list(){
		List<Product> list = productService.getAllProducts();
		Assertions.assertThat(list).isNotNull().isNotEmpty();

	}
	
	@Test
	public void getAllProductsByBrand_should_return_not_empty_list(){
		List<Product> list = productService. getAllProductsByBrand(DEFAULT_ID);
		Assertions.assertThat(list).isNotNull().isNotEmpty();

	}
	
	@Test
	public void getAllProductsByType_should_return_not_empty_list(){
		List<Product> list = productService.getAllProductsByType(DEFAULT_ID);
		Assertions.assertThat(list).isNotNull().isNotEmpty();

	}
	
	@Test
	public void getProduct_should_return_not_null(){
		Product product = productService.getProduct(DEFAULT_ID);
		Assertions.assertThat(product).isNotNull();
		Assertions.assertThat(product.getId()).isNotNull().isEqualTo(DEFAULT_ID);
	}
	
	@Test
	public void saveProduct_should_update_field(){
		defaultProduct.setName("Test");
		Product product = productService.saveProduct(defaultProduct);
		Assertions.assertThat(product).isNotNull();
		Assertions.assertThat(product.getId()).isNotNull();
		Assertions.assertThat(product.getName()).isNotNull().isEqualTo("Test");
	}
	
	@Test
	public void deleteProduct_should_mark_as_deleted(){
		defaultProduct.setId(null);
		defaultProduct.setName("Test");
		Product product = productService.saveProduct(defaultProduct);

		
		product = productService.deleteProduct(product.getId());
		Assertions.assertThat(product).isNotNull();
		Assertions.assertThat(product.getId()).isNotNull().isEqualTo(product.getId());
		Assertions.assertThat(product.getIsDeleted()).isTrue();
	}
	
	@Test
	public void getProductSizes_should_return_not_empty_list(){
		List<ProductSizeMapping> mappings =  productService.getProductSizes(DEFAULT_ID);
		Assertions.assertThat(mappings).isNotNull().isNotEmpty();

	}
	
	@Test
	public void saveProductSizeMapping_should_update_field(){
		ProductSizeMapping mapping = new ProductSizeMapping();
		mapping.setProduct(defaultProduct);
		mapping.setProductSize(defaultSize);
		mapping = productService.saveProductSizeMapping(mapping);
		
		Assertions.assertThat(mapping).isNotNull();
		Assertions.assertThat(mapping.getProduct().getId()).isNotNull().isEqualTo(DEFAULT_ID);
	}
}
