package com.web.customer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.customer.controller.CustomerController;
import com.web.customer.model.Customer;
import com.web.customer.service.CustomerService;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CustomerContollerTest {

	private MockMvc mockMvc;
	 
    @Mock
    private CustomerService customerService;
    
    @InjectMocks
	private CustomerController customerController;
   
    @Mock
    private Model model;
    
    @Before
    public void setup() {
    	mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
    
    private Customer getCustomer() {
    	Customer customer = new Customer(102L, "Princy", "Jose", "princy_ad@gmail.com", 9889970021L, "74,Forest Gate, Glassgow");
		return customer;    	
    }
    
    @Test
    public void viewHomePageTest() throws Exception {
    	List<Customer> clist = Arrays.asList(
    			new Customer(101L, "John", "Adams", "john_ad@gmail.com", 7890300021L, "34-Seven sisters St, London"),
    			getCustomer());      	
		 
       // when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));
		when(customerService.getAllCustomers()).thenReturn(clist);
             
        mockMvc.perform(get("/"))
                .andExpect(MockMvcResultMatchers.view().name("index"));            
      
        verify(customerService, times(1)).getAllCustomers();        
    }
    
  
    @Test
    public void showNewCustomerFormTest() throws Exception {    	
        mockMvc.perform(get("/showNewCustomerForm"))
                .andExpect(MockMvcResultMatchers.view().name("new_customer"));         
    }    
     
    @Test
    public void saveCustomerTest() throws Exception {
    	doNothing().when(customerService).saveCustomer(getCustomer());
    	             
        mockMvc.perform(post("/saveCustomer"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));   
        verify(customerService, times(1)).saveCustomer(Matchers.any()); 
    }    
    
	@Test
	public void showFormUpdateTest() throws Exception {
		when(customerService.getCustomerById(getCustomer().getId())).thenReturn(getCustomer());
		
		mockMvc.perform(get("/showFormUpdate/"+ getCustomer().getId()))
        		.andExpect(MockMvcResultMatchers.view().name("update_customer")); 
		
		verify(customerService, times(1)).getCustomerById(getCustomer().getId());
	}
    
    @Test
    public void deleteCustomerTest() throws Exception {
    	doNothing().when(customerService).deleteCustomerById(getCustomer().getId());
    	
        mockMvc.perform(get("/deleteCustomer/"+ getCustomer().getId()))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));   
        verify(customerService, times(1)).deleteCustomerById(getCustomer().getId());
    }    
}
