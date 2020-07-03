package com.techelevator.tenmo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
// Server
class AccountTest {

	Account test = new Account();
	
	@Test
	public void accountid_returns_26() {
		long accountId = 26;
		test.setAccountId(accountId);
		assertEquals(test.getAccountId(), accountId);
	}
	
	@Test
	public void userid_returns_36() {
		long userId = 36;
		test.setUserId(userId);
		assertEquals(test.getUserId(), userId);
	}
	
	@Test
	public void accountBalance_returns_999() {
		BigDecimal accountBalance = BigDecimal.valueOf(999.00);
		test.setAccountBalance(accountBalance);
		assertEquals(test.getAccountBalance(), accountBalance);
	}
	

}
