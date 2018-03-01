package com.inventory.management.services;

import org.junit.Test;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventory.management.BaseTestCase;
import com.inventory.management.model.Brand;
import com.inventory.management.model.reference.Country;
import com.inventory.management.model.reference.ProductSize;
import com.inventory.management.model.reference.ProductType;

public class ReferenceServiceTest extends BaseTestCase {

	@Autowired
	private ReferenceEntityService refService;
	
	@Test
	public void getAllProductSizes_should_return_not_empty_list(){
		List<ProductSize> list = refService.getAllProductSizes();
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getProductSize_should_return_not_null() {
		ProductSize size = refService.getProductSize(DEFAULT_ID);
		
		Assertions.assertThat(size).isNotNull();
		Assertions.assertThat(size.getId()).isNotNull().isEqualTo(DEFAULT_ID);
	}
	
	@Test
	public void saveProductSize(){
		ProductSize productSize = new ProductSize();
		productSize.setName("Test Size");
		ProductSize size = refService.saveProductSize(productSize);
		
		Assertions.assertThat(size).isNotNull();
		Assertions.assertThat(size.getId()).isNotNull();
		Assertions.assertThat(size.getName()).isNotNull().isEqualTo("Test Size");
	}
	
	@Test
	public void getAllProductTypes_should_return_not_empty_list(){
		List<ProductType> list = refService.getAllProductTypes();
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getProductType_should_return_not_null() {
		ProductType type = refService.getProductType(DEFAULT_ID);
		
		Assertions.assertThat(type).isNotNull();
		Assertions.assertThat(type.getId()).isNotNull().isEqualTo(DEFAULT_ID);
	}
	
	@Test
	public void getAllCountries_should_return_not_empty_list(){
		List<Country> list = refService.getAllCountries();
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getCountry_should_return_not_null() {
		Country country = refService.getCountry(DEFAULT_ID);
		
		Assertions.assertThat(country).isNotNull();
		Assertions.assertThat(country.getId()).isNotNull().isEqualTo(DEFAULT_ID);
	}
	
	@Test
	public void getBrand_should_return_not_null() {
		Brand brand = refService.getBrand(DEFAULT_ID);
		
		Assertions.assertThat(brand).isNotNull();
		Assertions.assertThat(brand.getId()).isNotNull().isEqualTo(DEFAULT_ID);
	}
}
