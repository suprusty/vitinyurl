package com.virtualidentity.vitinyurl.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualidentity.vitinyurl.entity.GlobalTinyURL;
import com.virtualidentity.vitinyurl.model.TinyURLDTO;
import com.virtualidentity.vitinyurl.service.GlobalTinyURLService;
import com.virtualidentity.vitinyurl.service.UserService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class TinyURLControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private GlobalTinyURLService globalTinyURLService;

	@Autowired
	private TinyURLController tinyURLController;

	static TinyURLDTO tinyURLDTO = new TinyURLDTO();
	static GlobalTinyURL globalTinyURL = new GlobalTinyURL();

	static String expectedShortenUrl = "http://localhost:8080/vitinyurl/shortenurl/bHQarx0J";;

	@BeforeAll
	static void init() {
		globalTinyURL.setOriginalUrl(
				"https://nautil.us/to-supercharge-learning-look-to-play-292946/?utm_source=pocket-newtab-intl-en");
		globalTinyURL.setShortUrl("bHQarx0J");
		tinyURLDTO.setOriginalUrl(
				"https://nautil.us/to-supercharge-learning-look-to-play-292946/?utm_source=pocket-newtab-intl-en");
	}

	@Test
	public void testCreateShortenUrl() throws Exception {

		when(userService.createShortenUrl(tinyURLDTO)).thenReturn(globalTinyURL);

		mockMvc.perform(MockMvcRequestBuilders.post("/shortenurl/").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(tinyURLDTO))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.shortUrl").value(expectedShortenUrl));
	}

	@Test
	public void testRedirect() throws Exception {

		URI expectedUri = URI.create(globalTinyURL.getOriginalUrl());

		Mockito.when(globalTinyURLService.getOriginalUrl("bHQarx0J")).thenReturn(globalTinyURL);

		mockMvc.perform(MockMvcRequestBuilders.get("/shortenurl/{shortenedUrl}", "bHQarx0J"))
				.andExpect(status().isMovedPermanently())
				.andExpect(MockMvcResultMatchers.header().string("Location", expectedUri.toString()));
	}

	private String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
