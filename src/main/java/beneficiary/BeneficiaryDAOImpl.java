package beneficiary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bank.util.ConnectionUtil;
import logger.Logger;

public class BeneficiaryDAOImpl implements BeneficiaryDAO {
	private static final Logger LOGGER = Logger.getInstance();

	public int addBeneficiary(Beneficiary beneficiary)  {
		String sql ="insert into beneficiary_list(acc_number,beneficiary_name,acc_no_1,IFSC_code)values(?,?,?,?)";
		LOGGER.info(sql);
		int rows=0;
		try (Connection con = ConnectionUtil.getconnection();PreparedStatement pst = con.prepareStatement(sql)){
			pst.setLong(1, beneficiary.getCustomerAccNo());
			pst.setString(2, beneficiary.getBeneficiaryName());
			pst.setLong(3, beneficiary.getAccNo());
			pst.setString(4, beneficiary.getiFSCCode());
			rows = pst.executeUpdate();
			LOGGER.info("no of rows inserted:" + rows);
		} catch (Exception e) {
			
			LOGGER.error(e);
		}
		return rows;
}
	public List<Beneficiary> displayBeneficiary() {
		List<Beneficiary> b= new ArrayList<>();

		String sql ="select beneficiary_name,acc_no_1,IFSC_code from beneficiary_list";
		LOGGER.info(sql);

		try(Connection con = ConnectionUtil.getconnection();
		Statement stmt = con.createStatement()){
		try(ResultSet rows = stmt.executeQuery(sql)){

		while (rows.next()) {
			String beneficiaryName = rows.getString("beneficiary_name");
			long accNo = rows.getLong("acc_no_1");
			String iFSCCode=rows.getString("IFSC_code");
			LOGGER.getInput(beneficiaryName);
			LOGGER.getInput(accNo);
			LOGGER.getInput(iFSCCode);
			Beneficiary bene=new Beneficiary();
			bene.setBeneficiaryName(beneficiaryName);
			bene.setAccNo(accNo);
			bene.setiFSCCode(iFSCCode);
			b.add(bene);
		}
		}
		}catch(Exception e) {
			LOGGER.error(sql);

		}
		return b;
	}
	public void updateBeneficiary(String beneficiaryName,long accNo) {
		String sql = "update beneficiary_list set beneficiary_name=? where acc_no_1=?";
		LOGGER.info(sql);

		try(Connection con = ConnectionUtil.getconnection();
		PreparedStatement pst = con.prepareStatement(sql)){
		pst.setString(1, beneficiaryName);
		pst.setLong (2, accNo);

		int rows = pst.executeUpdate();
		LOGGER.info("no of rows updated:"+rows);
		}
		catch(Exception e) {
			LOGGER.error(e);

		}
	}
	public int deleteBeneficiary(long accNo) {
		String sql = "delete from beneficiary_list where acc_no_1=?";
		LOGGER.info(sql);
		int rows=0;
		try (
			Connection con = ConnectionUtil.getconnection();
			PreparedStatement pst = con.prepareStatement(sql)){
			pst.setLong(1,accNo);

			rows = pst.executeUpdate();
			LOGGER.info("no of rows deleted:" + rows);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return rows;
	}

	public List<Beneficiary> searchByBeneficiaryName(String name) {
		List<Beneficiary> a = new ArrayList<>();
		String sql = "select beneficiary_name,acc_no_1,IFSC_code from beneficiary_list where beneficiary_name=?";
		LOGGER.info(sql);
		try(
		Connection con = ConnectionUtil.getconnection();
		PreparedStatement pst = con.prepareStatement(sql)){
		pst.setString(1,name);
		try(
		ResultSet rows = pst.executeQuery()){
		if (rows.next()) {
			String beneName = rows.getString("beneficiary_name");
			long accNumber=rows.getLong("acc_no_1");
			String ifsc = rows.getString("IFSC_code");
			LOGGER.getInput(beneName);
			LOGGER.getInput(accNumber);
			LOGGER.getInput(ifsc);
			Beneficiary bene=new Beneficiary();
			bene.setBeneficiaryName(beneName);
			bene.setAccNo(accNumber);
			bene.setiFSCCode(ifsc);
			a.add(bene);
			
		}
		}
		}
		catch(Exception e) {
			LOGGER.error(e);
		}
		return a;

	}
	public List<Beneficiary> displayParBeneficiary(long cusAccNo) {
		List<Beneficiary> b= new ArrayList<>();

		String sql ="select beneficiary_name,acc_no_1,IFSC_code from beneficiary_list where acc_number=?";
		LOGGER.info(sql);

		try(
				Connection con = ConnectionUtil.getconnection();
				PreparedStatement pst = con.prepareStatement(sql)){
				pst.setLong(1,cusAccNo);
				try(
				ResultSet rows = pst.executeQuery()){

		while (rows.next()) {
			String beneficiaryName = rows.getString("beneficiary_name");
			long accNo = rows.getLong("acc_no_1");
			String iFSCCode=rows.getString("IFSC_code");
			LOGGER.getInput(beneficiaryName);
			LOGGER.getInput(accNo);
			LOGGER.getInput(iFSCCode);
			Beneficiary bene=new Beneficiary();
			bene.setBeneficiaryName(beneficiaryName);
			bene.setAccNo(accNo);
			bene.setiFSCCode(iFSCCode);
			b.add(bene);
		}
		}
		}catch(Exception e) {
			LOGGER.error(sql);

		}
		return b;
	}

}
