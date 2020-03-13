package com.megala.bankapp.validator;

import com.megala.bankapp.domain.Beneficiary;
import com.megala.bankapp.exception.ValidateException;

public class BeneficiaryValidator {
	public static boolean validateBene(Beneficiary beneficiary) throws ValidateException {
		if (beneficiary.getBeneficiaryName() == null || "".equals(beneficiary.getBeneficiaryName().trim())) {
			throw new ValidateException("Beneficiary Name cannot be blank/empty!");
		} else if (Long.toString(beneficiary.getAccNo()).length() < 10 || Long.toString(beneficiary.getAccNo()).length() > 10) {
			throw new ValidateException("Invalid Beneficiary Account Number");

		} else if (beneficiary.getiFSCCode().length() < 11 || beneficiary.getiFSCCode().length() > 11) {
			throw new ValidateException("Invalid IFSC Number");
		}
		return true;

	}
}
