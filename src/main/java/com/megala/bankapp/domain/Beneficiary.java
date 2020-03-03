package com.megala.bankapp.domain;

public class Beneficiary {
	private long customerAccNo;
	private String beneficiaryName;
	private long accNo;
	private String iFSCCode;
	private int amount;
	private String comments;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	public String getiFSCCode() {
		return iFSCCode;
	}

	public void setiFSCCode(String iFSCCode) {
		this.iFSCCode = iFSCCode;
	}

	public long getCustomerAccNo() {
		return customerAccNo;
	}

	public void setCustomerAccNo(long customerAccNo) {
		this.customerAccNo = customerAccNo;
	}

}