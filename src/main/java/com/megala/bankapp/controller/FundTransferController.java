package com.megala.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megala.bankapp.dao.TransactionDAO;
import com.megala.bankapp.domain.Transaction;
import com.megala.bankapp.dto.MessageDTO;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.exception.DbException;
import com.megala.bankapp.exception.ServiceException;
import com.megala.bankapp.service.TransactionService;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class FundTransferController {
	@Autowired
	TransactionDAO dao;
	@Autowired
	private TransactionService trans;

	@PostMapping("/fundTransfer")
	public MessageDTO fundTransfer(@RequestParam("accNo") long accNum, @RequestParam("beneAccNo") long beneAccNo,
			@RequestParam("amount") int amount) {
		MessageDTO msg = new MessageDTO();

		Transaction t = new Transaction();
		t.setAccNo(accNum);
		t.setBeneficiaryAccNo(beneAccNo);
		t.setTransactionAmount(amount);
		boolean result = false;
		PaymentResponse fund = null;
		try {
			fund = trans.fundTransaction(t);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
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

		List<Transaction> c = null;
		try {
			c = dao.findAll();
		} catch (DbException e) {
			e.printStackTrace();
		}
		return c;
	}

	@GetMapping("/fundTransferDetailsByAccNo")
	public List<Transaction> fundTransferDetailsByAccNo(@RequestParam("accNo") long accNum) {

		List<Transaction> c = null;
		try {
			c = dao.findByAccNo(accNum);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return c;
	}

}
