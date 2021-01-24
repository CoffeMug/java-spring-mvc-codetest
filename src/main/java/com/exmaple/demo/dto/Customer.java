package com.exmaple.demo.dto;

import java.time.ZonedDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer implements Comparable<Customer> {

	private int id;

	@JsonProperty(value = "name")
	private String fullName;

	@JsonProperty(value = "duetime")
	private ZonedDateTime dueTime;

	@JsonProperty(value = "jointime")
	private ZonedDateTime joinTime;

	public Customer() {
		super();
	}

	public Customer(int id, String fullName, ZonedDateTime dueTime, ZonedDateTime joinTime) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.dueTime = dueTime;
		this.joinTime = joinTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public ZonedDateTime getDueTime() {
		return dueTime;
	}

	public void setDueTime(ZonedDateTime dueTime) {
		this.dueTime = dueTime;
	}

	public ZonedDateTime getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(ZonedDateTime joinTime) {
		this.joinTime = joinTime;
	}

	@Override
	public int compareTo(Customer customer) {
		if (Objects.isNull(this.getDueTime()) || Objects.isNull(customer.getDueTime()))
			return 0;

		return this.getDueTime().compareTo(customer.getDueTime());
	}

}
