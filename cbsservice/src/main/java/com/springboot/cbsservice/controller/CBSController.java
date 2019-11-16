/**
 * 
 */

package com.springboot.cbsservice.controller;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aman kumar
 *
 */
@RestController
@RequestMapping("finacle")
public class CBSController {

	
	@Autowired
	private Environment environment;
	
	
	@PostMapping("lien")
	public  String fundBlock(@RequestBody String request ){
		int port= Integer.parseInt(environment.getProperty("local.server.port"));
		 System.out.println("Request came ::::" + request+"the PORT::"+port);
		
		
		System.out.println("inside the fund block");
		int cbsref = RandomUtils.nextInt(5);
		
		return request+cbsref+"SERVER"+port;
		
		
	}
	
}
