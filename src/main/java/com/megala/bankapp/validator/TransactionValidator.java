package com.megala.bankapp.validator;

import com.megala.bankapp.domain.Transaction;
import com.megala.bankapp.exception.ValidateException;

public class TransactionValidator {

	public static boolean validateAccAndBalance(Transaction transaction) throws ValidateException {
		if(transaction.getBeneficiaryAccNo()<10||transaction.getBeneficiaryAccNo()>10) {
			throw new ValidateException("Invalid Beneficiary Account Number!!");
		}
		if(transaction.getTransactionAmount()<0 || transaction.getTransactionAmount()==0) {
			throw new ValidateException("Please the enter the valid amount");

		}
		return false;
		
	}

}
