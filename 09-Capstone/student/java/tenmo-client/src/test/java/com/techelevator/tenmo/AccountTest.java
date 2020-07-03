package com.techelevator.tenmo;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.techelevator.tenmo.models.Account;

// Client
public class AccountTest {

	private Account test;
	private static final long TEST_ACCOUNT_ID = 10;
	private static final long TEST_USER_ID = 1;
	private static final BigDecimal TEST_BALANCE = BigDecimal.TEN;
	
	@Before
	public void setUp() throws Exception {
		test = new Account();
		test.setAccountId(TEST_ACCOUNT_ID);
		test.setUserId(TEST_USER_ID);
		test.setAccountBalance(TEST_BALANCE);
	}

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
