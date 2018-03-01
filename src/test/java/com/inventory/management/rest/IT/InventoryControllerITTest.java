package com.inventory.management.rest.IT;

import org.junit.Test;
import org.junit.Before;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.inventory.management.model.InventoryRestDto;
import com.inventory.management.model.Product;
import com.inventory.management.model.Warehouse;
import com.inventory.management.model.reference.ProductSize;

public class InventoryControllerITTest extends BaseTestCaseIT{
	
	private String baseUrl = "/inventory";
	
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
	public void listAllInventories() throws Exception {
		verifyOK(createGetRequest(baseUrl, "/product/" + DEFAULT_ID).andDo(MockMvcResultHandlers.print()));
	}	
	
	@Test
	public void getInventoryByWarehouse() throws Exception {
		verifyOK(createGetRequest(baseUrl, "/warehouse/" + DEFAULT_ID +"/product/" + DEFAULT_ID).andDo(MockMvcResultHandlers.print()));
	}	
	
	@Test
	public void getInventoryByOffice() throws Exception {
		verifyOK(createGetRequest(baseUrl, "/office/" + DEFAULT_ID + "/product/" + DEFAULT_ID).andDo(MockMvcResultHandlers.print()));
	}	
	
	@Test
	public void setWarehouseInventory()  throws Exception {
		verifyOK(createPutRequest(baseUrl, "/set/warehouse/" + DEFAULT_ID, inventoryDto).andDo(MockMvcResultHandlers.print()));
	}
	
	@Test
	public void setOfficeInventory()  throws Exception {
		verifyOK(createPutRequest(baseUrl, "/set/office/" + DEFAULT_ID, inventoryDto).andDo(MockMvcResultHandlers.print()));
	}
	
	@Test
	public void setCompanyInventory()  throws Exception {
		verifyOK(createPutRequest(baseUrl, "/set/all" , inventoryDto).andDo(MockMvcResultHandlers.print()));
	}
	
}
