package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TransferTest {
	private Transfer test;
	private static final String TYPE = "Request";
	private static final String STATUS = "Pending";
	private static final long FROM_ID = (long)(Math.random() * 1000);
	private static final String FROM_NAME = "Iron Bank";
	private static final long TO_ID = (long)(Math.random() * 1000);
	private static final String TO_NAME = "Lannisters, LLC";
	private static final BigDecimal AMOUNT_REQUESTED = BigDecimal.valueOf(99999999);
	private static final long TRANSFER_ID = (long)(Math.random() * 1000);

	@Before
	public void setup() {
		test = new Transfer();
	}
	
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
	
	@Test
	public void getters_for_transfer_id_and_party_names_return_correctly() {
		Transfer season8Sucks = new Transfer(TYPE, STATUS, FROM_ID, FROM_NAME, TO_ID,
				TO_NAME, AMOUNT_REQUESTED, TRANSFER_ID);
		assertEquals(TRANSFER_ID, season8Sucks.getTransferId());
		assertEquals(FROM_NAME, season8Sucks.getAccountFromName());
		assertEquals(TO_NAME, season8Sucks.getAccountToName());
	}

}
