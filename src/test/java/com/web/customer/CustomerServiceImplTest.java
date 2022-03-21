package com.web.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.web.customer.model.Customer;
import com.web.customer.repository.CustomerRepository;
import com.web.customer.service.CustomerServiceImpl;

//@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomerServiceImplTest {
		
	//Dependencies will be mock
	@Autowired
	private CustomerRepository customerRepository;
	
	//System under Test
	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	List<Customer> customerList;
	private Customer customer1;
	
	@BeforeEach
	public void setUp() {
		customerList = new ArrayList<>();
		customer1 = new Customer(101L, "John", "Adams", "john_ad@gmail.com", 7890300021L, "34-Seven sisters St, London");
		customerList.add(customer1);		
	}
	
	@Test
	@Rollback(false)
	public void saveCustomer() {
		Customer customer = new Customer(51L, "Princy", "Jose", "princy_ad@gmail.com", 7890300021L, "74,Forest Gate, London");
		Customer savedCustomer = customerRepository.save(customer);
		assertNotNull(savedCustomer);
    }
	
	@Test
	public void testListCustomers() {
		List<Customer> customers = customerRepository.findAll();
		
		assertThat(customers).size().isGreaterThan(0);
	}
	
}
