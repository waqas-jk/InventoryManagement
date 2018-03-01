package com.inventory.management.rest.IT;

import org.junit.Before;
import org.junit.runner.RunWith;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class BaseTestCaseIT{

	private static final Logger LOGGER = Logger.getLogger(BaseTestCaseIT.class); 

	protected static final Long DEFAULT_ID = 1L;
	protected static final Long INVALID_ID = 99L;

	protected static final Long DEFAULT_QUANTITY = 999L;
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Autowired
    protected  WebApplicationContext applicationContext;

	protected MockMvc mockMVC;
	
	protected ObjectMapper mapper = new ObjectMapper();

    @Before
	public void setUp() {
    	
        mockMVC = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }
    
    protected ResultActions createGetRequest(String baseUrl, String url) throws Exception {
    	return mockMVC.perform(get(baseUrl + url).accept(MediaType.APPLICATION_JSON));
    }
    
    protected ResultActions createPutRequest(String baseUrl, String url, Object data) throws Exception {
    	LOGGER.info("Put JSON ===>" + mapper.writer().writeValueAsString(data));
    	return mockMVC.perform(put(baseUrl + url).contentType(APPLICATION_JSON_UTF8).content(mapper.writer().writeValueAsString(data)).accept(APPLICATION_JSON_UTF8));
    }

    protected ResultActions createPostRequest(String baseUrl, String url, Object data) throws Exception {
    	LOGGER.info("Post JSON ===>" + mapper.writer().writeValueAsString(data));
    	return mockMVC.perform(post(baseUrl + url).contentType(APPLICATION_JSON_UTF8).content(mapper.writer().writeValueAsString(data)).accept(MediaType.APPLICATION_JSON));
    }
    
    protected ResultActions createDeleteRequest(String baseUrl, String url, Object data) throws Exception {
    	return mockMVC.perform(delete(baseUrl + url).accept(MediaType.APPLICATION_JSON));
    }
    
    protected void verifyOK(ResultActions resultActions) throws Exception {
    	resultActions.andExpect(MockMvcResultMatchers.status().isOk())
    	.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
	
}
