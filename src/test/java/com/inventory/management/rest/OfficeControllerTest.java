package com.inventory.management.rest;

import org.junit.Test;
import org.junit.Before;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventory.management.BaseTestCase;
import com.inventory.management.exception.OfficeAlreadyExistsException;
import com.inventory.management.model.Office;
import com.inventory.management.model.Warehouse;
import com.inventory.management.model.reference.Country;

public class OfficeControllerTest extends BaseTestCase {
	
	@Autowired
	private OfficeController controller;
	
	private final Office office = new Office();
	
	@Before
	public void setup() {
		
		Country country = new Country();
		country.setId(DEFAULT_ID);
		
		office.setCountry(country);
		office.setCity("London");
		office.setName("Office Name");
	}
	
	@Test
	public void listAllOffices_should_return_not_empty_list() {
		List<Office> list = controller.listAllOffices();
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getOfficeById_should_return_notnull_office() {
		Office obj = controller.getOfficeById(DEFAULT_ID);
		Assertions.assertThat(obj).isNotNull();
		Assertions.assertThat(obj.getId()).isEqualTo(DEFAULT_ID);
	}
	
	@Test
	public void getOfficeByCountry_should_return_notnull_office() {
		Office obj = controller.getOfficeByCountry("USA");
		Assertions.assertThat(obj).isNotNull();
		Assertions.assertThat(obj.getCountry().getName()).isEqualTo("USA");
			
	}
	
	@Test
	public  void getWarehouseByOffice_return_not_empty_list() {
		List<Warehouse> list = controller.getWarehouseByOffice(DEFAULT_ID);
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test(expected=OfficeAlreadyExistsException.class)
	public void addNewOffice_should_throw_exception() throws OfficeAlreadyExistsException {
		Office obj = controller.addNewOffice(office);
		Assertions.assertThat(obj).isNotNull();	
		Assertions.assertThat(obj.getCity()).isEqualTo("London");		
	}

	@Test
	public void addNewOffice_should_return_notnull_office() throws OfficeAlreadyExistsException {
		Country country = new  Country();
		country.setId(5L);
		office.setCountry(country);
		Office obj = controller.addNewOffice(office);
		Assertions.assertThat(obj).isNotNull();	
		Assertions.assertThat(obj.getCity()).isEqualTo("London");		
	}

}
