package com.megala.bankapp.validator;

import com.megala.bankapp.domain.Customer;
import com.megala.bankapp.exception.ValidateException;

public class RegisterValidator {
	public static boolean registerValidate(Customer cus) throws ValidateException {
		if(cus.getName()==null||"".equals(cus.getName().trim())) {
			throw new ValidateException("Name Cannot be blank/empty!!");
		}
		if(cus.getStreet()==null||"".equals(cus.getStreet().trim())) {
			throw new ValidateException("Street Cannot be blank/empty!!");
		}
		if(cus.getCity()==null || "".equals(cus.getCity().trim())) {
			throw new ValidateException("City cannot be blank/empty!!");
		}
		return true;
		
	}

}
