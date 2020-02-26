package creditcard;


import java.time.LocalDate;

public class CreditCard {
	@Override
	public String toString() {
		return "CreditCard [creditcardId=" + creditcardId + ", cardNo=" + cardNo + ", pin=" + pin + ", accNo=" + accNo
				+ ", limitNo=" + limitNo + ", expiryDate=" + expiryDate + ", cvvNo=" + cvvNo + ", comments=" + comments
				+ ", availableBalance=" + availableBalance + ", blocked=" + blocked + "]";
	}
	private int creditcardId;
	private long cardNo;
	private int pin;
	private long accNo;
	private int limitNo;
	private LocalDate expiryDate;
	private int cvvNo;
	private String comments;
	private float availableBalance;
	public float getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(float balance) {
		this.availableBalance = balance;
	}
	private boolean blocked=true;
	public int getCreditcardId() {
		return creditcardId;
	}
	public void setCreditcardId(int creditcardId) {
		this.creditcardId = creditcardId;
	}
	public int getCvvNo() {
		return cvvNo;
	}
	public void setCvvNo(int cvvNo) {
		this.cvvNo = cvvNo;
	}
	public long getAccNo() {
		return accNo;
	}
	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}
	public int getLimitNo() {
		return limitNo;
	}
	public void setLimitNo(int limitNo) {
		this.limitNo = limitNo;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public long getCardNo() {
		return cardNo;
	}
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	

}
