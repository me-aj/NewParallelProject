package com.aj.parallelproject.dao;

import java.math.BigDecimal;

import com.aj.parallelproject.bean.Customer;
import com.aj.parallelproject.bean.Wallet;
import com.aj.parallelproject.repo.WalletRepoImpl;

public class WalletDaoImpl implements IWalletDao {

	private WalletRepoImpl repo = null;

	public WalletDaoImpl() {
		repo = new WalletRepoImpl();
	}

	@Override
	public Customer createAccount(String name, String mobileNo,
			BigDecimal amount) {
		Customer customer = new Customer();
		customer.setName(name);
		customer.setMobileNo(mobileNo);
		Wallet wallet = new Wallet();
		wallet.setBalance(amount);
		customer.setWallet(wallet);
		return repo.save(customer);
	}

	@Override
	public Customer showBalance(String mobileNo) {
		return repo.findOne(mobileNo);
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo,
			BigDecimal amount) {
		Customer sourceCustomer = repo.findOne(sourceMobileNo);
		Customer customer = sourceCustomer;
		Customer targetCustomer = repo.findOne(targetMobileNo);
		if (null != sourceCustomer
				&& null != targetCustomer
				&& sourceCustomer.getWallet().getBalance().compareTo(amount) >= 0) {
			sourceCustomer.getWallet().getBalance().subtract(amount);
			targetCustomer.getWallet().getBalance().add(amount);
			repo.save(sourceCustomer);
			repo.save(targetCustomer);
			customer = sourceCustomer;
		}
		return customer;
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		Customer customer = repo.findOne(mobileNo);
		customer.getWallet().setBalance(
				customer.getWallet().getBalance().add(amount));
		return repo.save(customer);
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		Customer customer = repo.findOne(mobileNo);
		if (customer.getWallet().getBalance().compareTo(amount) >= 0) {
			customer.getWallet().setBalance(
					customer.getWallet().getBalance().subtract(amount));
			customer = repo.save(customer);
		}
		return customer;
	}
}