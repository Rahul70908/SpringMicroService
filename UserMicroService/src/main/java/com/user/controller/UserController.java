package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.service.UserService;
import com.user.vo.ResponseVo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	UserService userService;
	
	int count = 1;

	@PostMapping(path = "/saveUser")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping(path = "/getData")
//	@CircuitBreaker(name = "DEPARTMENT-SERVICE", fallbackMethod = "fallBackResponse")
//	@Retry(name = "DEPARTMENT-SERVICE" ,fallbackMethod = "fallBackResponse")
	@RateLimiter(name = "DEPARTMENT-SERVICE", fallbackMethod = "fallBackResponse")
	public ResponseEntity<ResponseVo> responseVo(@RequestParam("id") Integer id) {
		
		System.out.println("Method Called  "+count++);
		return  new ResponseEntity<>(userService.getUserByDepartmentId(id), HttpStatus.OK);
	}
	public ResponseEntity<Object> fallBackResponse(Integer id , Exception e){
	  return new ResponseEntity<>("DEPARTMENT-SERVICE-DOWN", HttpStatus.FORBIDDEN);
	}
}