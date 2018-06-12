package com.aj.parallelproject.repo;

import java.util.HashMap;
import java.util.Map;

import com.aj.parallelproject.bean.Customer;


public class WalletRepoImpl {
	private static Map<String,Customer> data=new HashMap<String, Customer>();
	
	public WalletRepoImpl() {
		super();
	}

	public Customer save(Customer customer) {
		data.put(customer.getMobileNo(), customer);
		return customer;
	}

	public Customer findOne(String mobileNo) {
		return data.get(mobileNo);
	}

}