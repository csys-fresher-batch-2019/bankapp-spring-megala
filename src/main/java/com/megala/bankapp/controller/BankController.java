package com.megala.bankapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megala.bankapp.dao.BeneficiaryDAO;
import com.megala.bankapp.dao.TransactionDAO;
import com.megala.bankapp.domain.Beneficiary;
import com.megala.bankapp.domain.CreditCard;
import com.megala.bankapp.domain.Customer;
import com.megala.bankapp.domain.Register;
import com.megala.bankapp.domain.Transaction;
import com.megala.bankapp.dto.MessageDTO;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.factory.DAOFactory;
import com.megala.bankapp.service.CreditCardService;

@RestController
@RequestMapping("api")
public class BankController {

	@GetMapping("/Register")
		public MessageDTO Register(@RequestParam("name") String cusName,@RequestParam("street") String street,@RequestParam("city") String city,@RequestParam("accType") String accType,@RequestParam("mbleNo") long mbleNo,@RequestParam("email") String mailId,@RequestParam("password") String passWord)  {
			MessageDTO msg=new MessageDTO();
			Customer c=new Customer();
			c.setName(cusName);
			c.setStreet(street);
			c.setCity(city);
			c.setAccType(accType);
			c.setMobileNo(mbleNo);
			c.setEmail(mailId);
			c.setPassword(passWord);
			boolean result=false;
			Register reg=CreditCardService.register(c);
		
			result=reg.isStatus();
			if(result) {
			msg.setInfoMessage("Registered successfully");
			}
				else {
					msg.setErrorMessage("Failed to register");
				}
			return msg;	
	}
	@GetMapping("/AddBeneficiary")
	public MessageDTO addBene(@RequestParam("cusAcc") long cusAcc,@RequestParam("name")String beneName,@RequestParam("accNo")long acc,@RequestParam("ifs")String ifscNo) {
		MessageDTO msg=new MessageDTO();
		Beneficiary b=new Beneficiary();
		b.setCustomerAccNo(cusAcc);
		b.setBeneficiaryName(beneName);
		b.setAccNo(acc);
		b.setiFSCCode(ifscNo);
		BeneficiaryDAO c=DAOFactory.getBeneficiaryDAO();
		int a=c.addBeneficiary(b);
		if(a==1) {
			msg.setInfoMessage("Beneficiary added");
			}
		else {
			msg.setErrorMessage("Beneficiary already exists");
		}
		return msg;
		
		
}

	 @GetMapping("/FundTransfer")
	    public MessageDTO fundTransfer(@RequestParam("accNo") long accNum,@RequestParam("beneAccNo") long beneAccNo,@RequestParam("amount") int amount)  {
		MessageDTO msg=new MessageDTO();

	    Transaction t=new Transaction();
		t.setAccNo(accNum);
		t.setBeneficiaryAccNo(beneAccNo);
		t.setTransactionAmount(amount);
		boolean result=false;
		PaymentResponse fund=CreditCardService.fundTransaction(t);
		System.out.println(fund.getTransactionId());
		System.out.println(fund.isStatus());
		result=fund.isStatus();
		if(result) {
			msg.setInfoMessage("Transaction successful");

		}
		else {
			msg.setErrorMessage("Transaction failed");

		}
		return msg;
	    
	 
}
	 @GetMapping("/FundTransferDetails")
	    public List<Transaction> fundTransfer(@RequestParam("accNo") long accNum) {
		 TransactionDAO dao=DAOFactory.getTransactionDAO();
		 List<Transaction> c=dao.displayParTransaction(accNum);
		return c;
}
	 
	 @GetMapping("/CreditCard")
	 public MessageDTO creditCard(@RequestParam("cardNo") long card,@RequestParam("expiry")LocalDate expirydate,@RequestParam("cvv")int cvvNo,@RequestParam("pin")int pinNo,@RequestParam("price")float price,@RequestParam("id")String merchantId,@RequestParam("comment")String comments) {
		MessageDTO msg=new MessageDTO();
		CreditCard creditCard=new CreditCard();
		creditCard.setCardNo(card);
		creditCard.setExpiryDate(expirydate);
		creditCard.setCvvNo(cvvNo);
		creditCard.setPin(pinNo);
		
		
		try {
			boolean status1 = false;
			PaymentResponse obj = CreditCardService.pay(creditCard, price, merchantId, comments);
			System.out.println(obj);
			status1 = obj.isStatus();
			System.out.println(status1);
			if (status1) {
				msg.setInfoMessage("payment successful");
			} else {
				msg.setErrorMessage("payment failed");
			}
		
		} catch (Exception e1) {
			e1.printStackTrace();
			msg.setErrorMessage("Invalid credentials");
		}
		return msg;
		

	}

}
