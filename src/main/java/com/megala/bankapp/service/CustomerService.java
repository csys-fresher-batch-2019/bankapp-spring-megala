package com.megala.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megala.bankapp.dao.CustomerDAO;
import com.megala.bankapp.domain.Customer;
import com.megala.bankapp.domain.Register;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.exception.ServiceException;
import com.megala.bankapp.exception.ValidateException;
import com.megala.bankapp.validator.RegisterValidator;

@Service
public class CustomerService {
	@Autowired
	CustomerDAO cus;
	public PaymentResponse login(String email, String password) throws ServiceException {
		PaymentResponse response = new PaymentResponse();
		try {
			response = cus.login(email, password);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return response;
	}
	public Register register(Customer c) throws ServiceException {
		Register reg = new Register();
		try {
			RegisterValidator.registerValidate(c);
			try {
				reg = cus.register(c);
			} catch (DbException e) {
				throw new ServiceException(e.getMessage());
			}
		} catch (ValidateException e) {
			throw new ServiceException(e.getMessage());
		}

		return reg;
	}
}
