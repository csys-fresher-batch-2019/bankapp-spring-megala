package com.megala.bankapp.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.megala.bankapp.dao.CreditCardDAO;
import com.megala.bankapp.domain.CreditCard;
import com.megala.bankapp.domain.Customer;
import com.megala.bankapp.domain.Register;
import com.megala.bankapp.domain.Transaction;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.exception.ValidateException;
import com.megala.bankapp.factory.DAOFactory;
import com.megala.bankapp.util.ConnectionUtil;
import com.megala.bankapp.util.Logger;
import com.megala.bankapp.validator.CreditCardValidator;
@Service
public class CreditCardService {
//	 private CreditCardService() {
//	    throw new IllegalStateException("Utility class");
//	  }

	private static final Logger LOGGER = Logger.getInstance();

	public static boolean validateCreditCard(long creditCardNo,LocalDate expiryDate,int cvvNo)  {
		try {
			CreditCardValidator.validateCreditCard(creditCardNo,expiryDate,cvvNo);
			return true;
			
		}
		catch(Exception e) {
				
				LOGGER.error(e);
		}
		return false;
		

	}
	
	
	public static boolean checkLogin1(CreditCard creditCard)  {
		boolean result = false;
		try(Connection con = ConnectionUtil.getconnection();
				CallableStatement stmt=con.prepareCall("{call login_procedure1(?,?,?)}")){
				stmt.setLong(1, creditCard.getCardNo());
				stmt.setInt(2, creditCard.getPin());
				stmt.registerOutParameter(3, Types.VARCHAR);
				stmt.executeUpdate();
				String status=stmt.getString(3);
					if(status.equals("Login Successfull"))
					{
						result=true;
					}
				}catch(Exception e) {
					LOGGER.error(e);
				}
		
		return result;

	}
	public static boolean validateCreditCard(long creditCardNo,int creditCardPin) {
		try {
			CreditCardValidator.validateCreditCard(creditCardNo,creditCardPin);
			return true;
			
		}
		catch(Exception e) {
			LOGGER.error(e);
		}
		return false;
	}
	
	public static boolean refundAmount(int transactionId,float amount,String comments) {
		boolean result=false;
		try(Connection con = ConnectionUtil.getconnection();
				CallableStatement stmt=con.prepareCall("{call refund_procedure(?,?,?,?)}")){
				stmt.setInt(1, transactionId);
				stmt.setFloat(2,amount);
				stmt.setString(3, comments);
				stmt.registerOutParameter(4, Types.VARCHAR);
				stmt.executeUpdate();
				String status=stmt.getString(4);
					if(status.equals("Amount Refunded"))
					{
						LOGGER.info("Amount successfully refunded");
						result=true;
						LOGGER.debug(result);
				
					}
					else {
						LOGGER.info("Amount refund failed");

					}
				}
				catch(Exception e) {
					LOGGER.error(e);
				}
		

		return result;
	}
	public static PaymentResponse login(String email,String password) {
		boolean result=false;
		PaymentResponse response=new PaymentResponse();
		try(Connection con = ConnectionUtil.getconnection();
				CallableStatement stmt=con.prepareCall("{call login_procedure(?,?,?,?)}")){
				stmt.setString(1, email);
				stmt.setString(2,password);
				stmt.registerOutParameter(3, Types.INTEGER);
				stmt.registerOutParameter(4, Types.VARCHAR);
				stmt.executeUpdate();
				Long acc=stmt.getLong(3);
				String status=stmt.getString(4);
					if(status.equals("Login Successfull"))
					{
						LOGGER.info("Login Successfull");
						result=true;
						response.setAccountNo(acc);
						response.setStatus(result);
						
				
					}
					else {
						LOGGER.info("Login failed");
						result=false;
						response.setAccountNo(acc);
						response.setStatus(result);	
					}
				}
				catch(Exception e) {
					result=false;
					response.setStatus(result);
					e.printStackTrace();
				}
		

		return response;
	}
	public static PaymentResponse pay(CreditCard creditCard,float amount,String merchantId,String comments)  {
		
		PaymentResponse response =new PaymentResponse();
		boolean validate = false;
		try {
			validate = CreditCardValidator.validateCreditCard(creditCard.getCardNo(), creditCard.getExpiryDate(), creditCard.getCvvNo());

		} catch (ValidateException e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
		boolean validate1 = false;
		try {
			
			validate1 = CreditCardValidator.validateCreditCard(creditCard.getCardNo(), creditCard.getPin());

		} catch (ValidateException e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
		boolean result=false;
		if(validate || validate1) {
			CreditCardDAO c1=DAOFactory.getCreditCardDAO();
			int ccId=0;
			 ccId = c1.displayCreditCard(creditCard.getCardNo(),creditCard.getExpiryDate(),creditCard.getCvvNo());
			System.out.println("CCDisplayCard:" +ccId);
			if(ccId>0) {
			try(Connection con = ConnectionUtil.getconnection();
			CallableStatement stmt=con.prepareCall("{call trans_procedure1(?,?,?,?,?,?)}")){
			stmt.setLong(1, creditCard.getCardNo());
			stmt.setFloat(2,amount);
			stmt.setString(3, merchantId);
			stmt.setString(4, comments);
			stmt.registerOutParameter(5, Types.VARCHAR);
			stmt.registerOutParameter(6, Types.INTEGER);
			stmt.executeUpdate();
			String status=stmt.getString(5);
			Integer transactionId = stmt.getInt(6);
			
			
			
				if(status.equals("Transaction Successfull"))
				{
					LOGGER.info("Transaction successful");
					result=true;
					response.setTransactionId(transactionId);
					response.setStatus(result);
				}
				else {
					response.setStatus(false);
					LOGGER.debug(response);
				}
			}
			catch(Exception e) {
				response.setStatus(result);
				LOGGER.debug(response);
				
			}
		}
			else {
				response.setStatus(false);
				LOGGER.debug(response);
			}
		}
	
		return response;
	}
public static PaymentResponse fundTransaction(Transaction transaction)  {
		
		PaymentResponse response = new PaymentResponse();
		boolean result=false;
			try(Connection con = ConnectionUtil.getconnection();
			CallableStatement stmt=con.prepareCall("{call fund_transfer_procedure(?,?,?,?,?)}")){
			stmt.setLong(1, transaction.getAccNo());
			stmt.setLong(2,transaction.getBeneficiaryAccNo());
			stmt.setInt(3, transaction.getTransactionAmount());
			stmt.registerOutParameter(4, Types.INTEGER);
			stmt.registerOutParameter(5, Types.VARCHAR);
			stmt.executeUpdate();
			Integer transactionId = stmt.getInt(4);
			String status=stmt.getString(5);
			
				if(status.equals("Transaction success"))
				{
					LOGGER.info("Transaction successful");
					result=true;
					response.setTransactionId(transactionId);
					response.setStatus(result);
				}
				else {
					response.setTransactionId(transactionId);
					response.setStatus(result);
				}
			}
			catch(Exception e) {
				LOGGER.error(e);
			}
			return response;
		}
	
		
	
	
	public static Register register(Customer c) {
		Register reg=new Register();
		boolean result=false;
		try(Connection con = ConnectionUtil.getconnection();
				CallableStatement stmt=con.prepareCall("{call register_procedure(?,?,?,?,?,?,?,?,?)}")){
				stmt.setString(1, c.getName());
				stmt.setString(2,c.getStreet());
				stmt.setString(3, c.getCity());
				stmt.setLong(4, c.getMobileNo());
				stmt.setString(5, c.getEmail());
				stmt.setString(6, c.getPassword());
				stmt.setString(7,c.getAccType());
				stmt.registerOutParameter(8, Types.INTEGER);
				stmt.registerOutParameter(9, Types.VARCHAR);
				stmt.executeUpdate();
				String output=stmt.getString(9);
				long accountNo=stmt.getLong(8);
				
				
				
					if(output.equals("registered"))
					{
						LOGGER.info("Registered Successfully");
						result=true;
						reg.setAccNo(accountNo);
						reg.setStatus(result);
				
					}
					else {
						LOGGER.info("Registration failed");
						reg.setAccNo(accountNo);
						reg.setStatus(result);
					}
				}
				catch(Exception e) {
					LOGGER.error(e);
				}
		

		return reg;
	}

	
}