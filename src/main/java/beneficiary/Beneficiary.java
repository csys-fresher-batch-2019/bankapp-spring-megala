package beneficiary;

public class Beneficiary {
	private long customerAccNo;
	private String beneficiaryName;
	private long accNo;
	private String iFSCCode;
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