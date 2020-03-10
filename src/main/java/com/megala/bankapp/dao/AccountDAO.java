package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.Account;
import com.megala.bankapp.exception.DbException;

public interface AccountDAO {
	public void save(Account account) throws DbException;

	public List<Account> findAll() throws DbException;

	public int update(long accNo, int amount) throws DbException;

	public void delete(long accNo) throws DbException;

	public List<Account> findByAccNo(long accNo) throws DbException;

	public void findById(int id) throws DbException;

	public int findBalanceByAccNo(long accNo) throws DbException;
	
	public String findStatusByAccNo(long accNo) throws DbException;

	public int activeAccount(long accNo, String status) throws DbException;

	public Account getAccount(String email) throws DbException;
}
