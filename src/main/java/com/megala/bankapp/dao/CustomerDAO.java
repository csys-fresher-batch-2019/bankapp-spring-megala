package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.Customer;
import com.megala.bankapp.domain.Register;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.exception.DbException;

public interface CustomerDAO {
	public void save(Customer customer) throws DbException;

	public List<Customer> findAll() throws DbException;

	public void delete(int id) throws DbException;

	public void update(String name, int id) throws DbException;

	public Register register(Customer c) throws DbException;
	
	public PaymentResponse login(String email, String password) throws DbException;

}
