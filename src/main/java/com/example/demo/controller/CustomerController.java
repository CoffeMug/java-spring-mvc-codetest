package com.example.demo.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.SortService;
import com.exmaple.demo.dao.Customer;

@RestController
public class CustomerController {

	@Autowired
	private SortService<Customer> sortCustomerService;

	@PostMapping(path = "/sort", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Async
	public CompletableFuture<ResponseEntity<List<Customer>>> getResult(@RequestBody List<Customer> customers) throws InterruptedException {
		return CompletableFuture
				.completedFuture(ResponseEntity.ok().body((sortCustomerService.sort(customers))));
	}

}
