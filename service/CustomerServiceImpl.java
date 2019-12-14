package com.cubic.rest.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cubic.rest.service.exception.CustomerNotFoundException;
import com.cubic.rest.service.exception.InvalidDataException;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final Map<String, CustomerData> db = new HashMap<String, CustomerData>();
	private int count = 100;

	@Override
	public CustomerData create(final CustomerData customer) {
		validateCustomer(customer);
		count++;
		customer.setId(String.valueOf(count));
		db.put(customer.getId(), customer);
		return customer;
	}

	@Override
	public void modify(final CustomerData customer) {
		validateCustomer(customer);
		if (db.containsKey(customer.getId())) {
			db.put(customer.getId(), customer);
		} else {
			throw new CustomerNotFoundException("No Coustomer found to modify");
		}

	}

	@Override
	public CustomerData get(final String id) {
		if (db.containsKey(id)) {
			return db.get(id);
		} else {
			throw new CustomerNotFoundException("No Coustomer found to modify");
		}
	}

	@Override
	public void remove(final String id) {
		if (db.containsKey(id)) {
			db.remove(id);
		} else {
			throw new CustomerNotFoundException("No Coustomer found to modify");
		}
	}

	@Override
	public CustomerSearchResult search(String name) {
		final CustomerSearchResult result = CustomerSearchResult.builder().build();
		final Collection<CustomerData> customers = db.values();
		for (CustomerData customer : customers) {
			if (customer.getName().toLowerCase().startsWith(name.toLowerCase())) {
				result.getResults().add(customer);
			}
		}
		return result;
	}

	private void validateCustomer(final CustomerData customer) {
		if (customer == null || StringUtils.isEmpty(customer.getName()) || customer.getAge() < 18) {
			throw new InvalidDataException("Invalid data to process the request");
		}
	}

}
