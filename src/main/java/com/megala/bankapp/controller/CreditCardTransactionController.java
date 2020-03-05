package com.megala.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megala.bankapp.dao.CreditCardTransactionDAO;
import com.megala.bankapp.domain.CreditCardTransaction;

@RestController
@RequestMapping("api")
@CrossOrigin(origins ="*")
public class CreditCardTransactionController {
	@Autowired
	CreditCardTransactionDAO credit;

	@GetMapping("/creditCardTransDetails")
	public List<CreditCardTransaction> creditCardTransDetails() {
		List<CreditCardTransaction> d = credit.displayCreditCardPaymentList();
		return d;
	}

	@GetMapping("/creditCardTransDetailsByCardId")
	public List<CreditCardTransaction> creditCardTransDetailsByCardId(@RequestParam("cardId") int cardId) {
		List<CreditCardTransaction> d = credit.displayTransactionHistoryByCardId(cardId);
		return d;
	}

}
