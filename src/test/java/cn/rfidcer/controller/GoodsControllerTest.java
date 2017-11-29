package cn.rfidcer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import cn.rfidcer.DefaultControllerContext;

public class GoodsControllerTest extends DefaultControllerContext {
	@Test
	public void getAccount() throws Exception {
		this.mockMvc.perform(get("/goods/getGoods").accept(MediaType.parseMediaType( "application/json;charset=UTF-8" )))
        		.andExpect(status().isOk())
        		.andExpect(content().contentType("application/json;charset=UTF-8"))
        		//.andExpect(jsonPath("$[0].length()").value("10"));
        		.andExpect(jsonPath("$[0]['goodsId']").value("f797a6ab320245e4a6aed804b076ec7a"));
		 
	}

}
