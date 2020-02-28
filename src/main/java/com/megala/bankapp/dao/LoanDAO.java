package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.Loan;
import com.megala.bankapp.domain.LoanStatusEnum;

public interface LoanDAO {
	void addLoan(Loan loan) ;
	public List<Loan> displayLoan() ;
	public void updateLoan(LoanStatusEnum status, int id);
	public void deleteLoan(String loanNo) ;
		 

}
