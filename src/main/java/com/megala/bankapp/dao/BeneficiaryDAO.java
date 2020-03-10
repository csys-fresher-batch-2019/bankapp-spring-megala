package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.Beneficiary;
import com.megala.bankapp.exception.DbException;

public interface BeneficiaryDAO {
	public int save(Beneficiary beneficiary) throws DbException;

	public List<Beneficiary> findAll() throws DbException;

	public void update(String beneficiaryName, long accNo)throws DbException;

	public int delete(long accNo) throws DbException;

	public List<Beneficiary> findByName(String name) throws DbException;

	public List<Beneficiary> findByCusAccNo(long cusAccNo) throws DbException;
}
