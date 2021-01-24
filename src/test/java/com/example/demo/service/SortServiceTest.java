package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.ZonedDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.helper.TestData;
import com.exmaple.demo.dto.Customer;

@SpringBootTest
class SortServiceTest {

	@Autowired
	private SortCustomerService sortService;

	@Test
	public void sortNullList() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			sortService.sort(null);
		});
	}

	@Test
	public void sortEmptyList() {

		// Given
		List<Customer> unSortedCustomers = TestData.getEmptyCustomerList();

		// When
		List<Customer> sortedCustomers = sortService.sort(unSortedCustomers);

		// Then
		assertEquals(0, sortedCustomers.size());
	}

	@Test
	public void sortListWithOneCustomer() {

		// Given
		List<Customer> unSortedCustomers = TestData.getOneCustomerList();

		// When
		List<Customer> sortedCustomers = sortService.sort(unSortedCustomers);

		// Then
		assertEquals(1, sortedCustomers.size());

		assertEquals(1, sortedCustomers.get(0).getId());

		assertEquals("Amin Khorsandi", sortedCustomers.get(0).getFullName());

		assertEquals(ZonedDateTime.parse("2014-06-18T06:26:56-07:00"), sortedCustomers.get(0).getDueTime());
		assertEquals(ZonedDateTime.parse("2015-04-08T12:47:16-07:00"), sortedCustomers.get(0).getJoinTime());
	}

	@Test
	public void sortListWithTwoCustomers() {

		// Given
		List<Customer> unSortedCustomers = TestData.getTwoCustomerList();

		// When
		List<Customer> sortedCustomers = sortService.sort(unSortedCustomers);

		// Then
		assertEquals(2, sortedCustomers.size());

		assertEquals(2, sortedCustomers.get(0).getId());
		assertEquals(1, sortedCustomers.get(1).getId());

		assertEquals("David Khorsandi", sortedCustomers.get(0).getFullName());
		assertEquals("Amin Khorsandi", sortedCustomers.get(1).getFullName());

		assertEquals(ZonedDateTime.parse("2014-05-06T07:35:43-07:00"), sortedCustomers.get(0).getDueTime());
		assertEquals(ZonedDateTime.parse("2015-05-01T06:03:44-07:00"), sortedCustomers.get(0).getJoinTime());

		assertEquals(ZonedDateTime.parse("2014-06-18T06:26:56-07:00"), sortedCustomers.get(1).getDueTime());
		assertEquals(ZonedDateTime.parse("2015-04-08T12:47:16-07:00"), sortedCustomers.get(1).getJoinTime());
	}

}
