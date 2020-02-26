package transaction;

import java.util.List;

public interface TransactionDAO {
	public void addTransaction(Transaction transaction) ;
	public List<Transaction> displayTransaction() ;
	public void updateTransaction(int transactionAmount,long beneficiaryAccNo) ;
	public void deleteTransaction(long beneficiaryAccNo) ; 
	public List<Transaction> displayParTransaction(long accNo);
}
