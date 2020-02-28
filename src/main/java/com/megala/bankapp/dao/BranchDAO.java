package com.megala.bankapp.dao;

import java.util.List;

import com.megala.bankapp.domain.Branch;

public interface BranchDAO {
	void addBranch(Branch branch);
    List<Branch> list() ;
    void updateBranch(String name,int id) ;
    void delete(int id) ;
    
}
