package account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bank.util.ConnectionUtil;
import logger.Logger;

public class AccountDAOImpl implements AccountDAO {
	
	private static final Logger LOGGER = Logger.getInstance();
	private static final String ACTION1="customer_id";
	private static final String ACTION2="acc_type";
	private static final String ACTION3="acc_no";
	private static final String ACTION4="available_balance";

	public void addAccount(Account account){
		String sql = "insert into account_details(customer_id,acc_type,available_balance)values(?,?,?)";
		LOGGER.info(sql);
		try(Connection con = ConnectionUtil.getconnection();
			PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, account.getCustomerId());
			pst.setString(2, account.getAccType());
			pst.setInt(3, account.getAvailableBalance());
			int rows = pst.executeUpdate();
			LOGGER.info("no of rows inserted:" + rows);
		} catch (Exception e) {
			
			LOGGER.error(e);
		}

	}
	
	public List<Account> displayAcc() {
		List<Account> a = new ArrayList<>();

		String sql = "select customer_id,acc_no,acc_type,available_balance,status from account_details";
		LOGGER.info(sql);
		try(Connection con = ConnectionUtil.getconnection();
		Statement stmt = con.createStatement()){
		try(ResultSet rows = stmt.executeQuery(sql)){

		while (rows.next()) {
			int customerId = rows.getInt(ACTION1);
			long accNo = rows.getLong(ACTION3);
			String accType = rows.getString(ACTION2);
			int availableBalance = rows.getInt(ACTION4);
			String accStatus=rows.getString("status");
			LOGGER.getInput(customerId);
			LOGGER.getInput(accNo);
			LOGGER.getInput(accType);
			LOGGER.getInput(availableBalance);
			LOGGER.getInput(accStatus);

			Account account=new Account();
			account.setCustomerId(customerId);
			account.setAccNo(accNo);
			account.setAccType(accType);
			account.setAvailableBalance(availableBalance);
			account.setStatus(accStatus);
			a.add(account);
			
		}
		}
		}
		catch(Exception e) {
			LOGGER.error(e);
		}
		return a;
	
	}
	public int updateAccount( long accNo,int amount) {
		String sql = "update account_details set available_balance=? where acc_no=?";
		LOGGER.info(sql);
		int rows=0;
		try(Connection con = ConnectionUtil.getconnection();
		PreparedStatement pst = con.prepareStatement(sql)){
		pst.setInt(1, amount);
		pst.setLong(2,accNo );
		rows = pst.executeUpdate();
		LOGGER.info("no of rows updated:"+rows);
		}
		catch(Exception e) {
			LOGGER.error(e);
		}
		return rows;
	}
	public int activeAccount( long accNo,String status) {
		String sql = "update account_details set status=? where acc_no=?";
		LOGGER.info(sql);
		int rows=0;
		try(Connection con = ConnectionUtil.getconnection();
		PreparedStatement pst = con.prepareStatement(sql)){
		pst.setString(1, status);
		pst.setLong(2,accNo );

		rows = pst.executeUpdate();
		LOGGER.info("no of rows updated:"+rows);
		}
		catch(Exception e) {
			LOGGER.error(e);
		}
		return rows;
	}
	public void deleteAccount(long accNo) {
		String sql = "delete from account_details where acc_no=?";
		LOGGER.info(sql);
		
		try (
			Connection con = ConnectionUtil.getconnection();
			PreparedStatement pst = con.prepareStatement(sql)){
			pst.setLong(1,accNo);

			int rows = pst.executeUpdate();
			LOGGER.info("no of rows deleted:" + rows);
		} catch (Exception e) {
			
			LOGGER.error(e);
		}

	}
	public List<Account> searchByAccountNo(long accNo) {
		List<Account> a = new ArrayList<>();
		String sql = "select customer_id,acc_no,acc_type,available_balance from account_details where acc_no=?";
		LOGGER.info(sql);
		try(
		Connection con = ConnectionUtil.getconnection();
		PreparedStatement pst = con.prepareStatement(sql)){
		pst.setLong(1,accNo );
		try(
		ResultSet rows = pst.executeQuery()){
		if (rows.next()) {
			int customerId = rows.getInt(ACTION1);
			long accNumber=rows.getLong(ACTION3);
			String accType=rows.getString(ACTION2);
			int availableBalance = rows.getInt(ACTION4);
			
			Account account=new Account();
			account.setCustomerId(customerId);
			account.setAccNo(accNumber);
			account.setAccType(accType);
			account.setAvailableBalance(availableBalance);
			a.add(account);
			
			LOGGER.getInput(customerId);
			LOGGER.getInput(accNumber);
			LOGGER.getInput(accType);
			LOGGER.getInput(availableBalance);
			
		}
		}
		}
		catch(Exception e) {
			LOGGER.error(e);
		}
		return a;

	}
	
	public void displayAccount1(int id) {
		String sql = "select acc_no,acc_type,available_balance from account_details where customer_id=?";
		LOGGER.info(sql);
		try(Connection con = ConnectionUtil.getconnection();
		PreparedStatement pst = con.prepareStatement(sql)){
		pst.setInt(1,id);
		try(ResultSet rows = pst.executeQuery()){
		if (rows.next()) {
			long accNo = rows.getLong(ACTION3);
			String accType=rows.getString(ACTION2);
			int availableBalance = rows.getInt(ACTION4);
			
			LOGGER.getInput(accNo);
			LOGGER.getInput(accType);
			LOGGER.getInput(availableBalance);
		}
		}
		}
		catch(Exception e) {
			LOGGER.error(e);
		}
}
	
	public int displayBalance(long accNo) {
		String sql = "select available_balance from account_details where acc_no=?";
		LOGGER.info(sql);
		int availableBalance=0;
		try(Connection con = ConnectionUtil.getconnection();
		PreparedStatement pst = con.prepareStatement(sql)){
		pst.setLong(1,accNo);
		try(ResultSet rows = pst.executeQuery()){
		if (rows.next()) {
			availableBalance = rows.getInt(ACTION4);
			
			LOGGER.getInput(availableBalance);
		}
}
		}
		catch(Exception e) {
			LOGGER.error(e);
		}
		return availableBalance;
}
}