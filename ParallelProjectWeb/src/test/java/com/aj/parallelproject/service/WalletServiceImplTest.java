package com.aj.parallelproject.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class WalletServiceImplTest {

	IWalletService service=null;
	
	@Before
	public void setUp() throws Exception {
		service=new WalletServiceImpl();
	}
	@Test
	public final void testCheckNameForCreateAccount() {
		BigDecimal bigDecimal = new BigDecimal(987.98);
		Assert.assertNull((service.createAccount("980776", "9876543210",
				bigDecimal)));
	}

	@Test
	public final void testCheckPhoneForCreateAccount() {
		BigDecimal bigDecimal = new BigDecimal(987.98);
		Assert.assertNull(service
				.createAccount("abc", "9876543", bigDecimal));
	}

	@Test
	public final void testCheckAmountForCreateAccount() {
		BigDecimal bigDecimal = new BigDecimal(-987.88);
		Assert.assertNull(service.createAccount("980776", "9876543210",
				bigDecimal));
	}

	@Test
	public final void testAllValidForCreateAccount() {
		BigDecimal bigDecimal = new BigDecimal(987.98);
		assertNotNull(service.createAccount("abc", "9876543210",
				bigDecimal));
	}

	@Test
	public final void testPhoneShowBalance() {
		Assert.assertNull(service.showBalance("566789"));
	}

	@Test
	public final void testAllValidShowBalance() {
		assertEquals("abc", service.showBalance("566789").getName());
	}

	@Test
	public final void testSourcePhoneFundTransfer() {
		BigDecimal amount = new BigDecimal(987.98);
		Assert.assertNull(service.fundTransfer("9876", "1234567890", amount));
	}

	@Test
	public final void testTargetPhoneFundTransfer() {
		BigDecimal amount = new BigDecimal(987.98);
		Assert.assertNull(service.fundTransfer("1234567890", "9876", amount));
	}

	@Test
	public final void testAmountFundTransfer() {
		BigDecimal bigDecimal = new BigDecimal(-765);
		Assert.assertNull(service.createAccount("1234567890", "9876543210",
				bigDecimal));
	}

	@Test
	public final void testAllValidFundTransfer() {
		BigDecimal bigDecimal = new BigDecimal(9876.44);
		assertNotNull(service.createAccount("1234567890", "9876543210",
				bigDecimal));
	}

	@Test
	public final void testPhoneDepositAmount() {
		BigDecimal bigDecimal = new BigDecimal(9876.44);
		Assert.assertNull(service.depositAmount("9877", bigDecimal));
	}

	@Test
	public final void testAmountDepositAmount() {
		BigDecimal bigDecimal = new BigDecimal(-1234);
		Assert.assertNull(service.depositAmount("9876543211", bigDecimal));
	}

	@Test
	public final void testAllValidDepositAmount() {
		BigDecimal bigDecimal = new BigDecimal(12345.00);
		assertNotNull(service.depositAmount("9876543211", bigDecimal));
	}

	@Test
	public final void testPhoneWithdrawAmount() {
		BigDecimal amount = new BigDecimal(12345.00);
		Assert.assertNull(service.withdrawAmount("98767", amount));
	}

	@Test
	public final void testAmountWithdrawAmount() {
		BigDecimal amount = new BigDecimal(-987.09);
		Assert.assertNull(service.withdrawAmount("9876543212", amount));
	}

	@Test
	public final void testWithdrawAmountIsSufficient() {
		BigDecimal amount = new BigDecimal(12345.00);
		if (amount.doubleValue() < service.showBalance("9876543210")
				.getWallet().getBalance().doubleValue()) {
			fail("Amount insufficient");
		}
	}
}
