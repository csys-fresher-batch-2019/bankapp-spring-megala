package com.megala.bankapp.dao;

import java.time.LocalDate;
import java.util.List;

import com.megala.bankapp.domain.CreditCard;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.exception.DbException;

public interface CreditCardDAO {
	public void save(CreditCard creditCard) throws DbException;

	public List<CreditCard> findAll() throws DbException;

	public void update(String comments, long creditCardNo, boolean blocked) throws DbException;

	public int findId(long cardNo, LocalDate expiryDate, int cvvNo) throws DbException;

	public float findBalance(long cardNo) throws DbException;

	public List<CreditCard> findAllByAccNo(long accNo) throws DbException;

	public boolean checkLogin(CreditCard creditCard) throws DbException;
	
	public PaymentResponse pay(CreditCard creditCard, float amount, String merchantId, String comments) throws DbException;

	public boolean refundAmount(int transactionId, float amount, String comments) throws DbException;

}