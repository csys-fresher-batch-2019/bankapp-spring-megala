package com.megala.bankapp.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megala.bankapp.domain.Customer;
import com.megala.bankapp.domain.Register;
import com.megala.bankapp.dto.MessageDTO;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.service.CreditCardService;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class CustomerController {
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
		Register reg=null;
		try {
			reg = creditCardService.register(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		result = reg.isStatus();
		if (result) {
			System.out.println("Registration success");
			;
			msg.setInfoMessage("Registered successfully");
		} else {
			System.out.println("Registration failed");
			;
			msg.setErrorMessage("Failed to register");
		}
		return msg;
	}

	@PostMapping("/userLogin")
	public MessageDTO userLogin(@RequestParam("email") String email, @RequestParam("pass") String password) {
		MessageDTO msg = new MessageDTO();
		boolean status = false;
		PaymentResponse result=null;
		try {
			result = creditCardService.login(email, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		status = result.isStatus();
		if (status) {
			System.out.println("Login success");
			msg.setInfoMessage("Login success");
		} else {
			System.out.println("Invalid mail Id or password");
			msg.setErrorMessage("Invalid mail Id or password");
		}
		return msg;
	}

}
