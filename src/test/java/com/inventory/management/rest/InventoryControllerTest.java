package com.inventory.management.rest;

import org.junit.Test;
import org.junit.Before;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventory.management.BaseTestCase;
import com.inventory.management.exception.BadRequestBodyException;
import com.inventory.management.model.InventoryRestDto;
import com.inventory.management.model.Product;
import com.inventory.management.model.Warehouse;
import com.inventory.management.model.reference.ProductSize;

public class InventoryControllerTest extends BaseTestCase {
	
	@Autowired
	private InventoryController controller;
	
	private final InventoryRestDto inventoryDto = new InventoryRestDto();
	
	@Before
	public void setup() {
		
		Product product = new Product();
		Warehouse warehouse = new Warehouse();
		ProductSize productSize = new ProductSize();
		
		product.setId(DEFAULT_ID);
		warehouse.setId(DEFAULT_ID);
		productSize.setId(DEFAULT_ID);
		
		inventoryDto.setProduct(product);
		inventoryDto.setWarehouse(warehouse);
		inventoryDto.setProductSize(productSize);
		inventoryDto.setQpb(DEFAULT_QUANTITY);
		inventoryDto.setAvailableQuantity(DEFAULT_QUANTITY);
		inventoryDto.setInStockQuantity(DEFAULT_QUANTITY);
	}

	@Test
	public void listAllInventories_of_product_in_company_should_return_not_empty_list() {
		List<InventoryRestDto> list = controller.listAllInventories(DEFAULT_ID);
		Assertions.assertThat(list).isNotNull().isNotEmpty();
		
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void listAllInventories_of_product_in_company_with_invalid_id_should_throw_exception() {
		controller.listAllInventories(INVALID_ID);
	}
	
	@Test
	public void getInventoryByWarehouse_of_product_in_all_warehouse_should_return_not_empty_list() {
		List<InventoryRestDto> list = controller.getInventoryByWarehouse(DEFAULT_ID, DEFAULT_ID );
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void getInventoryByWarehouse_of_product_in_all_warehouse_with_invalid_id_should_throw_exception() {
		List<InventoryRestDto> list = controller.getInventoryByWarehouse(DEFAULT_ID, INVALID_ID );
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getInventoryByOffice_of_product_in_all_office_should_return_not_empty_list() {
		List<InventoryRestDto> list = controller.getInventoryByOffice(DEFAULT_ID, DEFAULT_ID);
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void setWarehouseInventory_update_product_quantity_in_warehouses_should_return_updated_inventory() {
		List<InventoryRestDto> list = controller.setWarehouseInventory(inventoryDto, DEFAULT_ID);
		assertInventoryForUpdate(list);
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void setWarehouseInventory_update_product_quantity_in_warehouses_with_invalid_id_should_throw_exception() {
		List<InventoryRestDto> list = controller.setWarehouseInventory(inventoryDto, INVALID_ID);
		assertInventoryForUpdate(list);
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void setOfficeInventory_update_product_quantity_in_office_with_invalid_id_should_throw_exception() {
		List<InventoryRestDto> list = controller.setOfficeInventory(inventoryDto, INVALID_ID);
		assertInventoryForUpdate(list);
	}
	
	@Test()
	public void setCompanyInventory_update_product_quantity_all_warehouses_should_return_updated_inventory() throws BadRequestBodyException {
		List<InventoryRestDto> list = controller.setCompanyInventory(inventoryDto);
		assertInventoryForUpdate(list);
	}
	
	private void assertInventoryForUpdate(List<InventoryRestDto> inventories) {
		Assertions.assertThat(inventories).isNotNull().isNotEmpty();

		inventories.forEach(in -> {
			Assertions.assertThat(in.getProduct().getId()).isEqualTo(DEFAULT_ID);
			Assertions.assertThat(in.getQpb()).isEqualTo(DEFAULT_QUANTITY);
			Assertions.assertThat(in.getInStockQuantity()).isEqualTo(DEFAULT_QUANTITY);
			Assertions.assertThat(in.getAvailableQuantity()).isEqualTo(DEFAULT_QUANTITY);

		});
	}
}
