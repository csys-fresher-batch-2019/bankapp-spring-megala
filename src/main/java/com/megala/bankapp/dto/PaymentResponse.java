package com.megala.bankapp.dto;

public class PaymentResponse {

	@Override
	public String toString() {
		return "PaymentResponse [status=" + status + ", transactionId=" + transactionId + "]";
	}

	private boolean status;

	private Integer transactionId;

	private long accountNo;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
}
