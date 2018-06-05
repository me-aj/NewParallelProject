package com.aj.parallelproject.repo;

import com.aj.parallelproject.bean.Customer;


public interface IWalletRepo {

	public boolean save(Customer customer);
	
	public Customer findOne(String mobileNo);
}
