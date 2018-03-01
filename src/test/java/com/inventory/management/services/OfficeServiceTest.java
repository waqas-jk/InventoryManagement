package com.inventory.management.services;

import org.junit.Test;
import org.junit.Before;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventory.management.BaseTestCase;
import com.inventory.management.model.Office;
import com.inventory.management.model.Warehouse;
import com.inventory.management.model.reference.Country;

public class OfficeServiceTest extends BaseTestCase {

	@Autowired
	private OfficeService officeService;
	
	private final Office defaultOffice = new Office();
	private final Warehouse defaultWarehouse = new Warehouse();
	
	@Before
	public void setup() {

		defaultWarehouse.setId(DEFAULT_ID);
		defaultOffice.setId(DEFAULT_ID);
	}
	
	@Test()
	public void getAllOffices_should_return_not_empty_list(){
		List<Office> list = officeService.getAllOffices();
		Assertions.assertThat(list).isNotNull().isNotEmpty();

	}
	
	@Test
	public void getOffice_should_return_not_null(){
		Office office = officeService.getOffice(DEFAULT_ID);
		Assertions.assertThat(office).isNotNull();
		Assertions.assertThat(office.getId()).isNotNull().isEqualTo(DEFAULT_ID);
	}
	
	@Test
	public void getOfficeByCountryId_should_return_not_null(){
		Office office = officeService.getOfficeByCountryId(DEFAULT_ID);
		Assertions.assertThat(office).isNotNull();
		Assertions.assertThat(office.getCountry().getId()).isNotNull().isEqualTo(DEFAULT_ID);
	}
	
	@Test
	public void getOffice_by_country_should_return_not_null(){
		Office office = officeService.getOffice("USA");
		Assertions.assertThat(office).isNotNull();
		Assertions.assertThat(office.getCountry().getName()).isNotNull().isEqualTo("USA");
	}
	
	@Test
	public void saveOffice_should_update_field(){
		defaultOffice.setName("Test Office");
		Country country = new Country();
		country.setId(DEFAULT_ID);
		defaultOffice.setCountry(country);
		defaultOffice.setCity("Test");
		Office office = officeService.saveOffice(defaultOffice);
		Assertions.assertThat(office).isNotNull();
		Assertions.assertThat(office.getId()).isNotNull();
		Assertions.assertThat(office.getName()).isNotNull().isEqualTo("Test Office");
	}
}
