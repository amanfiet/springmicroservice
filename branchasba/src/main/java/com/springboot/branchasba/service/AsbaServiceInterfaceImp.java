package com.springboot.branchasba.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AsbaServiceInterfaceImp implements AsbaServiceInterface {
	Logger log = LoggerFactory.getLogger(AsbaServiceInterfaceImp.class);

	@Autowired
	RestTemplate restTemplate;
	
	Map asbaMap = new HashMap<>();

	@Override
	public String calculateAmount(String ipoName) {

		System.out.println("incoming ipo name" + ipoName);
		asbaMap.put("IBL", "1234.00");
		asbaMap.put("RBL", "23440.00");

		return (String) asbaMap.get(ipoName);
	}

	@Override
	public String callCbs(String accountnumber, String amount, String ipoNumber) {

		String response = null;
		try {
			log.info("call to core banking system with {} ", ipoNumber);

			String reqStr=accountnumber+amount+ipoNumber;
			
			final  String url="http://cbsserver/finacle/lien";
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(reqStr, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			response = responseEntity.getBody();

			log.info("getting response from core banking system {} ", response);
		}

		catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));

		}
		return response;

	}

}
//