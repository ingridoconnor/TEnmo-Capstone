package com.techelevator.tenmo.daotests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.tenmo.dao.UserSqlDAO;
import com.techelevator.tenmo.model.User;

public class UserSqlDAOTest extends DAOIntegrationTest {

	private UserSqlDAO userDao;
	private JdbcTemplate jdbcTemplate;
	private User defaultDummy;
	private static final String DEFAULT_USER_NAME = "defaultDummy";
	private static final String DEFAULT_PASSWORD = "makingthisup";
	private static final String SECOND_DUMMY_USER_NAME = "anotherDummy";
	
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
		userDao = new UserSqlDAO(jdbcTemplate);
		
		// Add dummy user to table users
		defaultDummy = new User();
		defaultDummy.setUsername(DEFAULT_USER_NAME);
		defaultDummy.setPassword(DEFAULT_PASSWORD);
		addDummyToUserTable(defaultDummy);
	}
	
	private void addDummyToUserTable(User dummy) {
		SqlRowSet result = jdbcTemplate.queryForRowSet("INSERT INTO users (username, password_hash) VALUES (?, ?) RETURNING user_id", 
				dummy.getUsername(), dummy.getPassword());
		if (result.next())
			dummy.setId(result.getLong("user_id"));
	}
	
	@After
	public void tearDown() throws Exception {
		rollback();
	}
	
	@Test
	public void find_correct_user_id_by_username() {
		long expected = defaultDummy.getId();
		long actual = (long)userDao.findIdByUsername(defaultDummy.getUsername());
		assertEquals(expected, actual);
	}
	
	@Test
	public void adding_user_changes_list_of_total_users() {
		int sizeBefore = userDao.findAll().size();
		// Needs to check size after adding 2nd dummy user
		User anotherDummy = new User();
		anotherDummy.setUsername(SECOND_DUMMY_USER_NAME);
		anotherDummy.setPassword(DEFAULT_PASSWORD);
		addDummyToUserTable(anotherDummy);
		int actual = userDao.findAll().size();
		assertEquals(sizeBefore + 1, actual);
	}
	
	@Test
	public void find_correct_user_by_username() {
		User actual = userDao.findByUsername(DEFAULT_USER_NAME);
		assertEquals(defaultDummy.getId(), actual.getId());
		assertEquals(defaultDummy.getUsername(), actual.getUsername());
	}
	
	@Test
	public void create_also_changes_list_of_total_users() {
		int sizeBefore = userDao.findAll().size();
		assertTrue(userDao.create(SECOND_DUMMY_USER_NAME, DEFAULT_PASSWORD));
		assertEquals(sizeBefore + 1, userDao.findAll().size());
	}
}
