package loan;

import java.util.List;

public interface LoanDAO {
	void addLoan(Loan loan) ;
	public List<Loan> displayLoan() ;
	public void updateLoan(LoanStatusEnum status, int id);
	public void deleteLoan(String loanNo) ;
		 

}
