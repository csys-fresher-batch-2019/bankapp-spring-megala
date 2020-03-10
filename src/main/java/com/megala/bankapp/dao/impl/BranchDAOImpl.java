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

import com.megala.bankapp.dao.BranchDAO;
import com.megala.bankapp.domain.Branch;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.exception.ErrorConstants;

@Repository
public class BranchDAOImpl implements BranchDAO {
	// private static final Logger LOGGER = Logger.getInstance();
	private static final Logger logger = LoggerFactory.getLogger(BranchDAOImpl.class);
	@Autowired
	private DataSource dataSource;

	public void save(Branch branch) throws DbException {
		String sql = "insert into branch(branch_id,branch_name,branch_city)values(?,?,?)";
		logger.info(sql);
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, branch.getId());
			pst.setString(2, branch.getName());
			pst.setString(3, branch.getCity());

			int rows = pst.executeUpdate();
			logger.debug("no of rows inserted:" + rows);
		} catch (SQLException e) {

			throw new DbException(ErrorConstants.INVALID_ADD,e);
		}

	}

	public List<Branch> findAll() throws DbException, SQLException, IllegalAccessException {
		List<Branch> b = new ArrayList<>();

		String sql = "select branch_id,branch_name,branch_city from branch";
		logger.info(sql);

		try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement()) {
			try (ResultSet rows = stmt.executeQuery(sql)) {

				while (rows.next()) {
					int id = rows.getInt("branch_id");
					String name = rows.getString("branch_name");
					String city = rows.getString("branch_city");
					logger.debug("id:" + id + ",name:" + name + ",city:" + city);
					Branch branch = new Branch();
					branch.setId(id);
					branch.setName(name);
					branch.setCity(city);
					b.add(branch);
				}
			}
			catch (SQLException e) {
				throw new DbException(ErrorConstants.INVALID_CON,e);
			}
		} catch (RuntimeException e) {
			throw new DbException(ErrorConstants.INVALID_SELECT,e);
		}
		return b;
	}

	public void update(String name, int id) throws DbException {
		String sql = "update branch set branch_name=? where branch_id=?";
		logger.info(sql);

		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, name);
			pst.setInt(2, id);

			int rows = pst.executeUpdate();
			logger.info("no of rows updated:" + rows);
		} catch (SQLException e) {
			throw new DbException(ErrorConstants.INVALID_UPDATE,e);
		}
	}

	public void delete(int id) throws DbException {
		String sql = "delete from branch where branch_id=?";
		logger.info(sql);

		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, id);

			int rows = pst.executeUpdate();
			logger.info("no of rows deleted:" + rows);
		} catch (SQLException e) {

			throw new DbException(ErrorConstants.INVALID_DELETE,e);
		}

	}
}
