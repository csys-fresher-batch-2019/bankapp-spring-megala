package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.Branch;
import com.megala.bankapp.exception.DbException;

public interface BranchDAO {
	void addBranch(Branch branch) throws DbException;

	List<Branch> list() throws DbException;

	void updateBranch(String name, int id) throws DbException;

	void delete(int id) throws DbException;

}
