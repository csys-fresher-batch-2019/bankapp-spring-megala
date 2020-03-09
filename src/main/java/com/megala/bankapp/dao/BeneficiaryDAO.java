package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.Beneficiary;
import com.megala.bankapp.exception.DbException;

public interface BeneficiaryDAO {
	public int addBeneficiary(Beneficiary beneficiary) throws DbException;

	public List<Beneficiary> displayBeneficiary() throws DbException;

	public void updateBeneficiary(String beneficiaryName, long accNo)throws DbException;

	public int deleteBeneficiary(long accNo) throws DbException;

	public List<Beneficiary> searchByBeneficiaryName(String name) throws DbException;

	public List<Beneficiary> displayParBeneficiary(long cusAccNo) throws DbException;
}
