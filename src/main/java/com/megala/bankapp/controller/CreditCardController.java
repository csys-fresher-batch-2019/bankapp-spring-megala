package com.megala.bankapp.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megala.bankapp.domain.CreditCard;
import com.megala.bankapp.dto.MessageDTO;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.service.CreditCardService;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class CreditCardController {
	@Autowired
	private CreditCardService creditCardService;

	@PostMapping("/creditCardPay")
	public MessageDTO creditCardPay(@RequestParam("cardNo") long card, @RequestParam("expiry") String expirydatestr,
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

	@PostMapping("/creditCardLogin")
	public MessageDTO creditCardLogin(@RequestParam("cardNo") long cardNo, @RequestParam("pin") int pin) {
		boolean result = false;
		MessageDTO msg = new MessageDTO();
		CreditCard creditCard = new CreditCard();
		creditCard.setCardNo(cardNo);
		creditCard.setPin(pin);
		result = creditCardService.checkLogin1(creditCard);

		if (result) {
			msg.setInfoMessage("login successful");

		} else {
			msg.setErrorMessage("Invalid card Number or pin!!");
		}
		return msg;
	}
}
