package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.Account;
import com.megala.bankapp.exception.DbException;

public interface AccountDAO {
	public void addAccount(Account account) throws DbException;

	public List<Account> displayAcc() throws DbException;

	public int updateAccount(long accNo, int amount) throws DbException;

	public void deleteAccount(long accNo) throws DbException;

	public List<Account> searchByAccountNo(long accNo) throws DbException;

	public void displayAccount1(int id) throws DbException;

	public int displayBalance(long accNo) throws DbException;
	
	public String status(long accNo) throws DbException;

	public int activeAccount(long accNo, String status) throws DbException;
}
