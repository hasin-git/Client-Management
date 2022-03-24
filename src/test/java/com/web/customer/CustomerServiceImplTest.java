package com.web.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.customer.model.Customer;
import com.web.customer.repository.CustomerRepository;
import com.web.customer.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
		
	//Dependencies will be mock
	@Mock
	private CustomerRepository customerRepository;
	
	//System under Test
	@Autowired
	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	
	private Customer customer1;
	private Customer customer2;
	List<Customer> customerList;
	
	@BeforeEach
	public void setUp() {
		customerList = new ArrayList<>();
		customer1 = new Customer(12L, "Princy", "Jose", "princy_ad@gmail.com", 9889970021L, "74,Forest Gate, Glassgow");
		customer2 = new Customer(13L, "Ashley", "Munoz", "ashley_m@gmail.com", 8755609823L, "48,WentsWorth Road,Ireland");
		customerList.add(customer1);
		customerList.add(customer2);
	}
	
	@Test
	public void getAllCustomersTest() {
		customerRepository.save(customer1);
		when(customerRepository.findAll()).thenReturn(customerList);
			
		List<Customer> cusList = customerServiceImpl.getAllCustomers();
		assertEquals(customerList, cusList);
		verify(customerRepository, times(1)).save(customer1);
		verify(customerRepository, times(1)).findAll();
	}
	
	@Test
	public void saveCustomerTest() {
		when(customerRepository.save(customer1)).thenReturn(customer1);
	    
	    customerServiceImpl.saveCustomer(customer1);
	    
	    verify(customerRepository,times(1)).save(customer1);
	}	
	
	@Test
	public void getCustomerByIdTest() {
		when(customerRepository.findById((long) 12)).thenReturn(Optional.ofNullable(customer1));
		assertThat(customerServiceImpl.getCustomerById(customer1.getId())).isEqualTo(customer1);
		verify(customerRepository, times(1)).findById(customer1.getId());
	}	
		
	@Test
	public void deleteCustomerByIdTest() {
		customerServiceImpl.deleteCustomerById(customer1.getId());
		verify(customerRepository, times(1)).deleteById(customer1.getId());
	}	
}
