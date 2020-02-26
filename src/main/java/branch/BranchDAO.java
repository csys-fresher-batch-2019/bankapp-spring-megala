package branch;

import java.util.List;

public interface BranchDAO {
	void addBranch(Branch branch);
    List<Branch> list() ;
    void updateBranch(String name,int id) ;
    void delete(int id) ;
    
}
