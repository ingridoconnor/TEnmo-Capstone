package com.techelevator.tenmo.daotests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.AccountSqlDAO;
import com.techelevator.tenmo.dao.TransferSqlDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.dao.UserSqlDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

public class TransferSqlDAOTest extends DAOIntegrationTest {
	
	private TransferSqlDAO transferDao;
	private UserDAO userDao;
	private JdbcTemplate jdbcTemplate;
	private Account accountOne;
	private Account accountTwo;
	private Transfer transferOne;
	private Transfer transferTwo;
	private User badGuy;
	private User badGuy2;
	private static final String TYPE_SEND = "Send";
	private static final String TYPE_REQUEST = "Request";
	private static final String DEFAULT_USER_NAME = "badGuy";
	private static final String DEFAULT_USER_NAME_2 = "badGuy2";
	private static final String DEFAULT_PASSWORD = "frowny";
	private static final String DEFAULT_PASSWORD_2 = "frowny";
	private static final BigDecimal TEST_BALANCE = new BigDecimal("100.00");
	
	@BeforeClass
	public static void setupBeforeClass() throws Exception {
		setupDataSource();
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		closeDataSource();
	}
	@Before
	public void setup() throws Exception {
		// Initialize JDBC and DAO stuff here so that everything starts fresh with every test, and each test is 
		// independent of the others.
		jdbcTemplate = new JdbcTemplate(getDataSource());
		userDao = new UserSqlDAO(jdbcTemplate);
		transferDao = new TransferSqlDAO(jdbcTemplate, userDao);
		
		badGuy = new User();
		badGuy2 = new User();
		badGuy.setUsername(DEFAULT_USER_NAME);
		badGuy2.setUsername(DEFAULT_USER_NAME_2);
		badGuy.setPassword(DEFAULT_PASSWORD);
		badGuy2.setPassword(DEFAULT_PASSWORD_2);
		addDummyToUserTable(badGuy);
		addDummyToUserTable(badGuy2);
		
		// Now that we have fake users with working user_id's, we can make working fake test accounts
		accountOne = new Account();
		accountTwo = new Account();
		accountOne.setAccountBalance(TEST_BALANCE);
		accountTwo.setAccountBalance(TEST_BALANCE);
		accountOne.setUserId(badGuy.getId());
		accountTwo.setUserId(badGuy2.getId());
		addFakeAccountsToAccountTable(accountOne);
		addFakeAccountsToAccountTable(accountTwo);
		
		transferOne = new Transfer(TYPE_SEND, "Approved", accountOne.getAccountId(), badGuy.getUsername(),
				accountTwo.getAccountId(), badGuy2.getUsername(), BigDecimal.TEN);
		transferTwo = new Transfer(TYPE_SEND, "Approved", 0, "Troll", accountOne.getAccountId(), badGuy.getUsername(),
				BigDecimal.ONE);
		
		// Finally we can start making a fake transfer that works
		// This was our goal all along, but we couldn't do it without potentially messing up our tables
		// and throwing "this id is already in use" errors
		String sql = "INSERT INTO accounts (balance) VALUES ?, ? ";
	}
	private void addDummyToUserTable(User dummy) {
	    SqlRowSet result = jdbcTemplate.queryForRowSet("INSERT INTO users (username, password_hash) VALUES (?, ?) RETURNING user_id", 
	            dummy.getUsername(), dummy.getPassword());
	    if (result.next())
	        dummy.setId(result.getLong("user_id"));
	}
	private void addFakeAccountsToAccountTable(Account account) {
		SqlRowSet result = jdbcTemplate.queryForRowSet("INSERT INTO accounts (user_id, balance) VALUES (?, ?) RETURNING account_id",
				account.getUserId(), account.getAccountBalance());
		if(result.next())
			account.setAccountId(result.getLong("account_id"));
	}
	
	@After
	public void tearDown() throws Exception {
		rollback();
	}

	@Test
	public void transfer_adds_row_to_transfer_table() {
		assertEquals(0, transferDao.getAllTransfers(accountOne).size());
		assertTrue(transferDao.addRowToTransfer(transferOne));
		assertEquals(1, transferDao.getAllTransfers(accountOne).size());
		
	}
}
