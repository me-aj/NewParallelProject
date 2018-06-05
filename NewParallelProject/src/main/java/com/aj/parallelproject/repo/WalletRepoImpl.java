package com.aj.parallelproject.repo;

import java.util.Map;

import com.aj.parallelproject.bean.Customer;


public class WalletRepoImpl implements IWalletRepo {
	private Map<String,Customer> data;
	
	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		this.data = data;
	}

	public boolean save(Customer customer) {
		data.put(customer.getMobileNo(), customer);
		return true;
	}

	public Customer findOne(String mobileNo) {
		return data.get(mobileNo);
	}


}