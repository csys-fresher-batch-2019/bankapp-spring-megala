package com.megala.bankapp.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.megala.bankapp.dao.CustomerDAO;
import com.megala.bankapp.domain.Customer;
import com.megala.bankapp.domain.Register;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.util.Logger;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	private static final Logger LOGGER = Logger.getInstance();
	@Autowired
	private DataSource dataSource;

	public void save(Customer customer) throws DbException {
		String sql = "insert into customer_details(customer_name,customer_street,customer_city,mobile_no,email,password,acc_type)values(?,?,?,?,?,?,?)";
		LOGGER.info(sql);
		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, customer.getName());
			pst.setString(2, customer.getStreet());
			pst.setString(3, customer.getCity());
			pst.setLong(4, customer.getMobileNo());
			pst.setString(5, customer.getEmail());
			pst.setString(6, customer.getPassword());
			pst.setString(7, customer.getAccType());
			int rows = pst.executeUpdate();
			LOGGER.info("no of rows inserted:" + rows);
		} catch (SQLException e) {

			throw new DbException("Unable to add customer", e);
		}
	}

	public List<Customer> findAll() throws DbException {
		List<Customer> c = new ArrayList<>();

		String sql = "select customer_name,customer_id,customer_street,customer_city,mobile_no,email,password from customer_details";
		LOGGER.info(sql);

		try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement()) {
			try (ResultSet rows = stmt.executeQuery(sql)) {

				while (rows.next()) {
					String name = rows.getString("customer_name");
					int id = rows.getInt("customer_id");
					String street = rows.getString("customer_street");
					String city = rows.getString("customer_city");
					long mbleNo = rows.getLong("mobile_no");
					String mail = rows.getString("email");
					String password = rows.getString("password");
					Customer customer = new Customer();
					customer.setName(name);
					customer.setId(id);
					customer.setStreet(street);
					customer.setCity(city);
					customer.setMobileNo(mbleNo);
					customer.setEmail(mail);
					customer.setPassword(password);
					c.add(customer);
				}
			}
		} catch (SQLException e) {

			throw new DbException("Unable to display customer", e);
		}
		return c;
	}

	public void update(String name, int id) throws DbException {
		String sql = "update Customer_details set customer_name=? where customer_id=?";
		LOGGER.info(sql);

		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, name);
			pst.setInt(2, id);

			int rows = pst.executeUpdate();
			LOGGER.info("no of rows updated:" + rows);
		} catch (SQLException e) {

			throw new DbException("Unable to update customer name", e);
		}
	}

	public Register register(Customer c) throws DbException {
		Register reg = new Register();
		boolean result = false;
		try (Connection con = dataSource.getConnection();
				CallableStatement stmt = con.prepareCall("{call register_procedure(?,?,?,?,?,?,?,?,?)}")) {
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getStreet());
			stmt.setString(3, c.getCity());
			stmt.setLong(4, c.getMobileNo());
			stmt.setString(5, c.getEmail());
			stmt.setString(6, c.getPassword());
			stmt.setString(7, c.getAccType());
			stmt.registerOutParameter(8, Types.INTEGER);
			stmt.registerOutParameter(9, Types.VARCHAR);
			stmt.executeUpdate();
			String output = stmt.getString(9);
			long accountNo = stmt.getLong(8);

			if (output.equals("registered")) {
				LOGGER.info("Registered Successfully");
				result = true;
				reg.setAccNo(accountNo);
				reg.setStatus(result);

			} else {
				LOGGER.info("Registration failed");
				reg.setAccNo(accountNo);
				reg.setStatus(result);
			}
		}catch(SQLException e) {
			throw new DbException("Unable to register", e);

		}
			return reg;
	}
	
	public PaymentResponse login(String email, String password) throws DbException {
		PaymentResponse response = new PaymentResponse();
		boolean result = false;
		try (Connection con = dataSource.getConnection();
				CallableStatement stmt = con.prepareCall("{call login_procedure(?,?,?,?)}")) {
			stmt.setString(1, email);
			stmt.setString(2, password);
			stmt.registerOutParameter(3, Types.INTEGER);
			stmt.registerOutParameter(4, Types.VARCHAR);
			stmt.executeUpdate();
			Long acc = stmt.getLong(3);
			String status = stmt.getString(4);
			if (status.equals("Login Successfull")) {
				LOGGER.info("Login Successfull");
				result = true;
				response.setAccountNo(acc);
				response.setStatus(result);

			} else {
				LOGGER.info("Login failed");
				result = false;
				response.setAccountNo(acc);
				response.setStatus(result);
			}
		} catch (SQLException e) {
			result = false;
			response.setStatus(result);
			throw new DbException(e.getMessage());
		}
		return response;

	}
	public void delete(int id) throws DbException {
		String sql = "delete from customer_details where customer_id=?";
		LOGGER.info(sql);

		try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, id);

			int rows = pst.executeUpdate();
			LOGGER.info("no of rows deleted:" + rows);
		} catch (SQLException e) {

			throw new DbException("Unable to delete customer details", e);
		}

	}
}