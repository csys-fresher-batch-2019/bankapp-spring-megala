package com.megala.bankapp.domain;

import java.time.LocalDateTime;

public class Transaction {
	private int transactionId;
	private long accNo;
	private long beneficiaryAccNo;
	private LocalDateTime transactionDate = LocalDateTime.now();
	private int transactionAmount;
	private String status = "debit";

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public long getBeneficiaryAccNo() {
		return beneficiaryAccNo;
	}

	public void setBeneficiaryAccNo(long beneficiaryAccNo) {
		this.beneficiaryAccNo = beneficiaryAccNo;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(int transactionAmount) {
		this.transactionAmount = transactionAmount;
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