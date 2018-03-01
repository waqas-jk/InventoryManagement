package com.inventory.management.rest;

import org.junit.Test;
import org.junit.Before;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventory.management.BaseTestCase;
import com.inventory.management.model.Inventory;
import com.inventory.management.model.InventoryKey;
import com.inventory.management.model.InventoryRestDto;
import com.inventory.management.model.Product;
import com.inventory.management.model.Warehouse;
import com.inventory.management.model.reference.ProductSize;

public class InventoryBeanConverterTest extends BaseTestCase {
	
	@Autowired
	private InventoryBeanConverter converter;
	
	private Inventory inventory= new Inventory();
	private InventoryRestDto inventoryDto = new InventoryRestDto();
	
	@Before
	public void setup() {
		
		Product product = new Product();
		product.setId(DEFAULT_ID);
		
		ProductSize productSize = new ProductSize();
		productSize.setId(DEFAULT_ID);
		
		Warehouse warehouse = new Warehouse();
		warehouse.setId(DEFAULT_ID);
		
		inventoryDto.setProduct(product);
		inventoryDto.setProductSize(productSize);
		inventoryDto.setWarehouse(warehouse);
		inventoryDto.setMoq(DEFAULT_QUANTITY);
		inventoryDto.setQpb(DEFAULT_QUANTITY);
		inventoryDto.setReorderPoint(DEFAULT_QUANTITY);
		inventoryDto.setInStockQuantity(DEFAULT_QUANTITY);
		inventoryDto.setInTransitQuantity(DEFAULT_QUANTITY);
		inventoryDto.setAvailableQuantity(DEFAULT_QUANTITY);
		
		InventoryKey inventoryKey = new InventoryKey();
		inventoryKey.setProduct(product);
		inventoryKey.setSize(productSize);
		inventoryKey.setWarehouse(warehouse);
		
		inventory.setKey(inventoryKey);
		inventory.setMoq(DEFAULT_QUANTITY);
		inventory.setQpb(DEFAULT_QUANTITY);
		inventory.setReorderPoint(DEFAULT_QUANTITY);
		inventory.setInStockQuantity(DEFAULT_QUANTITY);
		inventory.setInTransitQuantity(DEFAULT_QUANTITY);
		inventory.setAvailableQuantity(DEFAULT_QUANTITY);
	}
	
	@Test
	public void convert_should_convert_inventory_object_to_rest_dto() {
		InventoryRestDto dao = converter.convert(inventory);
		Assertions.assertThat(dao).isNotNull();
		Assertions.assertThat(dao.getProduct().getId()).isEqualTo(DEFAULT_ID);
		Assertions.assertThat(dao.getProductSize().getId()).isEqualTo(DEFAULT_ID);
		Assertions.assertThat(dao.getWarehouse().getId()).isEqualTo(DEFAULT_ID);
		Assertions.assertThat(dao.getAvailableQuantity()).isEqualTo(DEFAULT_QUANTITY);
		Assertions.assertThat(dao.getInStockQuantity()).isEqualTo(DEFAULT_QUANTITY);
		Assertions.assertThat(dao.getInTransitQuantity()).isEqualTo(DEFAULT_QUANTITY);
		Assertions.assertThat(dao.getMoq()).isEqualTo(DEFAULT_QUANTITY);
		Assertions.assertThat(dao.getQpb()).isEqualTo(DEFAULT_QUANTITY);
		Assertions.assertThat(dao.getReorderPoint()).isEqualTo(DEFAULT_QUANTITY);
	}
	
	public void convert_should_convert_rest_dto_to_inventory_object() {
		Inventory inventory = converter.convert(inventoryDto);

		Assertions.assertThat(inventory).isNotNull();
		Assertions.assertThat(inventory.getKey().getProduct().getId()).isEqualTo(DEFAULT_ID);
		Assertions.assertThat(inventory.getKey().getSize().getId()).isEqualTo(DEFAULT_ID);
		Assertions.assertThat(inventory.getKey().getWarehouse().getId()).isEqualTo(DEFAULT_ID);
		Assertions.assertThat(inventory.getAvailableQuantity()).isEqualTo(DEFAULT_QUANTITY);
		Assertions.assertThat(inventory.getInStockQuantity()).isEqualTo(DEFAULT_QUANTITY);
		Assertions.assertThat(inventory.getInTransitQuantity()).isEqualTo(DEFAULT_QUANTITY);
		Assertions.assertThat(inventory.getMoq()).isEqualTo(DEFAULT_QUANTITY);
		Assertions.assertThat(inventory.getQpb()).isEqualTo(DEFAULT_QUANTITY);
		Assertions.assertThat(inventory.getReorderPoint()).isEqualTo(DEFAULT_QUANTITY);
		
	}

}
