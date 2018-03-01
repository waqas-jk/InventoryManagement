package com.inventory.management.rest.IT;

import org.junit.Test;
import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.inventory.management.model.Office;
import com.inventory.management.model.Warehouse;

public class WarehouseControllerIT extends BaseTestCaseIT{
	
	private String baseUrl = "/warehouse";
	
	private final Warehouse warehouse = new Warehouse();
	
	@Before
	public void setup() {
		
		Office office = new Office();
		office.setId(DEFAULT_ID);
		
		warehouse.setOffice(office);
		warehouse.setName("New warehouse");
		warehouse.setCity("Test City");
	}
	
    @Test
	public void listAllWarehouses() throws Exception {
    	verifyOK(createGetRequest(baseUrl, "/list").andDo(MockMvcResultHandlers.print()));
    }
	
	@Test
	public void getWarehouseById() throws Exception {
		verifyOK(createGetRequest(baseUrl, "/view/" + DEFAULT_ID).andDo(MockMvcResultHandlers.print()));
	}	
	
	@Test(expected=Exception.class)
	public void getWarehouseById_should_throw_exception()  throws Exception {
		createGetRequest(baseUrl, "/view/" + INVALID_ID).andDo(MockMvcResultHandlers.print())
	    	.andExpect(MockMvcResultMatchers.status().isInternalServerError())
	    	.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}	
	
	@Test
	public void deleteWarehouse()  throws Exception {
		verifyOK(createPutRequest(baseUrl, "/delete/" + DEFAULT_ID, null).andDo(MockMvcResultHandlers.print()));
	}	
	
	@Test(expected=Exception.class)
	public void deleteWarehouse_should_throw_exception()  throws Exception {
		createPutRequest(baseUrl, "/delete/" + INVALID_ID, null).andDo(MockMvcResultHandlers.print())
    		.andExpect(MockMvcResultMatchers.status().isInternalServerError())
	    	.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}	
	
	@Test
	public void editWarehouse()  throws Exception {
		warehouse.setName("updated");
		verifyOK(createPutRequest(baseUrl, "/edit/" + DEFAULT_ID, warehouse).andDo(MockMvcResultHandlers.print()));
	}
	
	@Test(expected=Exception.class)
	public void editWarehouse_should_throw_exception()  throws Exception {
		warehouse.setName("updated");
		createPutRequest(baseUrl, "/edit/" + INVALID_ID, warehouse).andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isInternalServerError())
    	.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void addNewWarehouse()  throws Exception {
		warehouse.setId(null);
		verifyOK(createPostRequest(baseUrl, "/new", warehouse).andDo(MockMvcResultHandlers.print()));
	}
	
}
