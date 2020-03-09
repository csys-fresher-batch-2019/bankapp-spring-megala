package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.Loan;
import com.megala.bankapp.domain.LoanStatusEnum;
import com.megala.bankapp.exception.DbException;

public interface LoanDAO {
	void addLoan(Loan loan) throws DbException;

	public List<Loan> displayLoan() throws DbException;

	public void updateLoan(LoanStatusEnum status, int id) throws DbException;

	public void deleteLoan(String loanNo) throws DbException;

}
