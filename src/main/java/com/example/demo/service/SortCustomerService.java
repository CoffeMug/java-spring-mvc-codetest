package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.exmaple.demo.dto.Customer;

/**
 * Service implementing the sorting of customers list interface.
 * @author amin
 *
 */
@Service
public class SortCustomerService implements SortService<Customer> {

	/**
	 * Sorts the customer's list based on the comparator defined in the 
	 * {@link#Customer} data object.
	 */
	@Override
	public List<Customer> sort(List<Customer> customers) {
		
		if(Objects.isNull(customers)) {
			throw new IllegalArgumentException("List of customers is null");
		}
		
		Collections.sort(customers);
		
		return customers;
	}

}
