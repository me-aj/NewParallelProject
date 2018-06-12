package com.aj.parallelproject.service;

import java.math.BigDecimal;

import com.aj.parallelproject.bean.Customer;
import com.aj.parallelproject.dao.IWalletDao;
import com.aj.parallelproject.dao.WalletDaoImpl;

public class WalletServiceImpl implements IWalletService {

	private IWalletDao dao;

	public WalletServiceImpl() {
		dao = new WalletDaoImpl();
	}

	public Customer createAccount(String name, String mobileNo,
			BigDecimal amount) {
		Customer customer = null;
		if (null != name && null != mobileNo
				&& BigDecimal.ZERO.compareTo(amount) >= 0
				&& "^[a-zA-Z0-9]+$".matches(name)
				&& "\\d{10}".matches(mobileNo)) {
			customer = dao.createAccount(name, mobileNo, amount);
		}
		return customer;
	}

	public Customer showBalance(String mobileNo) {
		Customer customer = null;
		if ("\\d{10}".matches(mobileNo)) {
			customer = dao.showBalance(mobileNo);
		}
		return customer;
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo,
			BigDecimal amount) {
		Customer customer = null;
		if (null != sourceMobileNo && null != targetMobileNo
				&& "\\d{10}".matches(sourceMobileNo)
				&& "\\d{10}".matches(targetMobileNo)
				&& BigDecimal.ZERO.compareTo(amount)>0) {
			customer=dao.fundTransfer(sourceMobileNo, targetMobileNo, amount);
		}
		return customer;
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		Customer customer = null;
		if (null != mobileNo && "\\d{10}".matches(mobileNo)
				&& BigDecimal.ZERO.compareTo(amount) >= 0) {
			customer = dao.depositAmount(mobileNo, amount);
		}
		return customer;
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		Customer customer = null;
		if (null != mobileNo && "\\d{10}".matches(mobileNo)
				&& BigDecimal.ZERO.compareTo(amount) >= 0) {
			if (dao.showBalance(mobileNo).getWallet().getBalance()
					.compareTo(amount) >= 0) {
				customer = dao.withdrawAmount(mobileNo, amount);
			}
		}
		return customer;
	}
}