package com.megala.bankapp.dao;

import java.time.LocalDate;
import java.util.List;

import com.megala.bankapp.domain.CreditCard;

public interface CreditCardDAO {
	public void addCreditCard(CreditCard creditCard) ;
	public List<CreditCard> displayCreditCards() ;
	public void updateCreditCard1(String comments,long creditCardNo,boolean blocked) ;
	public void deleteCreditCard(long accNo) ;
	public int displayCreditCard(long cardNo,LocalDate expiryDate,int cvvNo);
	public float displayBalance(long cardNo);	
	public List<CreditCard> displayCreditCardsByAccNo(long accNo);
}