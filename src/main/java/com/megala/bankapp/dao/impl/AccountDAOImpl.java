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

@Repository
public class AccountDAOImpl implements AccountDAO {

	private static final Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);

	private static final String CUSTOMERID = "customer_id";
	private static final String ACCTYPE = "acc_type";
	private static final String ACCNO = "acc_no";
	private static final String AVAILABLEBALANCE = "available_balance";

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
			throw new DbException("Unable to add account", e);
		}

	}

	public List<Account> findAll() throws DbException {
		List<Account> a = new ArrayList<>();

		String sql = "select customer_id,acc_no,acc_type,available_balance,status from account_details";
		logger.info(sql);
		try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement()) {
			try (ResultSet rows = stmt.executeQuery(sql)) {

				while (rows.next()) {
					int customerId = rows.getInt(CUSTOMERID);
					long accNo = rows.getLong(ACCNO);
					String accType = rows.getString(ACCTYPE);
					int availableBalance = rows.getInt(AVAILABLEBALANCE);
					String accStatus = rows.getString("status");

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
			throw new DbException("Unable to display account details", e);
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
					int customerId = rows.getInt(CUSTOMERID);
					long accNo = rows.getLong(ACCNO);
					String accType = rows.getString(ACCTYPE);
					int availableBalance = rows.getInt(AVAILABLEBALANCE);
					String accStatus = rows.getString("status");
					account = new Account();
					account.setCustomerId(customerId);
					account.setAccNo(accNo);
					account.setAccType(accType);
					account.setAvailableBalance(availableBalance);
					account.setStatus(accStatus);

				}
			}
		} catch (SQLException e) {
			throw new DbException("Unable to display account details", e);
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
			throw new DbException("Unable to update account", e);
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
			throw new DbException("Unable to active account", e);
		}
		return rows;
	}

	public List<Account> findByAccNo(long accNo) throws DbException {
		List<Account> a = new ArrayList<>();
		String sql = "select customer_id,acc_no,acc_type,available_balance,status from account_details where acc_no=?";
		logger.info(sql);
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, accNo);
			try (ResultSet rows = pst.executeQuery()) {
				if (rows.next()) {
					int customerId = rows.getInt(CUSTOMERID);
					long accNumber = rows.getLong(ACCNO);
					String accType = rows.getString(ACCTYPE);
					int availableBalance = rows.getInt(AVAILABLEBALANCE);
					String status = rows.getString("status");

					Account account = new Account();
					account.setCustomerId(customerId);
					account.setAccNo(accNumber);
					account.setAccType(accType);
					account.setAvailableBalance(availableBalance);
					account.setStatus(status);
					a.add(account);
				}
			}
		} catch (SQLException e) {
			throw new DbException("Unable to display account", e);
		}
		return a;

	}

	public int findBalanceByAccNo(long accNo) throws DbException {
		String sql = "select available_balance from account_details where acc_no=?";
		logger.info(sql);
		int availableBalance = 0;
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, accNo);
			try (ResultSet rows = pst.executeQuery()) {
				if (rows.next()) {
					availableBalance = rows.getInt(AVAILABLEBALANCE);
				}
			}
		} catch (SQLException e) {
			throw new DbException("Unable to display balance", e);
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
				}
			}
		} catch (SQLException e) {
			throw new DbException("Unable to display status", e);
		}
		return status;
	}

}