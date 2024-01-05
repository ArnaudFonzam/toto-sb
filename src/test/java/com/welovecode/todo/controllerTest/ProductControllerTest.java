package com.welovecode.todo.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.welovecode.todo.controller.ProductController;
import com.welovecode.todo.service.IProductServices;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IProductServices service;

	@Test
	public void testGetProducts() throws Exception {
		mockMvc.perform(get("/products"))
			.andExpect(status().isOk());
	}

}
