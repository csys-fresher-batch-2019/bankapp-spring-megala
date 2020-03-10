package com.megala.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.megala.bankapp.dao.CreditCardDAO;
import com.megala.bankapp.domain.CreditCard;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.exception.ErrorConstants;
import com.megala.bankapp.util.Logger;

@Repository
public class CreditCardDAOImpl implements CreditCardDAO {
	private static final Logger LOGGER = Logger.getInstance();
	@Autowired
	private DataSource dataSource;
	public void save(CreditCard creditCard) throws DbException{
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
		} catch (Exception e) {
			throw new DbException(ErrorConstants.INVALID_ADD);
		}
	}

	public int findId(long cardNo, LocalDate expiryDate, int cvvNo) throws DbException{
		String sql = "select credit_card_id from credit_card where credit_card_no=? and expiry_date=? and cvv_no=? ";
		int creditCardId = 0;
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, cardNo);
			pst.setDate(2, Date.valueOf(expiryDate));
			pst.setInt(3, cvvNo);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					creditCardId = rs.getInt("credit_card_id");
				}
			}
		} catch (Exception e) {

			throw new DbException(ErrorConstants.INVALID_SELECT);
		}
		return creditCardId;
	}

	public List<CreditCard> findAll() throws DbException  {
		List<CreditCard> c = new ArrayList<>();

		String sql = "select credit_card_no,acc_no,card_limit,expiry_date from credit_card";
		LOGGER.info(sql);

		try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement()) {
			try (ResultSet rows = stmt.executeQuery(sql)) {

				while (rows.next()) {
					long creditCardNo = rows.getLong("credit_card_no");
					long accNo = rows.getLong("acc_no");
					String limitNo = rows.getString("card_limit");
					LocalDate expiryDate = rows.getDate("expiry_date").toLocalDate();
					LOGGER.debug(creditCardNo);
					LOGGER.debug(accNo);
					LOGGER.debug(limitNo);
					LOGGER.debug(expiryDate);

					CreditCard creditcard = new CreditCard();
					c.add(creditcard);
				}
			}
		} catch (Exception e) {

			throw new DbException(ErrorConstants.INVALID_SELECT);
		}
		return c;
	}

	public List<CreditCard> findAllByAccNo(long accNo) throws DbException{
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
					LOGGER.debug(cardId);
					LOGGER.debug(creditCardNo);
					LOGGER.debug(limitNo);
					LOGGER.debug(expiryDate);
					LOGGER.debug(cvv);
					LOGGER.debug(balance);
					LOGGER.debug(pin);

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
		} catch (Exception e) {

			throw new DbException(ErrorConstants.INVALID_SELECT);
		}
		return c;
	}

	public void delete(long accNo) throws DbException{
		String sql = "delete from credit_card where acc_no=?";
		LOGGER.info(sql);

		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, accNo);

			int rows = pst.executeUpdate();
			LOGGER.info("no of rows deleted:" + rows);
		} catch (Exception e) {

			throw new DbException(ErrorConstants.INVALID_DELETE);
		}

	}

	public void update(String comments, long creditCardNo, boolean blocked) throws DbException{

		String sql = "update credit_card set comments=?,blocked=? where credit_card_no=?";
		LOGGER.info(sql);
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, comments);
			pst.setInt(2, blocked ? 1 : 0);
			pst.setLong(3, creditCardNo);
			int rows = pst.executeUpdate();
			LOGGER.info("no of rows updated:" + rows);

		} catch (Exception e) {

			throw new DbException(ErrorConstants.INVALID_UPDATE);
		}

	}

	public float findBalance(long cardNo) throws DbException{
		String sql = "select available_balance from credit_card where credit_card_no=?";
		float availableBalance = 0;
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setLong(1, cardNo);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					availableBalance = rs.getFloat("available_balance");
				}
			}
		} catch (Exception e) {

			throw new DbException(ErrorConstants.INVALID_SELECT);
		}
		return availableBalance;
	}


}
