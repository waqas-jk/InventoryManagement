package com.inventory.management.rest.IT;

import org.junit.Test;
import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.inventory.management.model.Office;
import com.inventory.management.model.reference.Country;

public class OfficeControllerITTest extends BaseTestCaseIT{
	
	private String baseUrl = "/office";
	
	private Office office = new Office();
	
	@Before
	public void setup() {
		
		Country country = new Country();
		country.setId(5L);
		
		office.setCountry(country);
		office.setCity("London");
		office.setName("Office Name");
	}
	
    @Test
	public void listAllOffices() throws Exception {
    	verifyOK(createGetRequest(baseUrl, "/list").andDo(MockMvcResultHandlers.print()));
    }
	
	@Test
	public void getOfficeById() throws Exception {
		verifyOK(createGetRequest(baseUrl, "/view/" + DEFAULT_ID).andDo(MockMvcResultHandlers.print()));
	}	
	
	@Test
	public void getOfficeByCountryId() throws Exception {
		verifyOK(createGetRequest(baseUrl, "/view/country/" + DEFAULT_ID).andDo(MockMvcResultHandlers.print()));
	}	
	
	@Test(expected=Exception.class)
	public void getOfficeById_should_throw_exception()  throws Exception {
		createGetRequest(baseUrl, "/view/" + INVALID_ID).andDo(MockMvcResultHandlers.print())
	    	.andExpect(MockMvcResultMatchers.status().isInternalServerError())
	    	.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}	
	
	@Test
	public void getWarehouseByOffice() throws Exception {
		verifyOK(createGetRequest(baseUrl, "/" + DEFAULT_ID + "/view/warehouse").andDo(MockMvcResultHandlers.print()));
	}	
	
	@Test
	public void addNewOffice()  throws Exception {
		office.setId(null);
		verifyOK(createPostRequest(baseUrl, "/new", office).andDo(MockMvcResultHandlers.print()));
	}
	
}
