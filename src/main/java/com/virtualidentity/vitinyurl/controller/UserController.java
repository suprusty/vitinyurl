package com.virtualidentity.vitinyurl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualidentity.vitinyurl.entity.NewUser;
import com.virtualidentity.vitinyurl.service.NewUserService;
import com.virtualidentity.vitinyurl.utils.APIConstant;
import com.virtualidentity.vitinyurl.utils.VITinyURLConsants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "VI tinyurl User Creation API")
public class UserController {

	private NewUserService userService;

	UserController(NewUserService userService) {
		this.userService = userService;
	}

	@ApiResponses({ @ApiResponse(responseCode = "201", description = APIConstant.API_USER_CREATED),
			@ApiResponse(responseCode = "401", description = APIConstant.API_AUTH_FAILED),
			@ApiResponse(responseCode = "403", description = APIConstant.API_ACCESS_FAILED),
			@ApiResponse(responseCode = "404", description = APIConstant.API_RESOURCE_NOT_FOUND),
			@ApiResponse(responseCode = "500", description = APIConstant.API_SERVER_ERROR) })
	@Operation(summary = VITinyURLConsants.USER_CREATE)
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody NewUser user) {
		String jwtToken = userService.createNewUser(user);
		return new ResponseEntity<String>("Dummy jwtToken -" + jwtToken, HttpStatus.CREATED);
	}
}