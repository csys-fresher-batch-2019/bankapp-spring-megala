package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.Customer;
import com.megala.bankapp.exception.DbException;

public interface CustomerDAO {
	public void addCustomer(Customer customer) throws DbException;

	public List<Customer> display() throws DbException;

	public void deleteCustomer(int id) throws DbException;

	public void updateCustomer(String name, int id) throws DbException;

}
