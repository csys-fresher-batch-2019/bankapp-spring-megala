package branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bank.util.ConnectionUtil;
import logger.Logger;

public class BranchDAOImpl implements BranchDAO {
	private static final Logger LOGGER = Logger.getInstance();

	public void addBranch(Branch branch) {
		String sql = "insert into branch(branch_id,branch_name,branch_city)values(?,?,?)";
		LOGGER.info(sql);
		try (
			Connection con = ConnectionUtil.getconnection();
			PreparedStatement pst = con.prepareStatement(sql)){
			pst.setInt(1, branch.getId());
			pst.setString(2, branch.getName());
			pst.setString(3, branch.getCity());

			int rows = pst.executeUpdate();
			LOGGER.info("no of rows inserted:" + rows);
		} catch (Exception e) {
		
			LOGGER.error(e);
		}

	}

	public List<Branch> list() {
		List<Branch> b = new ArrayList<>();

		String sql = "select branch_id,branch_name,branch_city from branch";
		LOGGER.info(sql);

		try(Connection con = ConnectionUtil.getconnection();
		Statement stmt = con.createStatement()){
		try(ResultSet rows = stmt.executeQuery(sql)){

		while (rows.next()) {
			int id = rows.getInt("branch_id");
			String name = rows.getString("branch_name");
			String city = rows.getString("branch_city");
			LOGGER.debug("id:"+id+",name:"+name+",city:"+city);
			Branch branch=new Branch();
			branch.setId(id);
			branch.setName(name);
			branch.setCity(city);
			b.add(branch);
		}
		}
		}catch(Exception e) {
			LOGGER.error(e);
		}
		return b;
	}

	public void updateBranch(String name, int id){
		String sql = "update branch set branch_name=? where branch_id=?";
		LOGGER.info(sql);

		try(Connection con = ConnectionUtil.getconnection();
		PreparedStatement pst = con.prepareStatement(sql)){
		pst.setString(1, name);
		pst.setInt(2, id);

		int rows = pst.executeUpdate();
		LOGGER.info("no of rows updated:"+rows);
	}
		catch(Exception e) {
			LOGGER.error(e);
		}
	}

	public void delete(int id) {
		String sql = "delete from branch where branch_id=?";
		LOGGER.info(sql);
		
		try (
			Connection con = ConnectionUtil.getconnection();
			PreparedStatement pst = con.prepareStatement(sql)){
			pst.setInt(1,id);

			int rows = pst.executeUpdate();
			LOGGER.info("no of rows deleted:" + rows);
		} catch (Exception e) {
			
			LOGGER.error(e);
		}

	}
}
