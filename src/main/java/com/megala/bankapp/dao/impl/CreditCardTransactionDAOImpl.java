package com.megala.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.megala.bankapp.dao.CreditCardTransactionDAO;
import com.megala.bankapp.domain.CreditCardTransaction;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.exception.ErrorConstants;
import com.megala.bankapp.util.Logger;

@Repository
public class CreditCardTransactionDAOImpl implements CreditCardTransactionDAO {
	private static final Logger LOGGER = Logger.getInstance();
	@Autowired
	private DataSource dataSource;

	public int save(CreditCardTransaction creditCardTransaction) throws DbException {
		String sql = "insert into credit_card_transaction(card_id,amount,description_1,transaction_date,status)values(?,?,?,?,?)";
		LOGGER.info(sql);
		int rows = 0;
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, creditCardTransaction.getCardId());
			pst.setFloat(2, creditCardTransaction.getAmount());
			pst.setString(3, creditCardTransaction.getMerchantId());
			pst.setTimestamp(4, creditCardTransaction.getTransactionDate());
			pst.setString(5, creditCardTransaction.getStatus());
			rows = pst.executeUpdate();
			LOGGER.info("no of rows inserted:" + rows);

		} catch (SQLException e) {

			throw new DbException(ErrorConstants.INVALID_ADD,e);
		}

		return rows;

	}

	public List<CreditCardTransaction> findAll() throws DbException {
		List<CreditCardTransaction> c = new ArrayList<>();

		String sql = "select transaction_id,card_id,amount,description_1,transaction_date,status,created_date from credit_card_transaction order by transaction_id DESC";
		LOGGER.info(sql);

		try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement()) {
			try (ResultSet rows = stmt.executeQuery(sql)) {

				while (rows.next()) {
					int transactionId = rows.getInt("transaction_id");
					long cardId = rows.getLong("card_id");
					float amount = rows.getFloat("amount");
					String description1 = rows.getString("description_1");
					Timestamp transactionDate = rows.getTimestamp("transaction_date");
					String status = rows.getString("status");
					Timestamp createdDate = rows.getTimestamp("created_date");
					LOGGER.debug(transactionId);
					LOGGER.debug(cardId);
					LOGGER.debug(amount);
					LOGGER.debug(description1);
					LOGGER.debug(transactionDate);
					LOGGER.debug(status);
					LOGGER.debug(createdDate);

					CreditCardTransaction creditCardTransaction = new CreditCardTransaction();
					creditCardTransaction.setTransactionId(transactionId);
					creditCardTransaction.setCardId(cardId);
					creditCardTransaction.setAmount(amount);
					creditCardTransaction.setMerchantId(description1);
					creditCardTransaction.setTransactionDate(transactionDate);
					creditCardTransaction.setStatus(status);
					creditCardTransaction.setCreatedDate(createdDate);
					c.add(creditCardTransaction);
				}
			}
		} catch (SQLException e) {

			throw new DbException(ErrorConstants.INVALID_SELECT,e);
		}
		return c;
	}

	public List<CreditCardTransaction> findByCardNo(long cardNo) throws DbException {
		List<CreditCardTransaction> history = new ArrayList<>();
		String sql = "select card_id,transaction_id,amount,description_1,transaction_date,status from credit_card_transaction where card_id=((select credit_card_id from credit_card where credit_card_no = ?))";
		LOGGER.info(sql);
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, cardNo);

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					int creditCardId = rs.getInt("card_id");
					int transactionId = rs.getInt("transaction_id");
					int balance = rs.getInt("amount");
					String description1 = rs.getString("description_1");
					Timestamp transactionDate = rs.getTimestamp("transaction_date");
					String status = rs.getString("status");
					LOGGER.debug(creditCardId);
					LOGGER.debug(transactionId);
					LOGGER.debug(balance);
					LOGGER.debug(description1);
					LOGGER.debug(transactionDate);
					LOGGER.debug(status);
					CreditCardTransaction creditCardTransaction = new CreditCardTransaction();
					creditCardTransaction.setCardId(creditCardId);
					creditCardTransaction.setTransactionId(transactionId);
					creditCardTransaction.setMerchantId(description1);
					creditCardTransaction.setAmount(balance);
					creditCardTransaction.setTransactionDate(transactionDate);
					creditCardTransaction.setStatus(status);
					history.add(creditCardTransaction);
				}
			}
		} catch (SQLException e) {

			throw new DbException(ErrorConstants.INVALID_SELECT,e);
		}
		return history;
	}

	public List<CreditCardTransaction> findByCardId(int cardId) throws DbException {
		List<CreditCardTransaction> history = new ArrayList<>();
		String sql = "select card_id,transaction_id,amount,description_1,transaction_date,status from credit_card_transaction where card_id=?";
		LOGGER.info(sql);
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, cardId);

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					int creditCardId = rs.getInt("card_id");
					int transactionId = rs.getInt("transaction_id");
					int balance = rs.getInt("amount");
					String description1 = rs.getString("description_1");
					Timestamp transactionDate = rs.getTimestamp("transaction_date");
					String status = rs.getString("status");
					LOGGER.debug(creditCardId);
					LOGGER.debug(transactionId);
					LOGGER.debug(balance);
					LOGGER.debug(description1);
					LOGGER.debug(transactionDate);
					LOGGER.debug(status);
					CreditCardTransaction creditCardTransaction = new CreditCardTransaction();
					creditCardTransaction.setCardId(creditCardId);
					creditCardTransaction.setTransactionId(transactionId);
					creditCardTransaction.setMerchantId(description1);
					creditCardTransaction.setAmount(balance);
					creditCardTransaction.setTransactionDate(transactionDate);
					creditCardTransaction.setStatus(status);
					history.add(creditCardTransaction);
				}
			}
		} catch (SQLException e) {

			throw new DbException(ErrorConstants.INVALID_SELECT,e);
		}
		return history;
	}

}
