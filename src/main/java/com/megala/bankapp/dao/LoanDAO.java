package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.Loan;
import com.megala.bankapp.domain.LoanStatusEnum;
import com.megala.bankapp.exception.DbException;

public interface LoanDAO {
	void save(Loan loan) throws DbException;

	public List<Loan> findAll() throws DbException;

	public void update(LoanStatusEnum status, int id) throws DbException;

	public void delete(String loanNo) throws DbException;

}
