package com.megala.bankapp.dao;

import java.time.LocalDate;
import java.util.List;

import com.megala.bankapp.domain.CreditCard;
import com.megala.bankapp.exception.DbException;

public interface CreditCardDAO {
	public void addCreditCard(CreditCard creditCard) throws DbException;

	public List<CreditCard> displayCreditCards() throws DbException;

	public void updateCreditCard1(String comments, long creditCardNo, boolean blocked) throws DbException;

	public void deleteCreditCard(long accNo) throws DbException;

	public int displayCreditCard(long cardNo, LocalDate expiryDate, int cvvNo) throws DbException;

	public float displayBalance(long cardNo) throws DbException;

	public List<CreditCard> displayCreditCardsByAccNo(long accNo) throws DbException;
}