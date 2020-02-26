package factory;

import account.AccountDAO;
import account.AccountDAOImpl;
import beneficiary.BeneficiaryDAO;
import beneficiary.BeneficiaryDAOImpl;
import branch.BranchDAO;
import branch.BranchDAOImpl;
import creditcard.CreditCardDAO;
import creditcard.CreditCardDAOImpl;
import creditcardtrans.CreditCardTransactionDAO;
import creditcardtrans.CreditCardTransactionDAOImpl;
import customer.CustomerDAO;
import customer.CustomerDAOImpl;
import loan.LoanDAO;
import loan.LoanDAOImpl;
import transaction.TransactionDAO;
import transaction.TransactionDAOImpl;

public class DAOFactory {
	private DAOFactory() {
		throw new IllegalStateException("Utility class");
	}

	public static BranchDAO getBranchDAO() {
		
		return new BranchDAOImpl();
	}
	public static CustomerDAO getCustomerDAO() {
		
		return new CustomerDAOImpl();
	}
	public static AccountDAO getAccountDAO() {
		return new AccountDAOImpl();
	}
	public static LoanDAO getLoanDAO() {
		return new LoanDAOImpl();
	
	}
	public static CreditCardDAO getCreditCardDAO() {
		return new CreditCardDAOImpl();
		
	}
	
	public static BeneficiaryDAO getBeneficiaryDAO() {
		return new BeneficiaryDAOImpl();
		

}
	public static TransactionDAO getTransactionDAO() {
		return new TransactionDAOImpl();
		
	}
	public static CreditCardTransactionDAO getCreditCardTransactionDAO() {
		
		return new CreditCardTransactionDAOImpl();
	
		
	}
}
	
