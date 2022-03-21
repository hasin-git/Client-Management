package com.web.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.customer.exception.CustomerNotFoundException;
import com.web.customer.model.Customer;
import com.web.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);		
	}

	@Override
	public Customer getCustomerById(long id) {
		Optional<Customer> optional = customerRepository.findById(id);
		Customer customer = null;
		if(optional.isPresent()) {
			customer = optional.get();
		} else {
			throw new CustomerNotFoundException("Customer not found for id:" + id);
		}
		return customer;
	}

	@Override
	public void deleteCustomerById(long id) {
		customerRepository.deleteById(id);	
	}
}
