package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.CreditCardTransaction;
import com.megala.bankapp.exception.DbException;

public interface CreditCardTransactionDAO {
	public int save(CreditCardTransaction creditCardTransaction) throws DbException;

	public List<CreditCardTransaction> findAll() throws DbException;

	public List<CreditCardTransaction> findByCardNo(long cardNo) throws DbException;

	public List<CreditCardTransaction> findByCardId(int cardId) throws DbException;

}
