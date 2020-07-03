package com.techelevator.tenmo.daotests;

import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.tenmo.dao.TransferSqlDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

class TransferSqlDAOTest extends DAOIntegrationTest {
	
	private TransferSqlDAO transferDao;
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
		transferDao = new TransferSqlDAO(jdbcTemplate);
		
		// Apologies for communication lapses and typos due to hunger lol
		// Pardon the wall of words also due to hunger
		
		// Create fake users in Java and put them into our database table "users"
		// Why? We really want to actually make fake transfers to test this DAO 
		// But the table "transfers" is being extra and won't let any of its columns be null
		// (see DbVis > TEnmo > tenmo > public > TABLE > transfers > Columns > NULLABLE where everything is 0, 
		// which I THINK means "false")
		
		// So that means "account_from" and "account_to" have to have actual, legit account ids
		// BUT the accounts table is ALSO being extra and won't let any of its columns accept null either
		
		// Which means to have working fake accounts so we can have working fake transfers, we have to make sure
		// the accounts have legit user_id's.
		// And that means we need to start all the way at the beginning and make fake Users. :'(
		
		// So back to making fake users in Java and our database:
		badGuy = new User();
		badGuy2 = new User();
		badGuy.setUsername(DEFAULT_USER_NAME);
		badGuy2.setUsername(DEFAULT_USER_NAME_2);
		badGuy.setPassword(DEFAULT_PASSWORD);
		badGuy.setPassword(DEFAULT_PASSWORD_2);
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
		
		transferOne = new Transfer(TYPE_SEND, "Approved", accountOne.getAccountId(), accountTwo.getAccountId(), BigDecimal.TEN);
		transferTwo = new Transfer(TYPE_SEND, "Approved", accountTwo.getAccountId(), accountOne.getAccountId(), BigDecimal.ONE);
		
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
	
	

	// Now for the tests
	// TransferSqlDAO has 2 methods so far that can be tested:
	// 1) getAllTransfers(Account account) - returns all transfers for a specific account
	// 		Separately test: a) If the account sends money and the transfer goes through, does that get included in the return list here?
	// 						b) EXTRA: If the account receives money and the transfer goes through, does that get included in the return?
	//						c) If the account tries to send money but the transfer is invalid, make sure the return list does NOT include that
	//						(We checked that last one by eye. There might be other stuff but this is all I can think of right now. Feel free to think of more!)
	//
	// 2) addRowToTransfer(Transfer newTransfer) - adds a new valid transfer to the database's "transfers" table
	//		Separately test: a) If the transfer is valid, the size of the return list after the addition should be the old size + 1
	// 						b) If the transfer is invalid, the size of the return list should remain unchanged
	@Test
	public void transfer_adds_row_to_transfer_table() {
		assertNull(transferDao.getAllTransfers(accountOne));
		
	}

}
