package com.example.demo.helper;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.exmaple.demo.dao.Customer;

public final class TestData {

	public static List<Customer> getEmptyCustomerList() {
		return new ArrayList<>();
	}

	public static List<Customer> getOneCustomerList() {
		Customer exampleCustomer1 = new Customer(1, "Amin Khorsandi", ZonedDateTime.parse("2014-06-18T06:26:56-07:00"),
				ZonedDateTime.parse("2015-04-08T12:47:16-07:00"));

		List<Customer> customers = new ArrayList<>();
		customers.add(exampleCustomer1);

		return customers;

	}

	public static List<Customer> getTwoCustomerList() {

		Customer exampleCustomer1 = new Customer(1, "Amin Khorsandi", ZonedDateTime.parse("2014-06-18T06:26:56-07:00"),
				ZonedDateTime.parse("2015-04-08T12:47:16-07:00"));
		Customer exampleCustomer2 = new Customer(2, "David Khorsandi", ZonedDateTime.parse("2014-05-06T07:35:43-07:00"),
				ZonedDateTime.parse("2015-05-01T06:03:44-07:00"));

		List<Customer> customers = new ArrayList<>();
		customers.add(exampleCustomer1);
		customers.add(exampleCustomer2);

		return customers;
	}

}
