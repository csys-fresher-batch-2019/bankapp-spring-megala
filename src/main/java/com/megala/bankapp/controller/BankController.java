package com.megala.bankapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megala.bankapp.dao.TransactionDAO;
import com.megala.bankapp.dao.impl.BeneficiaryDAO;
import com.megala.bankapp.domain.Beneficiary;
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
	MessageDTO msg=new MessageDTO();

	@GetMapping("/Register")
		public void Register(@RequestParam("name") String cusName,@RequestParam("street") String street,@RequestParam("city") String city,@RequestParam("accType") String accType,@RequestParam("mbleNo") long mbleNo,@RequestParam("email") String mailId,@RequestParam("password") String passWord)  {
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
				
	}
	@GetMapping("/AddBeneficiary")
	public MessageDTO addBene(@RequestParam("cusAcc") long cusAcc,@RequestParam("name")String beneName,@RequestParam("accNo")long acc,@RequestParam("ifs")String ifscNo) {
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
}