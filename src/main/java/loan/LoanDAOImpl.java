package loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bank.util.ConnectionUtil;
import logger.Logger;


public class LoanDAOImpl implements LoanDAO {
	private static final Logger LOGGER = Logger.getInstance();
	public void addLoan(Loan loan)  {
		String sql = "insert into loan_details(customer_id,branch_name,loan_no,amount,loan_status)values(?,?,?,?,?)";
		LOGGER.info(sql);
		try 
			(Connection con = ConnectionUtil.getconnection();
			PreparedStatement pst = con.prepareStatement(sql)){
			pst.setInt(1, loan.getCustomerId());
			pst.setString(2, loan.getBranchName());
			pst.setString(3, loan.getLoanNo());
			pst.setInt(4, loan.getAmount());
			pst.setString(5,loan.getStatus().toString());
			int rows = pst.executeUpdate();
			LOGGER.info("no of rows inserted:" + rows);
		} catch (Exception e) {
			
			LOGGER.error(e);
		}
}
	public List<Loan> displayLoan() {
		List<Loan> l = new ArrayList<>();

		String sql = "select customer_id,branch_name,loan_no,amount from loan_details";
		LOGGER.info(sql);

		try(Connection con = ConnectionUtil.getconnection();
		Statement stmt = con.createStatement()){
		try(ResultSet rows = stmt.executeQuery(sql)){
		

		while (rows.next()) {
			int customerId = rows.getInt("customer_id");
			String name = rows.getString("branch_name");
			String loanNo=rows.getString("loan_no");
			int amount = rows.getInt("amount");

			LOGGER.debug(customerId);
			LOGGER.debug(name);
			LOGGER.debug(loanNo);
			LOGGER.debug(amount);
			
			Loan loan=new Loan();
			l.add(loan);
		}
		}
		}catch (Exception e) {
		
			LOGGER.error(e);
		}
		return l;
	}
	public void updateLoan(LoanStatusEnum status, int id) {
		String sql = "update loan_details set loan_status=? where customer_id=?";
		LOGGER.info(sql);

		try(Connection con = ConnectionUtil.getconnection();
		PreparedStatement pst = con.prepareStatement(sql)){
		pst.setString(1, status.toString());
		pst.setInt(2, id);

		int rows = pst.executeUpdate();
		LOGGER.info("no of rows updated:"+rows);
		}
		catch (Exception e) {
			
			LOGGER.error(e);
		}
	}
	public void deleteLoan(String loanNo) {
		String sql = "delete from loan_details where loan_no=?";
		LOGGER.info(sql);
		
		try 
			(Connection con = ConnectionUtil.getconnection();
			PreparedStatement pst = con.prepareStatement(sql)){
			pst.setString(1,loanNo);

			int rows = pst.executeUpdate();
			LOGGER.info("no of rows deleted:" + rows);
		} catch (Exception e) {
			LOGGER.error(e);
			}
}
}