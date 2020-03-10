package com.megala.bankapp.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megala.bankapp.dao.CreditCardDAO;
import com.megala.bankapp.domain.CreditCard;
import com.megala.bankapp.domain.Customer;
import com.megala.bankapp.domain.Register;
import com.megala.bankapp.domain.Transaction;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.exception.ServiceException;
//import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.exception.ValidateException;
import com.megala.bankapp.factory.DAOFactory;
import com.megala.bankapp.util.Logger;
import com.megala.bankapp.validator.CreditCardValidator;

@Service
public class CreditCardService {
	// private CreditCardService() {
	// throw new IllegalStateException("Utility class");
	// }
	@Autowired
	private DataSource dataSource;
	private static final Logger LOGGER = Logger.getInstance();

	public static boolean validateCreditCard(long creditCardNo, LocalDate expiryDate, int cvvNo)
			throws ValidateException, ServiceException {
		try {
			CreditCardValidator.validateCreditCard(creditCardNo, expiryDate, cvvNo);
			return true;

		} catch (ValidateException e) {

			throw new ServiceException(e.getMessage());
		}

	}

	public boolean checkLogin1(CreditCard creditCard) throws ValidateException, ServiceException {
		boolean result = false;
		boolean validate = false;
		try {

			validate = CreditCardValidator.validateCreditCard(creditCard.getCardNo(), creditCard.getPin());

			if (validate) {
				try (Connection con = dataSource.getConnection();
						CallableStatement stmt = con.prepareCall("{call login_procedure1(?,?,?)}")) {
					stmt.setLong(1, creditCard.getCardNo());
					stmt.setInt(2, creditCard.getPin());
					stmt.registerOutParameter(3, Types.VARCHAR);
					stmt.executeUpdate();
					String status = stmt.getString(3);
					if (status.equals("Login Successful")) {
						result = true;
					} else {
						result = false;
					}
				} catch (SQLException e) {

					LOGGER.error(e);
				}
			}
		} catch (ValidateException e) {
			throw new ServiceException(e.getMessage());
		}
		return result;

	}

	public static boolean validateCreditCard(long creditCardNo, int creditCardPin) throws ValidateException {
		try {
			CreditCardValidator.validateCreditCard(creditCardNo, creditCardPin);
			return true;

		} catch (ValidateException e) {
			throw new ValidateException(e.getMessage());
		}
	}

	public boolean refundAmount(int transactionId, float amount, String comments) throws Exception {
		boolean result = false;
		try (Connection con = dataSource.getConnection();
				CallableStatement stmt = con.prepareCall("{call refund_procedure(?,?,?,?)}")) {
			stmt.setInt(1, transactionId);
			stmt.setFloat(2, amount);
			stmt.setString(3, comments);
			stmt.registerOutParameter(4, Types.VARCHAR);
			stmt.executeUpdate();
			String status = stmt.getString(4);
			if (status.equals("Amount Refunded")) {
				LOGGER.info("Amount successfully refunded");
				result = true;
				LOGGER.debug(result);

			} else {
				LOGGER.info("Amount refund failed");

			}
		}
		catch (SQLException e) {
			throw new ServiceException(e.getMessage());
		}

		return result;
	}

	public PaymentResponse login(String email, String password) throws SQLException, ValidateException, ServiceException {
		boolean result = false;
		PaymentResponse response = new PaymentResponse();
		try (Connection con = dataSource.getConnection();
				CallableStatement stmt = con.prepareCall("{call login_procedure(?,?,?,?)}")) {
			stmt.setString(1, email);
			stmt.setString(2, password);
			stmt.registerOutParameter(3, Types.INTEGER);
			stmt.registerOutParameter(4, Types.VARCHAR);
			stmt.executeUpdate();
			Long acc = stmt.getLong(3);
			String status = stmt.getString(4);
			if (status.equals("Login Successfull")) {
				LOGGER.info("Login Successfull");
				result = true;
				response.setAccountNo(acc);
				response.setStatus(result);

			} else {
				LOGGER.info("Login failed");
				result = false;
				response.setAccountNo(acc);
				response.setStatus(result);
			}
		} catch (SQLException e) {
			result = false;
			response.setStatus(result);
				throw new ServiceException(e.getMessage());
			}
		

		return response;
	}

	public PaymentResponse pay(CreditCard creditCard, float amount, String merchantId, String comments) throws SQLException, ValidateException, DbException, ServiceException {

		PaymentResponse response = new PaymentResponse();
		boolean validate = false;
		try {
			validate = CreditCardValidator.validateCreditCard(creditCard.getCardNo(), creditCard.getExpiryDate(),
					creditCard.getCvvNo());

		} catch (ValidateException e) {
			throw new ValidateException(e.getMessage());
		}
		boolean validate1 = false;
		try {

			validate1 = CreditCardValidator.validateCreditCard(creditCard.getCardNo(), creditCard.getPin());

		} catch (ValidateException e) {
			throw new ValidateException(e.getMessage());
		}
		boolean result = false;
		if (validate || validate1) {
			CreditCardDAO c1 = DAOFactory.getCreditCardDAO();
			int ccId = 0;
			try {
				ccId = c1.findId(creditCard.getCardNo(), creditCard.getExpiryDate(), creditCard.getCvvNo());
			} catch (DbException e1) {
				throw new DbException(e1.getMessage());
			}
			System.out.println("CCDisplayCard:" + ccId);
			if (ccId > 0) {
				try (Connection con = dataSource.getConnection();
						CallableStatement stmt = con.prepareCall("{call trans_procedure1(?,?,?,?,?,?)}")) {
					stmt.setLong(1, creditCard.getCardNo());
					stmt.setFloat(2, amount);
					stmt.setString(3, merchantId);
					stmt.setString(4, comments);
					stmt.registerOutParameter(5, Types.VARCHAR);
					stmt.registerOutParameter(6, Types.INTEGER);
					stmt.executeUpdate();
					String status = stmt.getString(5);
					Integer transactionId = stmt.getInt(6);

					if (status.equals("Transaction Successfull")) {
						LOGGER.info("Transaction successful");
						result = true;
						response.setTransactionId(transactionId);
						response.setStatus(result);
					} else {
						response.setStatus(false);
						LOGGER.debug(response);
					}
				} catch (SQLException e) {
					response.setStatus(result);
					LOGGER.debug(response);
					throw new ServiceException(e.getMessage());


				}
			} else {
				response.setStatus(false);
				LOGGER.debug(response);
			}
		}

		return response;
	}

	public PaymentResponse fundTransaction(Transaction transaction) throws SQLException, ServiceException {

		PaymentResponse response = new PaymentResponse();
		boolean result = false;
		try (Connection con = dataSource.getConnection();
				CallableStatement stmt = con.prepareCall("{call fund_transfer_procedure(?,?,?,?,?)}")) {
			stmt.setLong(1, transaction.getAccNo());
			stmt.setLong(2, transaction.getBeneficiaryAccNo());
			stmt.setInt(3, transaction.getTransactionAmount());
			stmt.registerOutParameter(4, Types.INTEGER);
			stmt.registerOutParameter(5, Types.VARCHAR);
			stmt.executeUpdate();
			Integer transactionId = stmt.getInt(4);
			String status = stmt.getString(5);

			if (status.equals("Transaction success")) {
				LOGGER.info("Transaction successful");
				result = true;
				response.setTransactionId(transactionId);
				response.setStatus(result);
			} else {
				response.setTransactionId(transactionId);
				response.setStatus(result);
			}
		} catch (SQLException e) {
			throw new ServiceException(e.getMessage());
		}
		return response;
	}

	public Register register(Customer c) throws SQLException, ServiceException {
		Register reg = new Register();
		boolean result = false;
		try (Connection con = dataSource.getConnection();
				CallableStatement stmt = con.prepareCall("{call register_procedure(?,?,?,?,?,?,?,?,?)}")) {
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getStreet());
			stmt.setString(3, c.getCity());
			stmt.setLong(4, c.getMobileNo());
			stmt.setString(5, c.getEmail());
			stmt.setString(6, c.getPassword());
			stmt.setString(7, c.getAccType());
			stmt.registerOutParameter(8, Types.INTEGER);
			stmt.registerOutParameter(9, Types.VARCHAR);
			stmt.executeUpdate();
			String output = stmt.getString(9);
			long accountNo = stmt.getLong(8);

			if (output.equals("registered")) {
				LOGGER.info("Registered Successfully");
				result = true;
				reg.setAccNo(accountNo);
				reg.setStatus(result);

			} else {
				LOGGER.info("Registration failed");
				reg.setAccNo(accountNo);
				reg.setStatus(result);
			}
		} catch (SQLException e) {
			throw new ServiceException(e.getMessage());
		}

		return reg;
	}

}