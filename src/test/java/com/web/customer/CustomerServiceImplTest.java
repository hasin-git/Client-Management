package com.web.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
	private Customer customer2;
	
	@BeforeEach
	public void setUp() {
		customerList = new ArrayList<>();
		customer1 = new Customer(101L, "John", "Adams", "john_ad@gmail.com", 7890300021L, "34-Seven sisters St, London");
		customer2 = new Customer(102L, "Anna", "Maria", "anna_22@gmail.com", 9844755473L, "20-WesternBorough,Edinburgh");
		customerList.add(customer1);
		customerList.add(customer2);
	}
	
	private List<Customer> getCustomersList() {
		List<Customer> list = new ArrayList<>();
		list.add(new Customer(101L, "John", "Adams", "john_ad@gmail.com", 7890300021L, "34-Seven sisters St, London"));
		list.add(new Customer(102L, "Anna", "Maria", "anna_22@gmail.com", 9844755473L, "20-WesternBorough,Edinburgh"));
	    return list;	
	}
	
	@Test
	@Rollback(false)
	public void saveCustomer() {
		Customer customer = new Customer(101L, "John", "Adams", "john_ad@gmail.com", 7890300021L, "34-Seven sisters St, London");
		Customer savedCustomer = customerRepository.save(customer);
		assertNotNull(savedCustomer);
    }
	
	@Test
	public void testListCustomers() {
		List<Customer> customers = customerRepository.findAll();
		
		assertThat(customers).size().isGreaterThan(0);
	}
	
	
	@Test
	@Rollback(false)
	public void deleteCustomerTest() {
		Long id = (long) 1;
		boolean isExistBeforeDelete = customerRepository.findById(id).isPresent();
		
		customerRepository.deleteById(id);
		
		boolean notExistAfterDelete = customerRepository.findById(id).isPresent();
		
		assertTrue(isExistBeforeDelete);
		assertFalse(notExistAfterDelete);
	}
	
	
	
	/*
	 * @Test
	 * 
	 * @Rollback(false) public void updateCustomerTest() { Long id = 102L; Customer
	 * customer = new Customer(102L, "Anna", "Maria", "anna_22@gmail.com",
	 * 9844755473L, "20-WesternBorough,Edinburgh");
	 * customerRepository.save(customer);
	 * 
	 * Optional<Customer> updateCustomer = customerRepository.findById(102L);
	 * assertThat(updateCustomer.get().getId()).isEqualTo(id); }
	 */

	/*
	 * @Test public void shouldReturnAllUsers() { List<Customer> users =
	 * getCustomersList(); users.add(new Customer());
	 * 
	 * when(customerRepository.findAll()).thenReturn(users);
	 * 
	 * List<Customer> expected = customerServiceImpl.getAllCustomers();
	 * 
	 * assertEquals(expected, users); verify(customerRepository).findAll(); }
	 */
	
	//@Test
	//public void findAllCustomersTest() {
		//STUB
		//Mockito.when(customerRepository.findAll()).thenReturn(getCustomersList());
		
		//Action
		//List<Customer> customerLst = customerServiceImpl.getAllCustomers();
		
		//customerRepository.save(customer1);
		//when(customerRepository.findAll()).thenReturn(customerList);
	//System.out.print("Customer lst===", customerList);
		//List<Customer> customerLst = customerServiceImpl.getAllCustomers();		
		//assertEquals(customerLst, customerList);
		//Assertion
		//assertEquals(102, customerLst.get(1).getId());
		//assertEquals(2, customerLst.size());		
	//}

}
