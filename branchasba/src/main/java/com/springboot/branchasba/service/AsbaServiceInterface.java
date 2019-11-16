package com.springboot.branchasba.service;

public interface AsbaServiceInterface {
	
	public String calculateAmount(String ipoName);
	public String callCbs(String accountnumber,String  amount,String ipoNumber);

	

}
