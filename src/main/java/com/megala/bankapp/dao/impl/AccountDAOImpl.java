package com.megala.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.megala.bankapp.dao.AccountDAO;
import com.megala.bankapp.domain.Account;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.exception.ErrorConstants;

@Repository
public class AccountDAOImpl implements AccountDAO {

	// private static final Logger LOGGER = Logger.getInstance();
	private static final Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);

	private static final String ACTION1 = "customer_id";
	private static final String ACTION2 = "acc_type";
	private static final String ACTION3 = "acc_no";
	private static final String ACTION4 = "available_balance";

	@Autowired
	private DataSource dataSource;

	public void save(Account account) throws DbException {
		String sql = "insert into account_details(customer_id,acc_type,available_balance)values(?,?,?)";
		logger.info(sql);
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, account.getCustomerId());
			pst.setString(2, account.getAccType());
			pst.setInt(3, account.getAvailableBalance());
			int rows = pst.executeUpdate();
			logger.debug("no of rows inserted:" + rows);
		} catch (SQLException e) {			
			throw new DbException(ErrorConstants.INVALID_ADD, e);
		}

	}

	public List<Account> findAll() throws DbException {
		List<Account> a = new ArrayList<>();

		String sql = "select customer_id,acc_no,acc_type,available_balance,status from account_details";
		logger.info(sql);
		try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement()) {
			try (ResultSet rows = stmt.executeQuery(sql)) {

				while (rows.next()) {
					int customerId = rows.getInt(ACTION1);
					long accNo = rows.getLong(ACTION3);
					String accType = rows.getString(ACTION2);
					int availableBalance = rows.getInt(ACTION4);
					String accStatus = rows.getString("status");
					logger.debug("customerId" + customerId);
					logger.debug("accNo" + accNo);
					logger.debug("accType" + accType);
					logger.debug("availableBalance" + availableBalance);
					logger.debug("accStatus" + accStatus);

					Account account = new Account();
					account.setCustomerId(customerId);
					account.setAccNo(accNo);
					account.setAccType(accType);
					account.setAvailableBalance(availableBalance);
					account.setStatus(accStatus);
					a.add(account);

				}
			}
		} catch (SQLException e) {
			throw new DbException(ErrorConstants.INVALID_SELECT,e);
		}
		return a;

	}

	public Account getAccount(String email) throws DbException {

		Account account = null;
		String sql = "select customer_id,acc_no,acc_type,available_balance,status from account_details where customer_id in ("
				+ " select customer_id from customer_details where email =?) ";
		logger.info(sql);
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, email);
			try (ResultSet rows = pst.executeQuery()) {

				if (rows.next()) {
					int customerId = rows.getInt(ACTION1);
					long accNo = rows.getLong(ACTION3);
					String accType = rows.getString(ACTION2);
					int availableBalance = rows.getInt(ACTION4);
					String accStatus = rows.getString("status");
					logger.debug("customerId" + customerId);
					logger.debug("accNo" + accNo);
					logger.debug("accType" + accType);
					logger.debug("availableBalance" + availableBalance);
					logger.debug("accStatus" + accStatus);

					account = new Account();
					account.setCustomerId(customerId);
					account.setAccNo(accNo);
					account.setAccType(accType);
					account.setAvailableBalance(availableBalance);
					account.setStatus(accStatus);

				}
			}
		} catch (SQLException e) {
			throw new DbException(ErrorConstants.INVALID_SELECT,e);
		}
		return account;

	}

	public int update(long accNo, int amount) throws DbException {
		String sql = "update account_details set available_balance=? where acc_no=?";
		logger.info(sql);
		int rows = 0;
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, amount);
			pst.setLong(2, accNo);
			rows = pst.executeUpdate();
			logger.info("no of rows updated:" + rows);
		} catch (Exception e) {
			throw new DbException(ErrorConstants.INVALID_UPDATE,e);
		}
		return rows;
	}

	public int activeAccount(long accNo, String status) throws DbException {
		String sql = "update account_details set status=? where acc_no=?";
		logger.info(sql);
		int rows = 0;
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, status);
			pst.setLong(2, accNo);

			rows = pst.executeUpdate();
			logger.info("no of rows updated:" + rows);
		} catch (SQLException e) {
			throw new DbException(ErrorConstants.INVALID_UPDATE,e);
		}
		return rows;
	}

	public void delete(long accNo) throws DbException {
		String sql = "delete from account_details where acc_no=?";
		logger.info(sql);

		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, accNo);

			int rows = pst.executeUpdate();
			logger.info("no of rows deleted:" + rows);
		} catch (SQLException e) {

			throw new DbException(ErrorConstants.INVALID_DELETE,e);
		}

	}

	public List<Account> findByAccNo(long accNo) throws DbException {
		List<Account> a = new ArrayList<>();
		String sql = "select customer_id,acc_no,acc_type,available_balance,status from account_details where acc_no=?";
		logger.info(sql);
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, accNo);
			try (ResultSet rows = pst.executeQuery()) {
				if (rows.next()) {
					int customerId = rows.getInt(ACTION1);
					long accNumber = rows.getLong(ACTION3);
					String accType = rows.getString(ACTION2);
					int availableBalance = rows.getInt(ACTION4);
					String status = rows.getString("status");

					Account account = new Account();
					account.setCustomerId(customerId);
					account.setAccNo(accNumber);
					account.setAccType(accType);
					account.setAvailableBalance(availableBalance);
					account.setStatus(status);
					a.add(account);

					logger.debug("account"+account);

				}
			}
		} catch (SQLException e) {
			throw new DbException(ErrorConstants.INVALID_SELECT,e);
		}
		return a;

	}

	public void findById(int id) throws DbException {
		String sql = "select acc_no,acc_type,available_balance from account_details where customer_id=?";
		logger.info(sql);
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, id);
			try (ResultSet rows = pst.executeQuery()) {
				if (rows.next()) {
					long accNo = rows.getLong(ACTION3);
					String accType = rows.getString(ACTION2);
					int availableBalance = rows.getInt(ACTION4);

					logger.debug("accNo" + accNo);
					logger.debug("accType" + accType);
					logger.debug("Balance" + availableBalance);
				}
			}
		} catch (SQLException e) {
			throw new DbException(ErrorConstants.INVALID_SELECT,e);
		}
	}

	public int findBalanceByAccNo(long accNo) throws DbException {
		String sql = "select available_balance from account_details where acc_no=?";
		logger.info(sql);
		int availableBalance = 0;
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, accNo);
			try (ResultSet rows = pst.executeQuery()) {
				if (rows.next()) {
					availableBalance = rows.getInt(ACTION4);

					logger.debug("Balance" + availableBalance);
				}
			}
		} catch (SQLException e) {
			throw new DbException(ErrorConstants.INVALID_SELECT,e);
		}
		return availableBalance;
	}

	public String findStatusByAccNo(long accNo) throws DbException {
		String sql = "select status from account_details where acc_no=?";
		logger.info(sql);
		String status = null;
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, accNo);
			try (ResultSet rows = pst.executeQuery()) {
				if (rows.next()) {
					status = rows.getString("status");

					logger.debug("Balance" + status);
				}
			}
		} catch (SQLException e) {
			throw new DbException(ErrorConstants.INVALID_SELECT,e);
		}
		return status;
	}

}