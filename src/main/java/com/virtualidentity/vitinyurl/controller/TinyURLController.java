package com.virtualidentity.vitinyurl.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualidentity.vitinyurl.entity.GlobalTinyURL;
import com.virtualidentity.vitinyurl.model.TinyURLDTO;
import com.virtualidentity.vitinyurl.service.GlobalTinyURLService;
import com.virtualidentity.vitinyurl.service.UserService;
import com.virtualidentity.vitinyurl.utils.APIConstant;
import com.virtualidentity.vitinyurl.utils.VITinyURLConsants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/shortenurl")
@Tag(name = "VI tinyurl Creation API")
public class TinyURLController {
	
	UserService userService;

	GlobalTinyURLService globalTinyURLServiceImpl;

	TinyURLController(UserService userService, GlobalTinyURLService globalTinyURLServiceImpl) {
		this.userService = userService;
		this.globalTinyURLServiceImpl = globalTinyURLServiceImpl;
	}

	@ApiResponses({ @ApiResponse(responseCode = "201", description = APIConstant.API_TINYURL_CREATED),
			@ApiResponse(responseCode = "401", description = APIConstant.API_AUTH_FAILED),
			@ApiResponse(responseCode = "403", description = APIConstant.API_ACCESS_FAILED),
			@ApiResponse(responseCode = "404", description = APIConstant.API_RESOURCE_NOT_FOUND),
			@ApiResponse(responseCode = "500", description = APIConstant.API_SERVER_ERROR) })
	@Operation(summary = VITinyURLConsants.TINYURL_CREATE)
	@PostMapping
	public ResponseEntity<GlobalTinyURL> createShortenUrl(@RequestBody TinyURLDTO tinyURLDTO) {
		GlobalTinyURL globalTinyURL = userService.createShortenUrl(tinyURLDTO);
		String shortenUrl = VITinyURLConsants.DOMAIN + globalTinyURL.getShortUrl();
		globalTinyURL.setShortUrl(shortenUrl);
		return new ResponseEntity<GlobalTinyURL>(globalTinyURL, HttpStatus.CREATED);
	}

	@GetMapping("/{shortenedUrl}")
	public ResponseEntity<Void> redirect(@PathVariable String shortenedUrl) {
		GlobalTinyURL url = globalTinyURLServiceImpl.getOriginalUrl(shortenedUrl);
		URI uri = URI.create(url.getOriginalUrl());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
	}
}