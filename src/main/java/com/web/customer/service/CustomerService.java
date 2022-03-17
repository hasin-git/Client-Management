package com.web.customer.service;

import java.util.List;

import com.web.customer.model.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers();
	void saveCustomer(Customer customer);
	Customer getCustomerById(long id);
	void deleteCustomerById(long id);
}
