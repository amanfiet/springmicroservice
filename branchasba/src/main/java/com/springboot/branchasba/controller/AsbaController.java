package com.springboot.branchasba.controller;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.branchasba.service.AsbaServiceInterface;

@RestController
@RequestMapping("/asba")
public class AsbaController {

	@Autowired
	AsbaServiceInterface asbainterface;

	@GetMapping("/apply")
	public String applyIpo(@RequestParam("ipo") String ipoName, @RequestParam("accnum") String accountNumber) {

		String ammount = asbainterface.calculateAmount(ipoName);

		int uti = RandomUtils.nextInt(14);
		String iporef = ipoName + uti;
	
		String  result=asbainterface.callCbs(accountNumber, ammount, iporef);
		

		return "Your  IPO hasbeen  applied succesfully"+result;

	}
}
