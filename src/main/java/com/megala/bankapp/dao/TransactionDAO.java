package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.Transaction;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.exception.DbException;

public interface TransactionDAO {
	public void save(Transaction transaction) throws DbException;

	public List<Transaction> findAll() throws DbException;

	public void update(int transactionAmount, long beneficiaryAccNo) throws DbException;

	public List<Transaction> findByAccNo(long accNo) throws DbException;
	
	public PaymentResponse fundTransaction(Transaction transaction) throws DbException;
}
