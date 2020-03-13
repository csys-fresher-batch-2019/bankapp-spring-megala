package com.megala.bankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megala.bankapp.dao.BeneficiaryDAO;
import com.megala.bankapp.domain.Beneficiary;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.exception.ServiceException;
import com.megala.bankapp.exception.ValidateException;
import com.megala.bankapp.validator.BeneficiaryValidator;

@Service
public class BeneficiaryService {
	@Autowired
	BeneficiaryDAO bene;

	public int addBene(Beneficiary beneficiary) throws ServiceException {
		int a = 0;
		try {
			BeneficiaryValidator.validateBene(beneficiary);
			a = bene.save(beneficiary);
		} catch (ValidateException e) {
			throw new ServiceException(e.getMessage());
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return a;

	}

	public List<Beneficiary> findByAccNoAndIfscCode(long accNo, String ifscCode) throws ServiceException {
		List<Beneficiary> b = null;
		try {
			b = bene.findByAccNoAndIfsc(accNo, ifscCode);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return b;
	}

	public List<Beneficiary> findByCusAccNum(Long obj) throws ServiceException {
		List<Beneficiary> b = null;
		try {
			b = bene.findByCusAccNo(obj);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return b;
	}

}
