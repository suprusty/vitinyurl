package com.virtualidentity.vitinyurl.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.virtualidentity.vitinyurl.service.NewUserService;

@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NewUserService userService;

	@Test
	public void testCreateUser() throws Exception {
		String jwtToken = "xyz+test@gmail.com";

		when(userService.createNewUser(any())).thenReturn(jwtToken);

		mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"name\": \"xyz\", \"email\": \"test@gmail.com\", \"password\": \"dummaypassword\" }"))
				.andExpect(status().isCreated()).andExpect(content().string("Dummy jwtToken -" + jwtToken));
	}
}
