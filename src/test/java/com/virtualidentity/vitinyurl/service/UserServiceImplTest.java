package com.virtualidentity.vitinyurl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.virtualidentity.vitinyurl.entity.GlobalTinyURL;
import com.virtualidentity.vitinyurl.exception.JWTTokenNotFoundException;
import com.virtualidentity.vitinyurl.model.TinyURLDTO;
import com.virtualidentity.vitinyurl.repository.UserRepository;

@SpringBootTest
public class UserServiceImplTest {
	@MockBean
	private UserRepository userRepository;
	@MockBean
	private NewUserService newUserService;
	@MockBean
	private GlobalTinyURLService globalTinyURLService;
	@MockBean
	private TinyURLService tinyURLService;

	@Autowired
	private UserServiceImpl userService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateShortenUrlWithExistingGlobalTinyURLAndEmptyJwtToken() {
		// Arrange
		TinyURLDTO tinyURLDTO = new TinyURLDTO();
		tinyURLDTO.setOriginalUrl("https://nautil.us/to-supercharge-learning-look-to-play-292946/?utm_source=pocket-newtab-intl-en");

		GlobalTinyURL globalTinyURL = new GlobalTinyURL();
		globalTinyURL.setOriginalUrl("https://nautil.us/to-supercharge-learning-look-to-play-292946/?utm_source=pocket-newtab-intl-en");
		globalTinyURL.setShortUrl("http://localhost:8080/vitinyurl/shortenurl/bHQarx0J");

		when(globalTinyURLService.getGlobalTinyURL(any(String.class), any(Boolean.class))).thenReturn(globalTinyURL);

		// Act
		GlobalTinyURL globalTinyURL1 = userService.createShortenUrl(tinyURLDTO);

		// Assert
		assertEquals(globalTinyURL.getShortUrl(), globalTinyURL1.getShortUrl());
	}

	@Test
	public void testCreateShortenUrlWithNonExistingGlobalTinyURLAndEmptyJwtToken() {
		// Arrange
		TinyURLDTO tinyURLDTO = new TinyURLDTO();
		tinyURLDTO.setOriginalUrl("https://nautil.us/to-supercharge-learning-look-to-play-292946/?utm_source=pocket-newtab-intl-en");

		GlobalTinyURL globalTinyURL = new GlobalTinyURL();
		globalTinyURL.setOriginalUrl("https://nautil.us/to-supercharge-learning-look-to-play-292946/?utm_source=pocket-newtab-intl-en");
		globalTinyURL.setShortUrl("http://localhost:8080/vitinyurl/shortenurl/bHQarx0J");

		when(globalTinyURLService.getGlobalTinyURL(any(String.class), any(Boolean.class))).thenReturn(null);
		when(globalTinyURLService.createGlobalTinyURL(any(TinyURLDTO.class))).thenReturn(globalTinyURL);

		// Act
		GlobalTinyURL globalTinyURL1 = userService.createShortenUrl(tinyURLDTO);

		// Assert
		assertEquals(globalTinyURL.getShortUrl(), globalTinyURL1.getShortUrl());
	}

	@Test
	public void testCreateShortenUrlWithInvalidJwtToken() {
		// Arrange
		TinyURLDTO tinyURLDTO = new TinyURLDTO();
		tinyURLDTO.setOriginalUrl("https://nautil.us/to-supercharge-learning-look-to-play-292946/?utm_source=pocket-newtab-intl-en");
		tinyURLDTO.setJwtToken("abcded");

		// Act
		assertThrows(JWTTokenNotFoundException.class, () -> userService.createShortenUrl(tinyURLDTO));
	}

}
