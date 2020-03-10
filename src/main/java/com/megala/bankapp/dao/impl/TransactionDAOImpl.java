package com.megala.bankapp.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.megala.bankapp.dao.TransactionDAO;
import com.megala.bankapp.domain.Transaction;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.exception.ErrorConstants;
import com.megala.bankapp.util.Logger;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
	private static final Logger LOGGER = Logger.getInstance();
	@Autowired
	private DataSource dataSource;

	public void save(Transaction transaction) throws DbException {
		try (Connection con = dataSource.getConnection();
				CallableStatement pst = con.prepareCall("{call fund_transfer_procedure(?,?,?,?,?)}")) {

			pst.setInt(1, transaction.getTransactionId());
			pst.setLong(2, transaction.getAccNo());
			pst.setLong(3, transaction.getBeneficiaryAccNo());
			pst.setInt(4, transaction.getTransactionAmount());
			pst.registerOutParameter(5, Types.VARCHAR);
			pst.executeUpdate();
			String status = pst.getString(5);
			LOGGER.debug(status);
		} catch (SQLException e) {

			throw new DbException(ErrorConstants.INVALID_ADD,e);
		}
	}

	public List<Transaction> findAll() throws DbException {
		List<Transaction> t = new ArrayList<>();

		String sql = "select transaction_id,acc_no,beneficiary_acc_no,transaction_date,transaction_amount,status from transaction_details order by transaction_id DESC";
		LOGGER.info(sql);

		try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement()) {
			try (ResultSet rows = stmt.executeQuery(sql)) {

				while (rows.next()) {
					int transactionId = rows.getInt("transaction_id");
					long accNo = rows.getLong("acc_no");
					long beneficiaryAccNo = rows.getLong("beneficiary_acc_no");
					LocalDateTime transactionDate = rows.getTimestamp("transaction_date").toLocalDateTime();
					int transactionAmount = rows.getInt("transaction_amount");
					String status = rows.getString("status");
					LOGGER.debug(transactionId);
					LOGGER.debug(accNo);
					LOGGER.debug(beneficiaryAccNo);
					LOGGER.debug(transactionDate);
					LOGGER.debug(transactionAmount);
					LOGGER.debug(status);
					Transaction transaction = new Transaction();
					transaction.setTransactionId(transactionId);
					transaction.setAccNo(accNo);
					transaction.setBeneficiaryAccNo(beneficiaryAccNo);
					transaction.setTransactionDate(transactionDate);
					transaction.setTransactionAmount(transactionAmount);
					transaction.setStatus(status);

					t.add(transaction);
				}
			}
		} catch (SQLException e) {

			throw new DbException(ErrorConstants.INVALID_SELECT,e);
		}

		return t;
	}

	public void update(int transactionAmount, long beneficiaryAccNo) throws DbException {
		String sql = "update transaction_details set transaction_amount=? where beneficiary_acc_no=?";
		LOGGER.info(sql);

		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, transactionAmount);
			pst.setLong(2, beneficiaryAccNo);

			int rows = pst.executeUpdate();
			LOGGER.info("no of rows updated:" + rows);
		} catch (SQLException e) {

			throw new DbException(ErrorConstants.INVALID_UPDATE,e);
		}
	}

	public void delete(long beneficiaryAccNo) throws DbException {
		String sql = "delete from transaction_details where beneficiary_acc_no=?";
		LOGGER.info(sql);

		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, beneficiaryAccNo);

			int rows = pst.executeUpdate();
			LOGGER.info("no of rows deleted:" + rows);
		} catch (SQLException e) {

			throw new DbException(ErrorConstants.INVALID_DELETE,e);
		}

	}

	public List<Transaction> findByAccNo(long accNo) throws DbException {
		List<Transaction> t = new ArrayList<>();

		String sql = "select transaction_id,acc_no,beneficiary_acc_no,transaction_date,transaction_amount,status from transaction_details where acc_no=? order by transaction_id DESC";
		LOGGER.info(sql);

		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, accNo);
			try (ResultSet rows = pst.executeQuery()) {

				while (rows.next()) {
					int transactionId = rows.getInt("transaction_id");
					long accNum = rows.getLong("acc_no");
					long beneficiaryAccNo = rows.getLong("beneficiary_acc_no");
					LocalDateTime transactionDate = rows.getTimestamp("transaction_date").toLocalDateTime();
					int transactionAmount = rows.getInt("transaction_amount");
					String status = rows.getString("status");
					LOGGER.debug(transactionId);
					LOGGER.debug(accNum);
					LOGGER.debug(beneficiaryAccNo);
					LOGGER.debug(transactionDate);
					LOGGER.debug(transactionAmount);
					LOGGER.debug(status);
					Transaction transaction = new Transaction();
					transaction.setTransactionId(transactionId);
					transaction.setAccNo(accNum);
					transaction.setBeneficiaryAccNo(beneficiaryAccNo);
					transaction.setTransactionDate(transactionDate);
					transaction.setTransactionAmount(transactionAmount);
					transaction.setStatus(status);

					t.add(transaction);
				}
			}
		} catch (SQLException e) {

			throw new DbException(ErrorConstants.INVALID_SELECT,e);
		}

		return t;
	}

}
