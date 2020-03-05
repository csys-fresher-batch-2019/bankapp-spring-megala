package com.megala.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megala.bankapp.dao.BeneficiaryDAO;
import com.megala.bankapp.domain.Beneficiary;
import com.megala.bankapp.dto.MessageDTO;

@RestController
@RequestMapping("api")
public class BeneficiaryController {
	@Autowired
	BeneficiaryDAO c;
	@Autowired
	BeneficiaryDAO b;

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
	public List<Beneficiary> listBeneDetailsByAccNo(@RequestParam("accNo") long accNum) {
		List<Beneficiary> d = b.displayParBeneficiary(accNum);
		return d;
	}

	@GetMapping("/listBeneDetailsByName")
	public List<Beneficiary> listBeneDetailsByName(@RequestParam("name") String name) {
		List<Beneficiary> d = b.searchByBeneficiaryName(name);
		return d;
	}

}
