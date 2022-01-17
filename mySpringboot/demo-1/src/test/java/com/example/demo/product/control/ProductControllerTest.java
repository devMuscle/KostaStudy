package com.example.demo.product.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


//@WebMvcTest(ProductController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc; // 모의 객체 : "흉내"내는 "가짜" 모듈
	
	@Test
	void testList() throws Exception {
		MockHttpServletRequestBuilder  mockRequestBuilder =
				MockMvcRequestBuilders.get("/productlist");
//		mockRequestBuilder.param("test", "요청전달데이터");
		ResultActions resultActions = mockMvc.perform(mockRequestBuilder);
		String expectedViewName = "productlistresult";
		ResultMatcher matcher = MockMvcResultMatchers.view().name(expectedViewName);
		resultActions.andExpect(matcher);
	}
}


