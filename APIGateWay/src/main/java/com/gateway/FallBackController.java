package com.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

	@GetMapping(path = "/userFallback")
	public String userServiceFallBackMethod() {
		
		return "User Service is taking longer than Expected";
	}
	
	@GetMapping(path = "/departmentFallback")
	public String departmentServiceFallBackMethod() {
		
		return "Department Service is taking longer than Expected";
	}
}