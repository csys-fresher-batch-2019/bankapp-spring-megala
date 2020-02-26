package creditcardtrans;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CreditCardTransaction {
	private long cardId;
	private int transactionId;
	private float amount;
	private Timestamp transactionDate=Timestamp.valueOf(LocalDateTime.now());
	private String status;
	private String merchantId;
	private Timestamp createdDate=Timestamp.valueOf(LocalDateTime.now());
	public long getCardId() {
		return cardId;
	}
	public void setCardId(long cardId) {
		this.cardId = cardId;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
	
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Timestamp getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId2) {
		
		this.merchantId = merchantId2;
		
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
}
