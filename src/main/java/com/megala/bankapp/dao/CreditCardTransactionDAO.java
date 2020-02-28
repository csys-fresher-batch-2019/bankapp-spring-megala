package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.CreditCardTransaction;

public interface CreditCardTransactionDAO {
	public int addCreditCardTransaction(CreditCardTransaction creditCardTransaction) ;
	public List<CreditCardTransaction> displayCreditCardPaymentList() ;
	public List<CreditCardTransaction> displayTransactionHistory(long cardId);	
	public List<CreditCardTransaction> displayTransactionHistoryByCardId(int cardId);

}
