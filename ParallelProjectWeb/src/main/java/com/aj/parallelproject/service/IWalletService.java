package com.aj.parallelproject.service;

import java.math.BigDecimal;

import com.aj.parallelproject.bean.Customer;

public interface IWalletService {

	public Customer createAccount(String name, String mobileNo, BigDecimal amount);
	
	public Customer showBalance(String mobileNo);
	
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount);
	
	public Customer depositAmount(String mobileNo, BigDecimal amount);
	
	public Customer withdrawAmount(String mobileNo, BigDecimal amount);
	
}
