package account;

import java.util.List;

public interface AccountDAO {
	public void addAccount(Account account) ;
	public List<Account> displayAcc();
	public int updateAccount(long accNo,int amount) ;
	public void deleteAccount(long accNo) ;
	public List<Account> searchByAccountNo(long accNo) ;
	public void displayAccount1(int id) ;
	public int displayBalance(long accNo) ;
	public int activeAccount( long accNo,String status);
}
