package com.megala.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megala.bankapp.dao.AccountDAO;
import com.megala.bankapp.domain.Account;
import com.megala.bankapp.dto.MessageDTO;
import com.megala.bankapp.exception.DbException;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class AccountController {

	@Autowired
	AccountDAO a;
	@Autowired
	AccountDAO u;

	@PostMapping("/activeAccount")
	public MessageDTO activeAccount(@RequestParam("accNo") long accNum, @RequestParam("status") String status)
			 {
		MessageDTO msg = new MessageDTO();

		int account=0;
		try {
			account = a.activeAccount(accNum, status);
		} catch (DbException e) {
			e.printStackTrace();
		}
		if (account == 1) {
			msg.setInfoMessage("Account Updated");
		} else {
			msg.setErrorMessage("Account Failed to update!!");
		}
		return msg;

	}

	@PostMapping("/updateBalance")
	public MessageDTO updateBalance(@RequestParam("accNo") long accNum, @RequestParam("amount") int amount)
			 {
		MessageDTO msg = new MessageDTO();
		int account=0;
		try {
			account = u.update(accNum, amount);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			System.out.println("Login success");
			msg.setInfoMessage("Login successsful");
		} else {
			System.out.println("Login failed");
			msg.setErrorMessage("Login failed");
		}
		return msg;
	}

	@GetMapping("/getAccount")
	public Account userLogin(@RequestParam("email") String email) {

		Account account = null;
		try {
			account = u.getAccount(email);
		} catch (DbException e) {
			e.printStackTrace();
		}

		return account;
	}

	@GetMapping("/listAccountDetails")
	public List<Account> listAccountDetails()  {
		List<Account> account=null;
		try {
			account = u.findAll();
		} catch (DbException e) {
			e.printStackTrace();
		}
		return account;
	}

	@GetMapping("/listAccountDetailsByAccNo")
	public List<Account> listAccountDetailsByAccNo(@RequestParam("accNo") long accNum) {
		List<Account> account=null;
		try {
			account = u.findByAccNo(accNum);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return account;
	}

}
