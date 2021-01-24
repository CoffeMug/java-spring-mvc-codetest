package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.helper.TestData;
import com.exmaple.demo.dto.Customer;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerControllerTest {

	@LocalServerPort
	int randomServerPort;

	String baseUrl;

	private RestTemplate restTemplate;

	@BeforeAll
	public void setup() {
		this.restTemplate = getInitializedRestTemplate();
		this.baseUrl = "http://localhost:" + randomServerPort + "/sort";
	}

	private RestTemplate getInitializedRestTemplate() {
		final RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}

	@AfterAll
	public void tearDown() {
		this.restTemplate = null;
		this.baseUrl = null;
	}

	@Test
	void sendSortRequestWithBadHeader() throws URISyntaxException {

		// Given
		URI uri = new URI(baseUrl);
		List<Customer> twoCustomerList = TestData.getTwoCustomerList();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);

		HttpEntity<List<Customer>> request = new HttpEntity<>(twoCustomerList, headers);

		// When and Then
		Assertions.assertThrows(HttpClientErrorException.class, () -> {
			restTemplate.exchange(uri, HttpMethod.POST, request, new ParameterizedTypeReference<List<Customer>>() {
			});
		});

	}

	@Test
	void sendSortRequestSingleElementList() throws URISyntaxException {

		// Given
		URI uri = new URI(baseUrl);

		List<Customer> oneCustomerList = TestData.getOneCustomerList();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<List<Customer>> request = new HttpEntity<>(oneCustomerList, headers);

		// When
		ResponseEntity<List<Customer>> result = restTemplate.exchange(uri, HttpMethod.POST, request,
				new ParameterizedTypeReference<List<Customer>>() {
				});

		// Then
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(MediaType.APPLICATION_JSON, result.getHeaders().getContentType());

		assertEquals(1, result.getBody().size());
		// Returned list should be same as the sent list
		assertEquals(oneCustomerList.get(0).getFullName(), result.getBody().get(0).getFullName());
		assertTrue(oneCustomerList.get(0).getDueTime().isEqual(result.getBody().get(0).getDueTime()));
		assertTrue(oneCustomerList.get(0).getJoinTime().isEqual(result.getBody().get(0).getJoinTime()));
		assertEquals(oneCustomerList.get(0).getId(), result.getBody().get(0).getId());

	}

	@Test
	void sendSortRequestWithGoodHeaderAndSomeData() throws URISyntaxException {

		// Given
		URI uri = new URI(baseUrl);

		List<Customer> twoCustomerList = TestData.getTwoCustomerList();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<List<Customer>> request = new HttpEntity<>(twoCustomerList, headers);

		// When
		ResponseEntity<List<Customer>> result = restTemplate.exchange(uri, HttpMethod.POST, request,
				new ParameterizedTypeReference<List<Customer>>() {
				});

		// Then
		// Assert response headers
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(MediaType.APPLICATION_JSON, result.getHeaders().getContentType());

		// Assert response body
		assertEquals(2, result.getBody().size());

		assertEquals(2, result.getBody().get(0).getId());
		assertEquals("David Khorsandi", result.getBody().get(0).getFullName());
		assertTrue(ZonedDateTime.parse("2014-05-06T07:35:43-07:00").isEqual(result.getBody().get(0).getDueTime()));
		assertTrue(ZonedDateTime.parse("2015-05-01T06:03:44-07:00").isEqual(result.getBody().get(0).getJoinTime()));

		assertEquals(1, result.getBody().get(1).getId());
		assertEquals("Amin Khorsandi", result.getBody().get(1).getFullName());
		assertTrue(ZonedDateTime.parse("2014-06-18T06:26:56-07:00").isEqual(result.getBody().get(1).getDueTime()));
		assertTrue(ZonedDateTime.parse("2015-04-08T12:47:16-07:00").isEqual(result.getBody().get(1).getJoinTime()));

		assertTrue(result.getBody().get(1).getDueTime().isAfter(result.getBody().get(0).getDueTime()));
	}

}
