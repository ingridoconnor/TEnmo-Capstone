package com.techelevator.tenmo.daotests;

import java.math.BigDecimal;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.tenmo.dao.TransferSqlDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

class TransferSqlDAOTest extends DAOIntegrationTest {
	
	private TransferSqlDAO transferDao;
	private JdbcTemplate jdbcTemplate;
	private Account accountOne;
	private Account accountTwo;
	private User badGuy;
	private User badGuy2;
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
		jdbcTemplate = new JdbcTemplate(getDataSource());
		transferDao = new TransferSqlDAO(jdbcTemplate);
		badGuy = new User();
		badGuy2 = new User();
		accountOne = new Account();
		accountTwo = new Account();
		accountOne.setAccountBalance(TEST_BALANCE);
		accountTwo.setAccountBalance(TEST_BALANCE);
		badGuy.setUsername(DEFAULT_USER_NAME);
		badGuy2.setUsername(DEFAULT_USER_NAME_2);
		badGuy.setPassword(DEFAULT_PASSWORD);
		badGuy.setPassword(DEFAULT_PASSWORD_2);
		addDummyToUserTable(badGuy);
		addDummyToUserTable(badGuy2);
		
		String sql = "INSERT INTO accounts (balance) VALUES ?, ? ";
	}
	private void addDummyToUserTable(User dummy) {
	    SqlRowSet result = jdbcTemplate.queryForRowSet("INSERT INTO users (username, password_hash) VALUES (?, ?) RETURNING user_id", 
	            dummy.getUsername(), dummy.getPassword());
	    if (result.next())
	        dummy.setId(result.getLong("user_id"));
	}

	@Test
	public void transfer_adds_row_to_transfer_table() {
		int sizeBefore = transferDao.getAllTransfers(accountOne).size();
		
	}

}
