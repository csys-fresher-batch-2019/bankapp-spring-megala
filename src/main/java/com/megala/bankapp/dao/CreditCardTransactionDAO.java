package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.CreditCardTransaction;
import com.megala.bankapp.exception.DbException;

public interface CreditCardTransactionDAO {
	public int addCreditCardTransaction(CreditCardTransaction creditCardTransaction) throws DbException;

	public List<CreditCardTransaction> displayCreditCardPaymentList() throws DbException;

	public List<CreditCardTransaction> displayTransactionHistory(long cardId) throws DbException;

	public List<CreditCardTransaction> displayTransactionHistoryByCardId(int cardId) throws DbException;

}
