package com.techelevator.tenmo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class TransferTest {
	Transfer test = new Transfer();

	@Test
	public void transfer_type_returns_groovy() {
		String transferType = "groovy";
		test.setTransferType(transferType);
		assertEquals(test.getTransferType(), transferType);
	}
	@Test
	public void transfer_status_returns_fatal() {
		String transferStatus = "fatal";
		test.setTransferStatus(transferStatus);
		assertEquals(test.getTransferStatus(), transferStatus);
	}
	@Test
	public void accountFromId_returns_23() {
		long accountFromId = 23;
		test.setAccountFromId(accountFromId);
		assertEquals(test.getAccountFromId(), accountFromId);
	}
	@Test
	public void userFrom_returns_the_irs() {
		String userFrom = "the irs";
		test.setUserFrom(userFrom);
		assertEquals(test.getUserFrom(), userFrom);
	}
	@Test
	public void accountToId_returns_900() {
		long accountToId = 900;
		test.setAccountToId(accountToId);
		assertEquals(test.getAccountToId(), accountToId);
	}
	@Test
	public void userTo_returns_taxpayer() {
		String userTo = "taxpayer";
		test.setUserTo(userTo);
		assertEquals(test.getUserTo(), userTo);
	}
	@Test
	public void amount_returns_1200() {
		BigDecimal amount = BigDecimal.valueOf(1200.00);
		test.setAmount(amount);
		assertEquals(test.getAmount(), amount);
	}

}
