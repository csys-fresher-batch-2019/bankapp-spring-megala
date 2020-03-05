package com.megala.bankapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megala.bankapp.dao.AccountDAO;
import com.megala.bankapp.dao.BeneficiaryDAO;
import com.megala.bankapp.dao.CreditCardTransactionDAO;
import com.megala.bankapp.dao.TransactionDAO;
import com.megala.bankapp.domain.Account;
import com.megala.bankapp.domain.Beneficiary;
import com.megala.bankapp.domain.CreditCard;
import com.megala.bankapp.domain.CreditCardTransaction;
import com.megala.bankapp.domain.Customer;
import com.megala.bankapp.domain.Register;
import com.megala.bankapp.domain.Transaction;
import com.megala.bankapp.dto.MessageDTO;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.service.CreditCardService;

@RestController
@RequestMapping("api")
public class BankController {
	@Autowired
	BeneficiaryDAO c;
	@Autowired
	TransactionDAO dao;
	@Autowired
	CreditCardTransactionDAO credit;
	@Autowired
	AccountDAO a;
	@Autowired
	AccountDAO u;
	@Autowired
	BeneficiaryDAO b;
	@Autowired
	private CreditCardService creditCardService;
	
	@PostMapping("/register")
	public MessageDTO register(@RequestParam("name") String cusName, @RequestParam("street") String street,
			@RequestParam("city") String city, @RequestParam("accType") String accType,
			@RequestParam("mbleNo") long mbleNo, @RequestParam("email") String mailId,
			@RequestParam("password") String passWord) {
		MessageDTO msg = new MessageDTO();
		Customer c = new Customer();
		c.setName(cusName);
		c.setStreet(street);
		c.setCity(city);
		c.setAccType(accType);
		c.setMobileNo(mbleNo);
		c.setEmail(mailId);
		c.setPassword(passWord);
		boolean result = false;
		Register reg = creditCardService.register(c);

		result = reg.isStatus();
		if (result) {
			msg.setInfoMessage("Registered successfully");
		} else {
			msg.setErrorMessage("Failed to register");
		}
		return msg;
	}
	
	@PostMapping("/addBene")
	public MessageDTO addBene(@RequestParam("cusAcc") long cusAcc, @RequestParam("name") String beneName,
			@RequestParam("accNo") long acc, @RequestParam("ifs") String ifscNo) {
		MessageDTO msg = new MessageDTO();
		Beneficiary b = new Beneficiary();
		b.setCustomerAccNo(cusAcc);
		b.setBeneficiaryName(beneName);
		b.setAccNo(acc);
		b.setiFSCCode(ifscNo);

		int a = c.addBeneficiary(b);
		if (a == 1) {
			msg.setInfoMessage("Beneficiary added");
		} else {
			msg.setErrorMessage("Beneficiary already exists");
		}
		return msg;

	}
	@GetMapping("/listBeneDetailsByAccNo")
	public List<Beneficiary> listBeneDetailsByAccNo(@RequestParam("accNo") long accNum){
		List<Beneficiary> d=b.displayParBeneficiary(accNum);
		return d;
	}
	@GetMapping("/listBeneDetailsByName")
	public List<Beneficiary> listBeneDetailsByName(@RequestParam("name") String name){
		List<Beneficiary> d=b.searchByBeneficiaryName(name);
		return d;
	}
	

	@PostMapping("/fundTransfer")
	public MessageDTO fundTransfer(@RequestParam("accNo") long accNum, @RequestParam("beneAccNo") long beneAccNo,
			@RequestParam("amount") int amount) {
		MessageDTO msg = new MessageDTO();

		Transaction t = new Transaction();
		t.setAccNo(accNum);
		t.setBeneficiaryAccNo(beneAccNo);
		t.setTransactionAmount(amount);
		boolean result = false;
		PaymentResponse fund = creditCardService.fundTransaction(t);
		System.out.println(fund.getTransactionId());
		System.out.println(fund.isStatus());
		result = fund.isStatus();
		if (result) {
			msg.setInfoMessage("Transaction successful");

		} else {
			msg.setErrorMessage("Transaction failed");

		}
		return msg;

	}
	@GetMapping("/fundTransferDetails")
	public List<Transaction> fundTransferDetails() {

		List<Transaction> c = dao.displayTransaction();
		return c;
	}

	@GetMapping("/fundTransferDetailsByAccNo")
	public List<Transaction> fundTransferDetailsByAccNo(@RequestParam("accNo") long accNum) {

		List<Transaction> c = dao.displayParTransaction(accNum);
		return c;
	}

	@PostMapping("/creditCard")
	public MessageDTO creditCard(@RequestParam("cardNo") long card, @RequestParam("expiry") String expirydatestr,
			@RequestParam("cvv") int cvvNo, @RequestParam("pin") int pinNo, @RequestParam("price") float price,
			@RequestParam("merchantId") String merchantId, @RequestParam("comment") String comments) {
		MessageDTO msg = new MessageDTO();
		CreditCard creditCard = new CreditCard();
		creditCard.setCardNo(card);
		creditCard.setExpiryDate(LocalDate.parse(expirydatestr));
		creditCard.setCvvNo(cvvNo);
		creditCard.setPin(pinNo);

		try {
			boolean status1 = false;
			PaymentResponse obj = creditCardService.pay(creditCard, price, merchantId, comments);
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

	@GetMapping("/creditCardDetails")
	public List<CreditCardTransaction> creditCardDetails() {
		List<CreditCardTransaction> d = credit.displayCreditCardPaymentList();
		return d;
	}
	@GetMapping("/creditCardDetailsByCardId")
	public List<CreditCardTransaction> creditCardDetailsByCardId(@RequestParam("cardId") int cardId) {
		List<CreditCardTransaction> d = credit.displayTransactionHistoryByCardId(cardId);
		return d;
	}
	@PostMapping("/activeAccount")
	public MessageDTO activeAccount(@RequestParam("accNo") long accNum,@RequestParam("status") String status) {
		MessageDTO msg = new MessageDTO();

		int account = a.activeAccount(accNum, status);
		if (account == 1) {
			msg.setInfoMessage("Account Updated");
		} else {
			msg.setErrorMessage("Account Failed to update!!");
		}
		return msg;
		
	}
	@PostMapping("/updateBalance")
	public MessageDTO updateBalance(@RequestParam("accNo") long accNum,@RequestParam("amount") int amount) {
		MessageDTO msg = new MessageDTO();
		int account = u.updateAccount(accNum, amount);
		if (account == 1) {
			msg.setInfoMessage("Amount Successfully added");
		} else {
			msg.setErrorMessage("Invalid Account Number!!");
		}
		return msg;
		
	}
	@PostMapping("/login")
	public MessageDTO login(@RequestParam("name") String name,@RequestParam("password") String password) {
		MessageDTO msg = new MessageDTO();
		if (name.equalsIgnoreCase("admin") && password.equals("admin")) {
			msg.setInfoMessage("Login successsful");
		} else {
			msg.setErrorMessage("Login failed");
		}
		return msg;
	}
	@GetMapping("/listAccountDetails")
	public List<Account> listAccountDetails(){
		List<Account> account = u.displayAcc();
		return account;
	}
	@GetMapping("/listAccountDetailsByAccNo")
	public List<Account> listAccountDetailsByAccNo(@RequestParam("accNo") long accNum){
		List<Account> account = u.searchByAccountNo(accNum);
		return account;
	}
	@PostMapping("/creditCardLogin")
	public MessageDTO creditCardLogin(@RequestParam("cardNo") long cardNo,@RequestParam("pin") int pin) {
		boolean result=false;
		MessageDTO msg = new MessageDTO();
		CreditCard creditCard = new CreditCard();
		creditCard.setCardNo(cardNo);
		creditCard.setPin(pin);
		result=creditCardService.checkLogin1(creditCard);		
		
		if(result) {
			msg.setInfoMessage("login successful");
			
		}
		else {
			msg.setErrorMessage("Invalid card Number or pin!!");
		}
		return msg;
	}
	@PostMapping("/userLogin")
	public MessageDTO userLogin(@RequestParam("email") String email,@RequestParam("pass") String password) {
			MessageDTO msg = new MessageDTO();
			boolean status = false;
			PaymentResponse result = creditCardService.login(email, password);
			status = result.isStatus();
			if (status) {
				msg.setInfoMessage("Login success");
		}
			else {
				msg.setErrorMessage("Invalid mail Id or password");
			}
			return msg;
		}
	}
