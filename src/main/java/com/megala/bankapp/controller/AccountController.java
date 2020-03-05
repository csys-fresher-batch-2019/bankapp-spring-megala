package com.megala.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megala.bankapp.dao.AccountDAO;
import com.megala.bankapp.domain.Account;
import com.megala.bankapp.dto.MessageDTO;

@RestController
@RequestMapping("api")
@CrossOrigin(origins ="*")
public class AccountController {

	@Autowired
	AccountDAO a;
	@Autowired
	AccountDAO u;

	@PutMapping("/activeAccount")
	public MessageDTO activeAccount(@RequestParam("accNo") long accNum, @RequestParam("status") String status) {
		MessageDTO msg = new MessageDTO();

		int account = a.activeAccount(accNum, status);
		if (account == 1) {
			msg.setInfoMessage("Account Updated");
		} else {
			msg.setErrorMessage("Account Failed to update!!");
		}
		return msg;

	}

	@PutMapping("/updateBalance")
	public MessageDTO updateBalance(@RequestParam("accNo") long accNum, @RequestParam("amount") int amount) {
		MessageDTO msg = new MessageDTO();
		int account = u.updateAccount(accNum, amount);
		if (account == 1) {
			msg.setInfoMessage("Amount Successfully added");
		} else {
			msg.setErrorMessage("Invalid Account Number!!");
		}
		return msg;

	}

	@PostMapping("/adminLogin")
	public MessageDTO adminLogin(@RequestParam("name") String name, @RequestParam("password") String password) {
		MessageDTO msg = new MessageDTO();
		if (name.equalsIgnoreCase("admin") && password.equals("admin")) {
			msg.setInfoMessage("Login successsful");
		} else {
			msg.setErrorMessage("Login failed");
		}
		return msg;
	}

	@GetMapping("/listAccountDetails")
	public List<Account> listAccountDetails() {
		List<Account> account = u.displayAcc();
		return account;
	}

	@GetMapping("/listAccountDetailsByAccNo")
	public List<Account> listAccountDetailsByAccNo(@RequestParam("accNo") long accNum) {
		List<Account> account = u.searchByAccountNo(accNum);
		return account;
	}

}
