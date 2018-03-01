package com.inventory.management.services;

import org.junit.Test;
import org.junit.Before;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventory.management.BaseTestCase;
import com.inventory.management.model.Office;
import com.inventory.management.model.Warehouse;

public class WarehouseServiceTest extends BaseTestCase {

	@Autowired
	private WarehouseService warehouseService;
	
	private final Warehouse defaultWarehouse = new Warehouse();
	
	@Before
	public void setup() {
		Office office = new Office();
		office.setId(DEFAULT_ID);
		
		defaultWarehouse.setOffice(office);
		defaultWarehouse.setCity("Test");
	}
	
	@Test()
	public void getAllWarehouses_should_return_not_empty_list(){
		List<Warehouse> list = warehouseService.getAllWarehouses();
		Assertions.assertThat(list).isNotNull().isNotEmpty();

	}
	
	@Test
	public void getAllWarehousesByOffice_should_return_not_empty_list(){
		List<Warehouse> list = warehouseService.getAllWarehousesByOffice(DEFAULT_ID);
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getAllWarehousesByCountryId_should_return_not_empty_list(){
		 List<Warehouse> list = warehouseService.getAllWarehousesByCountryId(DEFAULT_ID);
		 Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getAllWarehousesByCountryName_should_return_not_empty_list(){
		List<Warehouse> list = warehouseService.getAllWarehousesByCountryName("USA");
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getWarehouse_should_return_not_null(){
		Warehouse warehouse = warehouseService.getWarehouse(DEFAULT_ID);
		Assertions.assertThat(warehouse).isNotNull();
		Assertions.assertThat(warehouse.getId()).isNotNull().isEqualTo(DEFAULT_ID);
	}
	
	
	@Test
	public void saveWarehouse_should_update_field(){
		defaultWarehouse.setName("Test Warehouse");
		Office office = new Office();
		office.setId(DEFAULT_ID);
		defaultWarehouse.setOffice(office);
		defaultWarehouse.setCity("Test");
		Warehouse warehouse = warehouseService.saveWarehouse(defaultWarehouse);
		Assertions.assertThat(warehouse).isNotNull();
		Assertions.assertThat(warehouse.getId()).isNotNull();
		Assertions.assertThat(warehouse.getName()).isNotNull().isEqualTo("Test Warehouse");
	}
	
	@Test
	public void deleteWarehouse_should_mark_as_deleted(){
		
		Warehouse warehouse= new Warehouse();
		Office office = new Office();
		office.setId(DEFAULT_ID);
		
		warehouse.setName("Test Warehouse");
		warehouse.setOffice(office);
		warehouse.setCity("Test");
		warehouse = warehouseService.saveWarehouse(warehouse);
		
		warehouse = warehouseService.deleteWarehouse(warehouse);
		Assertions.assertThat(warehouse).isNotNull();
		Assertions.assertThat(warehouse.getId()).isNotNull().isEqualTo(warehouse.getId());
		Assertions.assertThat(warehouse.getIsDeleted()).isTrue();
	}
}
