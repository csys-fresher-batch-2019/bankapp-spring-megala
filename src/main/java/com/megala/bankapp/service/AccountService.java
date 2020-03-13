package com.megala.bankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megala.bankapp.dao.AccountDAO;
import com.megala.bankapp.domain.Account;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.exception.ServiceException;
@Service
public class AccountService {
	@Autowired
	private AccountDAO accountDAO;

	public void addAccount(Account account) throws ServiceException {
		try {
			accountDAO.save(account);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public int active(long val, String active) throws ServiceException {
		int account = 0;
		try {
			account = accountDAO.activeAccount(val, active);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return account;
	}
	public int findBalance(long accNo) throws ServiceException {
		int balance=0;
		try {
			balance = accountDAO.findBalanceByAccNo(accNo);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return balance;
	}
	public List<Account> findByAccNum(long val) throws ServiceException{
		List<Account> l=null;
		try {
			l= accountDAO.findByAccNo(val);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return l;
	}
	public List<Account> findAll() throws ServiceException{
		List<Account> l=null;
		try {
			l=accountDAO.findAll();
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return l;
	}

	public int updateAmount(long val, int amount) throws ServiceException {
		int account=0;
		try {
			account=accountDAO.update(val, amount);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return account;
	}
}
