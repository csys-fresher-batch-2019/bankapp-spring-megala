package loan;

public class Loan {
	private int customerId;
	private String branchName;
	private String loanNo;
	private int amount;
	private LoanStatusEnum status=LoanStatusEnum.APPROVED;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public LoanStatusEnum getStatus() {
		return status;
	}
	public void setStatus(LoanStatusEnum status) {
		this.status = status;
	}

}