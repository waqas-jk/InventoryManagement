package com.inventory.management.rest;

import org.junit.Test;
import org.junit.Before;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventory.management.BaseTestCase;
import com.inventory.management.model.Office;
import com.inventory.management.model.Warehouse;

public class WarehouseControllerTest extends BaseTestCase {
	
	@Autowired
	private WarehouseController controller;
	
	private final Warehouse warehouse = new Warehouse();
	
	@Before
	public void setup() {
		
		Office office = new Office();
		office.setId(DEFAULT_ID);
		
		warehouse.setOffice(office);
	}
	

	@Test
	public void listAllWarehouses_should_return_not_empty_list() {
		List<Warehouse> list = controller.listAllWarehouses();
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	public void getWarehouseById_should_return_not_null() {
		Warehouse obj = controller.getWarehouseById(DEFAULT_ID);
		Assertions.assertThat(obj).isNotNull();
		Assertions.assertThat(obj.getId()).isEqualTo(DEFAULT_ID);
		
	}

	public void deleteWarehouse_should_mark_as_deleted() {
		Warehouse obj = controller.deleteWarehouse(DEFAULT_ID);
		Assertions.assertThat(obj).isNotNull();
		Assertions.assertThat(obj.getIsDeleted()).isTrue();
		
	}	

	public void editWarehouse_should_update_field() {
		warehouse.setCity("Test City");
		Warehouse obj = controller.editWarehouse(warehouse, DEFAULT_ID);
		
		Assertions.assertThat(obj).isNotNull();
		Assertions.assertThat(obj.getCity()).isEqualTo("Test City");
	}

	public void addNewWarehouse_should_return_added_object() {
		Warehouse obj = controller.addNewWarehouse(warehouse);
		Assertions.assertThat(obj).isNotNull();
		Assertions.assertThat(obj.getCity()).isEqualTo("Test City");
		
	}
}
