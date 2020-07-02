package com.techelevator.tenmo.daotests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.tenmo.dao.AccountSqlDAO;
import com.techelevator.tenmo.dao.TransferSqlDAO;

class TransferSqlDAOTest extends DAOIntegrationTest {
	
	private TransferSqlDAO transferDao;
	private JdbcTemplate jdbcTemplate;
	
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

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
