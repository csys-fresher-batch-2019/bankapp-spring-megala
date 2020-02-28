package account;

import java.io.Serializable;

public class Account implements Serializable{
	private int customerId;
	private long accNo;
	private String accType;
	private int availableBalance;
	transient private String status;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		if(customerId<0){
			throw new IllegalArgumentException("Invalid number");
		}
		this.customerId = customerId;
	}
	
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public int getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(int availableBalance) {
		this.availableBalance = availableBalance;
	}
	public long getAccNo() {
		return accNo;
	}
	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
