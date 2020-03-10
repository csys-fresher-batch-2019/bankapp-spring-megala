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

import com.megala.bankapp.dao.BeneficiaryDAO;
import com.megala.bankapp.domain.Beneficiary;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.exception.ErrorConstants;

@Repository
public class BeneficiaryDAOImpl implements BeneficiaryDAO {
	// private static final Logger LOGGER = Logger.getInstance();
	private static final Logger logger = LoggerFactory.getLogger(BeneficiaryDAOImpl.class);
	@Autowired
	private DataSource dataSource;

	public int save(Beneficiary beneficiary) throws DbException {
		String sql = "insert into beneficiary_list(acc_number,beneficiary_name,acc_no_1,IFSC_code)values(?,?,?,?)";
		logger.info(sql);
		int rows = 0;
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, beneficiary.getCustomerAccNo());
			pst.setString(2, beneficiary.getBeneficiaryName());
			pst.setLong(3, beneficiary.getAccNo());
			pst.setString(4, beneficiary.getiFSCCode());
			rows = pst.executeUpdate();
			logger.debug("no of rows inserted:" + rows);
		} catch (SQLException e) {

			throw new DbException(ErrorConstants.INVALID_ADD);
		}
		return rows;
	}

	public List<Beneficiary> findAll() throws DbException {
		List<Beneficiary> b = new ArrayList<>();

		String sql = "select beneficiary_name,acc_no_1,IFSC_code from beneficiary_list";
		logger.info(sql);

		try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement()) {
			try (ResultSet rows = stmt.executeQuery(sql)) {

				while (rows.next()) {
					String beneficiaryName = rows.getString("beneficiary_name");
					long accNo = rows.getLong("acc_no_1");
					String iFSCCode = rows.getString("IFSC_code");
					logger.debug("beneficiaryName" + beneficiaryName);
					logger.debug("accNo" + accNo);
					logger.debug("iFSCCode" + iFSCCode);
					Beneficiary bene = new Beneficiary();
					bene.setBeneficiaryName(beneficiaryName);
					bene.setAccNo(accNo);
					bene.setiFSCCode(iFSCCode);
					b.add(bene);
				}
			}
		} catch (SQLException e) {
			throw new DbException(ErrorConstants.INVALID_SELECT);

		}
		return b;
	}

	public void update(String beneficiaryName, long accNo) throws DbException {
		String sql = "update beneficiary_list set beneficiary_name=? where acc_no_1=?";
		logger.info(sql);

		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, beneficiaryName);
			pst.setLong(2, accNo);

			int rows = pst.executeUpdate();
			logger.debug("no of rows updated:" + rows);
		} catch (SQLException e) {
			throw new DbException(ErrorConstants.INVALID_UPDATE);

		}
	}

	public int delete(long accNo) throws DbException {
		String sql = "delete from beneficiary_list where acc_no_1=?";
		logger.info(sql);
		int rows = 0;
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, accNo);

			rows = pst.executeUpdate();
			logger.debug("no of rows deleted:" + rows);
		} catch (SQLException e) {
			throw new DbException(ErrorConstants.INVALID_DELETE);
		}
		return rows;
	}

	public List<Beneficiary> findByName(String name) throws DbException {
		List<Beneficiary> a = new ArrayList<>();
		String sql = "select beneficiary_name,acc_no_1,IFSC_code,balance,status from beneficiary_list where beneficiary_name=?";
		logger.info(sql);
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, name);
			try (ResultSet rows = pst.executeQuery()) {
				if (rows.next()) {
					String beneName = rows.getString("beneficiary_name");
					long accNumber = rows.getLong("acc_no_1");
					String ifsc = rows.getString("IFSC_code");
					int balance = rows.getInt("balance");
					String status = rows.getString("status");
					logger.debug("beneName" + beneName);
					logger.debug("accNumber" + accNumber);
					logger.debug("ifsc" + ifsc);
					logger.debug("balance" + balance);
					logger.debug("comments" + status);
					Beneficiary bene = new Beneficiary();
					bene.setBeneficiaryName(beneName);
					bene.setAccNo(accNumber);
					bene.setiFSCCode(ifsc);
					bene.setAmount(balance);
					bene.setComments(status);
					a.add(bene);

				}
			}
		} catch (SQLException e) {
			throw new DbException(ErrorConstants.INVALID_SELECT);
		}
		return a;

	}

	public List<Beneficiary> findByCusAccNo(long cusAccNo) throws DbException {
		List<Beneficiary> b = new ArrayList<>();

		String sql = "select beneficiary_name,acc_no_1,IFSC_code,balance,status from beneficiary_list where acc_number=?";
		logger.info(sql);

		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, cusAccNo);
			try (ResultSet rows = pst.executeQuery()) {

				while (rows.next()) {
					String beneficiaryName = rows.getString("beneficiary_name");
					long accNo = rows.getLong("acc_no_1");
					String iFSCCode = rows.getString("IFSC_code");
					int amount = rows.getInt("balance");
					String comment = rows.getString("status");
					logger.debug("beneficiaryName" + beneficiaryName);
					logger.debug("accNo" + accNo);
					logger.debug("iFSCCode" + iFSCCode);
					logger.debug("amount" + amount);
					logger.debug("comment" + comment);
					Beneficiary bene = new Beneficiary();
					bene.setBeneficiaryName(beneficiaryName);
					bene.setAccNo(accNo);
					bene.setiFSCCode(iFSCCode);
					bene.setAmount(amount);
					bene.setComments(comment);
					b.add(bene);
				}
			}
		} catch (SQLException e) {
			throw new DbException(ErrorConstants.INVALID_SELECT);
		}
		return b;
	}

}
