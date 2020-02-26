package creditcard;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bank.util.ConnectionUtil;
import logger.Logger;

public class CreditCardDAOImpl implements CreditCardDAO {
	private static final Logger LOGGER = Logger.getInstance();

	public void addCreditCard(CreditCard creditCard) {
		String sql ="insert into credit_card(credit_card_no,credit_card_pin,acc_no,card_limit,cvv_no,expiry_date,available_balance)values(?,?,?,?,?,?,?)";
		LOGGER.info(sql);
		try(Connection con = ConnectionUtil.getconnection();
			PreparedStatement pst = con.prepareStatement(sql)){
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
		
			LOGGER.error(e);
		}
}
	public int displayCreditCard(long cardNo,LocalDate expiryDate,int cvvNo) {
		String sql ="select credit_card_id from credit_card where credit_card_no=? and expiry_date=? and cvv_no=? ";
		int creditCardId = 0;
		try (
			Connection con = ConnectionUtil.getconnection();
			PreparedStatement pst = con.prepareStatement(sql)){
			pst.setLong(1,cardNo);
			pst.setDate(2,Date.valueOf(expiryDate));
			pst.setInt(3,cvvNo);
			
			try(ResultSet rs = pst.executeQuery()){
			if (rs.next()) {
				creditCardId = rs.getInt("credit_card_id");
			}
		}
		}catch (Exception e) {
			
			LOGGER.error(e);
		}
		return creditCardId;
	}
	public List<CreditCard> displayCreditCards()  {
		List<CreditCard> c= new ArrayList<>();

		String sql ="select credit_card_no,acc_no,card_limit,expiry_date from credit_card";
		LOGGER.info(sql);

		try(Connection con = ConnectionUtil.getconnection();
		Statement stmt = con.createStatement()){
		try(ResultSet rows = stmt.executeQuery(sql)){

		while (rows.next()) {
			long creditCardNo = rows.getLong("credit_card_no");
			long accNo = rows.getLong("acc_no");
			String limitNo=rows.getString("card_limit");
			LocalDate expiryDate = rows.getDate("expiry_date").toLocalDate();
			LOGGER.debug(creditCardNo);
			LOGGER.debug(accNo);
			LOGGER.debug(limitNo);
			LOGGER.debug(expiryDate);

			CreditCard creditcard=new CreditCard();
			c.add(creditcard);
		}
		}
		}catch (Exception e) {
		
			LOGGER.error(e);
		}
		return c;
	}
	
	public List<CreditCard> displayCreditCardsByAccNo(long accNo)  {
		List<CreditCard> c= new ArrayList<>();

		String sql ="select credit_card_id,credit_card_no,card_limit,expiry_date,cvv_no,available_balance,credit_card_pin from credit_card where acc_no=?";
		LOGGER.info(sql);

		try (
				Connection con = ConnectionUtil.getconnection();
				PreparedStatement pst = con.prepareStatement(sql)){
				pst.setLong(1,accNo);

				try(ResultSet rows = pst.executeQuery()){

		while (rows.next()) {
			int cardId=rows.getInt("credit_card_id");
			long creditCardNo = rows.getLong("credit_card_no");
			int limitNo=rows.getInt("card_limit");
			LocalDate expiryDate = rows.getDate("expiry_date").toLocalDate();
			int cvv=rows.getInt("cvv_no");
			float balance=rows.getFloat("available_balance");
			int pin=rows.getInt("credit_card_pin");
			LOGGER.debug(cardId);
			LOGGER.debug(creditCardNo);
			LOGGER.debug(limitNo);
			LOGGER.debug(expiryDate);
			LOGGER.debug(cvv);
			LOGGER.debug(balance);
			LOGGER.debug(pin);


			CreditCard creditCard=new CreditCard();
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
		}catch (Exception e) {
		
			LOGGER.error(e);
		}
		return c;
	}
	
	public void deleteCreditCard(long accNo) {
		String sql = "delete from credit_card where acc_no=?";
		LOGGER.info(sql);
		
		try (
			Connection con = ConnectionUtil.getconnection();
			PreparedStatement pst = con.prepareStatement(sql)){
			pst.setLong(1,accNo);

			int rows = pst.executeUpdate();
			LOGGER.info("no of rows deleted:" + rows);
		}catch (Exception e) {
		
			LOGGER.error(e);
		}

	}
	public void updateCreditCard1(String comments,long creditCardNo,boolean blocked) {
		
		String sql="update credit_card set comments=?,blocked=? where credit_card_no=?";
		LOGGER.info(sql);
		try(Connection con = ConnectionUtil.getconnection();
		PreparedStatement pst = con.prepareStatement(sql)){
		pst.setString (1,comments);
		pst.setInt(2, blocked?1:0);
		pst.setLong(3, creditCardNo);
		int rows = pst.executeUpdate();
		LOGGER.info("no of rows updated:"+rows);

	}catch (Exception e) {
		
		LOGGER.error(e);
	}

	}
	
	public float displayBalance(long cardNo) {
		String sql ="select available_balance from credit_card where credit_card_no=?";
		float availableBalance = 0;
		try (
			Connection con = ConnectionUtil.getconnection();
			PreparedStatement pst = con.prepareStatement(sql)){
			pst.setLong(1,cardNo);
			
			try(ResultSet rs = pst.executeQuery()){
			if (rs.next()) {
				availableBalance = rs.getFloat("available_balance");
			}
		}
		}catch (Exception e) {
			
			LOGGER.error(e);
		}
		return availableBalance;
	}
}
