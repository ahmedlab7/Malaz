package com.cubic.rest.service;

public interface CustomerService {
	CustomerData create(final CustomerData customer);

	void modify(final CustomerData customer);

	CustomerData get(final String id);

	void remove(final String id);

	CustomerSearchResult search(final String name);
}
