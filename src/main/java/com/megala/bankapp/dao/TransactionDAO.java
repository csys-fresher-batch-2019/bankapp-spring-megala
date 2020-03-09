package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.Transaction;
import com.megala.bankapp.exception.DbException;

public interface TransactionDAO {
	public void addTransaction(Transaction transaction) throws DbException;

	public List<Transaction> displayTransaction() throws DbException;

	public void updateTransaction(int transactionAmount, long beneficiaryAccNo) throws DbException;

	public void deleteTransaction(long beneficiaryAccNo) throws DbException;

	public List<Transaction> displayParTransaction(long accNo) throws DbException;
}
