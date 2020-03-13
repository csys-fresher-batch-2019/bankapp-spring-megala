package com.megala.bankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megala.bankapp.dao.TransactionDAO;
import com.megala.bankapp.domain.Transaction;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.exception.ServiceException;
import com.megala.bankapp.exception.ValidateException;
import com.megala.bankapp.validator.TransactionValidator;

@Service
public class TransactionService {
	@Autowired 
	private TransactionDAO trans;
	public List<Transaction> findByAccNum(long val) throws ServiceException {
		List<Transaction> t=null;
		try {
			t=trans.findByAccNo(val);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return t;
	}
	public List<Transaction> findAllTransaction() throws ServiceException{
		List<Transaction> t=null;
		try {
			t=trans.findAll();
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return t;
	}
	public List<Transaction> findByAccNumber(Long obj) throws ServiceException {
		List<Transaction> t=null;
		try {
			t=trans.findByAccNo(obj);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return t;
	}
	public PaymentResponse fundTransaction(Transaction transaction) throws ServiceException {

		PaymentResponse response = new PaymentResponse();
		try {
			TransactionValidator.validateAccAndBalance(transaction);
			try {
				response = trans.fundTransaction(transaction);
			} catch (DbException e) {
				throw new ServiceException(e.getMessage());
			}
		} catch (ValidateException e) {
			throw new ServiceException(e.getMessage());
		}
		return response;
	}

}
