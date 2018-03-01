package com.inventory.management.services;

import org.junit.Test;
import org.junit.Before;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventory.management.BaseTestCase;
import com.inventory.management.model.Inventory;
import com.inventory.management.model.InventoryKey;
import com.inventory.management.model.Office;
import com.inventory.management.model.Product;
import com.inventory.management.model.Warehouse;
import com.inventory.management.model.reference.ProductSize;

public class InventoryServiceTest extends BaseTestCase {

	@Autowired
	private InventoryService inventoryService;
	
	private final InventoryKey inventoryKey = new InventoryKey();
	private final Product defaultProduct = new Product();
	private final Office defaultOffice = new Office();
	private final Warehouse defaultWarehouse = new Warehouse();
	private final ProductSize defaultSize = new ProductSize();
	
	@Before
	public void setup() {

		defaultProduct.setId(DEFAULT_ID);
		defaultWarehouse.setId(DEFAULT_ID);
		defaultSize.setId(DEFAULT_ID);
		defaultOffice.setId(DEFAULT_ID);
		
		inventoryKey.setProduct(defaultProduct);
		inventoryKey.setWarehouse(defaultWarehouse);
		inventoryKey.setSize(defaultSize);
	}
	
	@Test
	public void getAllInventories_should_return_not_empty_list(){
			
		List<Inventory> list = inventoryService.getAllInventories();
		Assertions.assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	public void getAllInventoriesByWarehouse_should_return_not_empty_list(){
		
		List<Inventory> list = inventoryService.getAllInventoriesByWarehouse(DEFAULT_ID);
		Assertions.assertThat(list).isNotNull();
	}
	
	@Test
	public void getAllInventoriesByOffice_should_return_not_empty_list(){
		
		List<Inventory> list = inventoryService.getAllInventoriesByOffice(DEFAULT_ID);
		Assertions.assertThat(list).isNotNull();
	}
	
	@Test
	public void getInventory_should_return_not_empty_list(){
		
		List<Inventory> list = inventoryService.getInventory(DEFAULT_ID, DEFAULT_ID);
		Assertions.assertThat(list).isNotNull();
	}
	
	@Test
	public void getInventory_should_return_not_null_inventory(){
		
		Inventory inventory = inventoryService.getInventory(inventoryKey);
		Assertions.assertThat(inventory).isNotNull();
		Assertions.assertThat(inventory.getKey()).isNotNull();
	}
	
	@Test
	public void saveInventory_should_update_field() {
		
		Inventory inventory = inventoryService.getInventory(inventoryKey);
		inventory.setQpb(DEFAULT_QUANTITY);
		inventory = inventoryService.saveInventory(inventory);
		
		Assertions.assertThat(inventory).isNotNull();
		Assertions.assertThat(inventory.getKey()).isNotNull();
		Assertions.assertThat(inventory.getQpb()).isEqualTo(DEFAULT_QUANTITY);
	}
	
	@Test
	public void updateProductInventoryInOffice_should_update_values(){
		Inventory paramInventory = prepareInventoryForUpdate();
		
		List<Inventory> inventories = inventoryService.updateProductInventoryInOffice(paramInventory, defaultProduct, defaultOffice);
		
		assertInventoryForUpdate(inventories);
	}
	
	@Test
	public void updateProductInventoryInOffice_without_product_sizes_should_update_values(){
		Inventory paramInventory = prepareInventoryForUpdate();
		paramInventory.getKey().setSize(null);
		
		List<Inventory> inventories = inventoryService.updateProductInventoryInOffice(paramInventory, defaultProduct, defaultOffice);
		
		assertInventoryForUpdate(inventories);
	}
	
	@Test
	public void updateProductInventoryInCompany_without_product_sizes_should_update_values(){
		Inventory paramInventory = prepareInventoryForUpdate();
		paramInventory.getKey().setSize(null);
		List<Inventory> inventories = inventoryService.updateProductInventoryInCompany(paramInventory, defaultProduct);
			
		assertInventoryForUpdate(inventories);
	}
	
	@Test
	public void updateProductInventoryInWarehouse_without_product_sizes_should_update_values(){
		Inventory paramInventory = prepareInventoryForUpdate();
		paramInventory.getKey().setSize(null);
		
		List<Inventory> inventories = inventoryService.updateProductInventoryInWarehouse(paramInventory, defaultProduct, defaultWarehouse); 
		assertInventoryForUpdate(inventories);
	}
	
	private Inventory prepareInventoryForUpdate() {
		Inventory paramInventory = new Inventory();
		paramInventory.setInStockQuantity(DEFAULT_QUANTITY);
		paramInventory.setQpb(DEFAULT_QUANTITY);
		
		InventoryKey key = new InventoryKey();
		key.setProduct(defaultProduct);
		key.setWarehouse(defaultWarehouse);
		key.setSize(defaultSize);
		paramInventory.setKey(key);
		
		return paramInventory;
	}
	
	private void assertInventoryForUpdate(List<Inventory> inventories) {
		Assertions.assertThat(inventories).isNotNull().isNotEmpty();
		Assertions.assertThat(inventories.size()).isGreaterThan(1);

		inventories.forEach(in -> {
			Assertions.assertThat(in.getQpb()).isEqualTo(DEFAULT_QUANTITY);
			Assertions.assertThat(in.getInStockQuantity()).isEqualTo(DEFAULT_QUANTITY);

		});
	}
}
