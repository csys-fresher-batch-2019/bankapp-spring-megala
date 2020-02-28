package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.Customer;

public interface CustomerDAO {
	public void addCustomer(Customer customer) ;
	public List<Customer> display();
	public void deleteCustomer(int id);
	public void updateCustomer(String name, int id);



}
