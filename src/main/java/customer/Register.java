package customer;

public class Register {
	private boolean status;
	private long accNo;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public long getAccNo() {
		return accNo;
	}
	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}
	@Override
	public String toString() {
		return "Register [status=" + status + ", accNo=" + accNo + "]";
	}
	
}
