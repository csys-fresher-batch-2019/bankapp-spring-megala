package creditcard;

import java.time.LocalDate;

public class CreditCardValidator {
	private CreditCardValidator() {
		throw new IllegalStateException("Utility class");
	}
	
	public static boolean validateCreditCard(long creditCardNo,LocalDate expiryDate,int cvvNo) throws ValidateException {
		if(Long.toString(creditCardNo).length()<16 || Long.toString(creditCardNo).length()>16) {
			throw new ValidateException("Invalid credit card no");
		}
		else if(expiryDate.compareTo(LocalDate.now())<0) {
			throw new ValidateException("Your creditCard is expired");
			
		}
		else if(Integer.toString(cvvNo).length()<3 || Integer.toString(cvvNo).length()>3) {
			throw new ValidateException("Invalid cvv Number");
		}
		return true;
		
		
	}
	public static boolean validateCreditCard(long creditCardNo,int creditCardPin) throws ValidateException {
		if(Long.toString(creditCardNo).length()<16 || Long.toString(creditCardNo).length()>16) {
			throw new ValidateException("Invalid credit card no");
		}
		else if(Integer.toString(creditCardPin).length()<4 || Integer.toString(creditCardPin).length()>4) {
			throw new ValidateException("Invalid pin Number");
		}
		return true;
		
	} 

}
