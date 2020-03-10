package com.megala.bankapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.megala.bankapp.domain.Branch;
import com.megala.bankapp.exception.DbException;

public interface BranchDAO {
	void save(Branch branch) throws DbException;

	List<Branch> findAll() throws DbException, SQLException, IllegalAccessException;

	void update(String name, int id) throws DbException;

	void delete(int id) throws DbException;

}
