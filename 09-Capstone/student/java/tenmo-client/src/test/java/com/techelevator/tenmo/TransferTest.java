package com.techelevator.tenmo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.tenmo.models.Transfer;



public class TransferTest {

	private Transfer test;
	
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
	public void set_and_get_transfer_id() {
		long testId = (long)(Math.random()*100);
		test.setTransferId(testId);
		assertEquals(testId, test.getTransferId());
	}

	@Test
	public void constructor_without_transfer_id_works() {
		String type = "Send";
		String status = "Approved";
		long fromId = (long)(Math.random()*100);
		long toId = (long)(Math.random()*100);
		BigDecimal amount = BigDecimal.valueOf(Math.random() * 1000);
		prepopulateTestTransfer(type, status, fromId, toId, amount);
		Transfer actual = new Transfer(type, status, fromId, toId, amount);
		
		assertEquals(test.getTransferType(), actual.getTransferType());
		assertEquals(test.getTransferStatus(), actual.getTransferStatus());
		assertEquals(test.getAccountFromId(), actual.getAccountFromId());
		assertEquals(test.getAccountToId(), actual.getAccountToId());
		assertEquals(test.getAmount(), actual.getAmount());
	}
	
	private void prepopulateTestTransfer(String type, String status, long accountFrom, long accountTo, BigDecimal amount) {
		test.setTransferType(type);
		test.setTransferStatus(status);
		test.setAccountFromId(accountFrom);
		test.setAccountToId(accountTo);
		test.setAmount(amount);
	}

}
