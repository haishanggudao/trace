package cn.rfidcer.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType; 
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext; 

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration 
@ContextConfiguration(locations={"classpath:spring-config.xml", 
		"classpath:spring-mvc.xml"})
public class ExampleTests {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void getAccount() throws Exception {
		this.mockMvc.perform(get("/goods/getGoods")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
        		.andExpect(status().isOk())
        		.andExpect(content().contentType("application/json;charset=UTF-8"))
        		.andExpect(jsonPath("$[0]['goodsId']").value("f797a6ab320245e4a6aed804b076ec7a"));
		 
	}
}
