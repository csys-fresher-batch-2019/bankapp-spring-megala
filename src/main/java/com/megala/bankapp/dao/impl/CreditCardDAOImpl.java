package com.megala.bankapp.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.megala.bankapp.dao.CreditCardDAO;
import com.megala.bankapp.domain.CreditCard;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.util.Logger;

@Repository
public class CreditCardDAOImpl implements CreditCardDAO {
	private static final Logger LOGGER = Logger.getInstance();
	@Autowired
	private DataSource dataSource;

	public void save(CreditCard creditCard) throws DbException {
		String sql = "insert into credit_card(credit_card_no,credit_card_pin,acc_no,card_limit,cvv_no,expiry_date,available_balance)values(?,?,?,?,?,?,?)";
		LOGGER.info(sql);
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, creditCard.getCardNo());
			pst.setInt(2, creditCard.getPin());
			pst.setLong(3, creditCard.getAccNo());
			pst.setInt(4, creditCard.getLimitNo());
			pst.setInt(5, creditCard.getCvvNo());
			pst.setDate(6, Date.valueOf(creditCard.getExpiryDate()));
			pst.setFloat(7, creditCard.getAvailableBalance());
			int rows = pst.executeUpdate();
			LOGGER.info("no of rows inserted:" + rows);
		} catch (SQLException e) {
			throw new DbException("Unable to add creditcard", e);
		}
	}

	public boolean checkLogin(CreditCard creditCard) throws DbException {
		boolean result = false;

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
			throw new DbException("Unable to add creditcard", e);
		}
		return result;
	}

	public int findId(long cardNo, LocalDate expiryDate, int cvvNo) throws DbException {
		String sql = "select credit_card_id from credit_card where credit_card_no=? and expiry_date=? and cvv_no=? ";
		int creditCardId = 0;
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, cardNo);
			pst.setDate(2, Date.valueOf(expiryDate));
			pst.setInt(3, cvvNo);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					creditCardId = rs.getInt("credit_card_id");
					System.out.println(creditCardId);
				}
			}
		} catch (SQLException e) {

			throw new DbException("Unable to display cardid", e);
		}
		return creditCardId;
	}

	public PaymentResponse pay(CreditCard creditCard, float amount, String merchantId, String comments)
			throws DbException {
		PaymentResponse response = new PaymentResponse();
		boolean result = false;

		int ccId = 0;
		try {
			ccId = findId(creditCard.getCardNo(), creditCard.getExpiryDate(), creditCard.getCvvNo());
			System.out.println(ccId);
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
				throw new DbException(e.getMessage());

			}
		} else {
			response.setStatus(false);
			LOGGER.debug(response);
		}
		return response;

	}

	public boolean refundAmount(int transactionId, float amount, String comments) throws DbException {
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
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		return result;
	}

	public List<CreditCard> findAll() throws DbException {
		List<CreditCard> c = new ArrayList<>();

		String sql = "select credit_card_no,acc_no,card_limit,expiry_date from credit_card";
		LOGGER.info(sql);

		try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement()) {
			try (ResultSet rows = stmt.executeQuery(sql)) {

				while (rows.next()) {
					long creditCardNo = rows.getLong("credit_card_no");
					long accNo = rows.getLong("acc_no");
					int limitNo = rows.getInt("card_limit");
					LocalDate expiryDate = rows.getDate("expiry_date").toLocalDate();
					CreditCard creditCard = new CreditCard();
					creditCard.setCardNo(creditCardNo);
					creditCard.setAccNo(accNo);
					creditCard.setLimitNo(limitNo);
					creditCard.setExpiryDate(expiryDate);
					c.add(creditCard);
				}
			}
		} catch (SQLException e) {

			throw new DbException("Unable to display creditcard details", e);
		}
		return c;
	}

	public List<CreditCard> findAllByAccNo(long accNo) throws DbException {
		List<CreditCard> c = new ArrayList<>();

		String sql = "select credit_card_id,credit_card_no,card_limit,expiry_date,cvv_no,available_balance,credit_card_pin from credit_card where acc_no=?";
		LOGGER.info(sql);

		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, accNo);

			try (ResultSet rows = pst.executeQuery()) {

				while (rows.next()) {
					int cardId = rows.getInt("credit_card_id");
					long creditCardNo = rows.getLong("credit_card_no");
					int limitNo = rows.getInt("card_limit");
					LocalDate expiryDate = rows.getDate("expiry_date").toLocalDate();
					int cvv = rows.getInt("cvv_no");
					float balance = rows.getFloat("available_balance");
					int pin = rows.getInt("credit_card_pin");

					CreditCard creditCard = new CreditCard();
					creditCard.setCreditcardId(cardId);
					creditCard.setCardNo(creditCardNo);
					creditCard.setLimitNo(limitNo);
					creditCard.setExpiryDate(expiryDate);
					creditCard.setCvvNo(cvv);
					creditCard.setAvailableBalance(balance);
					creditCard.setPin(pin);
					c.add(creditCard);
				}
			}
		} catch (SQLException e) {

			throw new DbException("Unable to display creditcard details", e);
		}
		return c;
	}

	public void update(String comments, long creditCardNo, boolean blocked) throws DbException {

		String sql = "update credit_card set comments=?,blocked=? where credit_card_no=?";
		LOGGER.info(sql);
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, comments);
			pst.setInt(2, blocked ? 1 : 0);
			pst.setLong(3, creditCardNo);
			int rows = pst.executeUpdate();
			LOGGER.info("no of rows updated:" + rows);

		} catch (SQLException e) {

			throw new DbException("Unable to update creditcard", e);
		}

	}

	public float findBalance(long cardNo) throws DbException {
		String sql = "select available_balance from credit_card where credit_card_no=?";
		float availableBalance = 0;
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, cardNo);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					availableBalance = rs.getFloat("available_balance");
				}
			}
		} catch (SQLException e) {

			throw new DbException("Unable to display balance", e);
		}
		return availableBalance;
	}

}
