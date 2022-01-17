package com.example.demo.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(SampleController.class)
class SampleControllerTest {

	@Autowired
	private MockMvc mockMvc;// 모의 객체 : "흉내"내는 "가짜" 모듈
	@Test
	void testGreeting() throws Exception {
//		fail("Not yet implemented");
//		MockHttpServletRequestBuilder mockRequestBuilder = MockMvcRequestBuilders.get("/greeting");
//		MockHttpServletRequestBuilder mockRequestBuilder1 = mockRequestBuilder.accept(MediaType.APPLICATION_JSON);
		
		MockHttpServletRequestBuilder mockRequestBuilder = MockMvcRequestBuilders.get("/greeting").accept(MediaType.APPLICATION_JSON);
		
		ResultActions resultActions = mockMvc.perform(mockRequestBuilder);
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
		resultActions.andExpect(MockMvcResultMatchers.content().string("Welcome"));
	}

	@Test
	void testFooter() throws Exception {
		MockHttpServletRequestBuilder mockRequestBuilder = MockMvcRequestBuilders.get("/footer").accept(MediaType.APPLICATION_JSON);
//		mockRequestBuilder.param("test", "요청전달데이터");
		ResultActions resultActions = mockMvc.perform(mockRequestBuilder);
		
		String expectedViewName = "footer";
		
		ResultMatcher matcher = MockMvcResultMatchers.view().name(expectedViewName);
		resultActions.andExpect(matcher);
		
	}
}
