package com.techelevator.tenmo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.techelevator.tenmo.models.Transfer;



public class TransferTest {

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
	public void accountFrom_returns_the_irs() {
		String accountFrom = "the irs";
		test.setAccountFromName(accountFrom);
		assertEquals(test.getAccountFromName(), accountFrom);
	}
	@Test
	public void accountToId_returns_900() {
		long accountToId = 900;
		test.setAccountToId(accountToId);
		assertEquals(test.getAccountToId(), accountToId);
	}
	@Test
	public void accountTo_returns_taxpayer() {
		String accountTo = "taxpayer";
		test.setAccountToName(accountTo);
		assertEquals(test.getAccountToName(), accountTo);
	}
	@Test
	public void amount_returns_1200() {
		BigDecimal amount = BigDecimal.valueOf(1200.00);
		test.setAmount(amount);
		assertEquals(test.getAmount(), amount);
	}


}
