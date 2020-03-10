package com.megala.bankapp.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.megala.bankapp.dao.CreditCardDAO;
import com.megala.bankapp.dao.CustomerDAO;
import com.megala.bankapp.dao.TransactionDAO;
import com.megala.bankapp.domain.CreditCard;
import com.megala.bankapp.domain.Customer;
import com.megala.bankapp.domain.Register;
import com.megala.bankapp.domain.Transaction;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.exception.ServiceException;
import com.megala.bankapp.exception.ValidateException;
import com.megala.bankapp.util.Logger;
import com.megala.bankapp.validator.CreditCardValidator;

@Service
public class CreditCardService {
	@Autowired
	private CreditCardDAO credit;
	@Autowired
	private CustomerDAO cus;
	@Autowired
	private TransactionDAO trans;
	private static final Logger LOGGER = Logger.getInstance();

	public static boolean validateCreditCard(long creditCardNo, LocalDate expiryDate, int cvvNo)
			throws ServiceException {
		try {
			CreditCardValidator.validateCreditCard(creditCardNo, expiryDate, cvvNo);
			return true;

		} catch (ValidateException e) {

			throw new ServiceException(e.getMessage());
		}

	}

	public boolean checkLogin(CreditCard creditCard) throws ServiceException, DbException {
		boolean result = false;
		boolean validate = false;
		try {

			validate = CreditCardValidator.validateCreditCard(creditCard.getCardNo(), creditCard.getPin());

			if (validate) {
				result = credit.checkLogin(creditCard);
			}
		} catch (ValidateException e) {
			throw new ServiceException(e.getMessage());
		}
		return result;

	}

	public static boolean validateCreditCard(long creditCardNo, int creditCardPin) throws ServiceException {
		try {
			CreditCardValidator.validateCreditCard(creditCardNo, creditCardPin);
			return true;

		} catch (ValidateException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public boolean refundAmount(int transactionId, float amount, String comments) throws ServiceException {
		boolean result = false;
		try {
			result = credit.refundAmount(transactionId, amount, comments);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return result;
	}

	public PaymentResponse login(String email, String password) throws ServiceException {
		PaymentResponse response = new PaymentResponse();
		try {
			response=cus.login(email, password);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return response;
	}

	public PaymentResponse pay(CreditCard creditCard, float amount, String merchantId, String comments)
			throws ServiceException {

		PaymentResponse response = new PaymentResponse();
		boolean validate = false;
		try {
			validate = CreditCardValidator.validateCreditCard(creditCard.getCardNo(), creditCard.getExpiryDate(),
					creditCard.getCvvNo());

		} catch (ValidateException e) {
			throw new ServiceException(e.getMessage());
		}
		boolean validate1 = false;
		try {

			validate1 = CreditCardValidator.validateCreditCard(creditCard.getCardNo(), creditCard.getPin());
			System.out.println(validate1);
		} catch (ValidateException e) {
			throw new ServiceException(e.getMessage());
		}
		if (validate || validate1) {
			try {
				response = credit.pay(creditCard, amount, merchantId, comments);
				response.setStatus(true);
			} catch (DbException e) {

				throw new ServiceException(e.getMessage());
			}
		} else {
			response.setStatus(false);
			LOGGER.debug(response);
		}

		return response;
	}

	public PaymentResponse fundTransaction(Transaction transaction) throws ServiceException {

		PaymentResponse response = new PaymentResponse();
		try {
			response = trans.fundTransaction(transaction);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}
		return response;
	}

	public Register register(Customer c) throws ServiceException {
		Register reg = new Register();
		try {
			reg = cus.register(c);
		} catch (DbException e) {
			throw new ServiceException(e.getMessage());
		}

		return reg;
	}

}